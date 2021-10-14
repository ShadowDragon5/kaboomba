package com.core;

import com.entities.*;
import com.utils.*;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;

import java.util.*;
import java.util.concurrent.Callable;

public class ServerApplication {
    private static final HashMap<Connection, String> connections = new HashMap<>();
    private static ServerState serverState = new ServerState();

    public static void main(String... args) throws Exception {
        GameMap gameMap = new GameMap();
        gameMap.loadMap("src/main/resources/map1.tmx");

        Server server = new Server(1000000, 1000000);

        server.start();
        server.bind(54555);

        server.addListener(new Listener() {
            public void received(Connection connection, Object object) {
                if (!(object instanceof String)) {
                    return;
                }

                String[] contents = String.valueOf(object).split(";");
                ClientAction clientAction = ClientAction.valueOf(contents[0]);
                Player playerToUpdate;
                String id;

                if (clientAction == ClientAction.CONNECTED) {
                    String payload = contents[1];
                    PlayerColors playerColor = UtilityMethods.getPlayerColorOrDefault(payload);

                    // Create player using abstract factory
                    PlayerCreator playerCreator = new DefaultPlayerCreator();
                    Player player = playerCreator.createPlayer(playerColor);

                    int playerCount = serverState.getState().getPlayers().size() + 1;
                    float playerDim = 1.5f *  player.getDimensions();

                    float xPos = playerCount == 1 || playerCount == 4 ?
                            UtilityMethods.preciseArithmetics(-1f, playerDim, ArithmeticActions.SUM)
                            :
                            UtilityMethods.preciseArithmetics(1f, playerDim, ArithmeticActions.MIN);

                    float yPos = playerCount == 1 || playerCount == 3 ?
                            UtilityMethods.preciseArithmetics(1f, playerDim, ArithmeticActions.MIN)
                            :
                            UtilityMethods.preciseArithmetics(-1f, playerDim, ArithmeticActions.SUM);

                    player.setPosition(new Position(xPos, yPos));

                    connections.put(connection, player.ID);
                    serverState.attach(new Client(serverState, connection, player.ID));
                    serverState.getState().addPlayer(player);

                    String mapJson = String.format("%s;%s", ServerAction.MAP_INIT, Globals.gson.toJson(gameMap));
                    connection.sendTCP(mapJson);
                } else {
                    id = connections.get(connection);
                    playerToUpdate = serverState.getState().getPlayer(id);

                    Position oldPosition = new Position(playerToUpdate.getPosition().getX(), playerToUpdate.getPosition().getY());
                    PlayersAbstractFactory playerFactory = playerToUpdate.getFactory();

                    switch (clientAction) {
                        case MOVE_UP:
                            playerToUpdate.move(Direction.UP);
                            break;
                        case MOVE_DOWN:
                            playerToUpdate.move(Direction.DOWN);
                            break;
                        case MOVE_LEFT:
                            playerToUpdate.move(Direction.LEFT);
                            break;
                        case MOVE_RIGHT:
                            playerToUpdate.move(Direction.RIGHT);
                            break;
                        case PLANT_BOMB:
                            Bomb bomb = playerFactory.createBomb(playerToUpdate);
                            serverState.getState().addBomb(bomb);
                            scheduleTask(() -> {
                                serverState.getState().removeBomb(bomb);
                                serverState.notifyObservers();
                                return null;
                            }, "Bomb_Timer", bomb.getLifespan());
                            break;
                        case PLANT_PIT:
                            Pit pit = playerFactory.createPit(playerToUpdate);
                            serverState.getState().addPit(pit);
                            scheduleTask(() -> {
                                serverState.getState().removePit(pit);
                                serverState.notifyObservers();
                                return null;
                            }, "Pit_Timer", pit.getLifespan());
                            break;
                        case PLANT_SHIELD:
                            Shield shield = playerFactory.createShield(playerToUpdate);
                            serverState.getState().addShield(shield);
                            scheduleTask(() -> {
                                serverState.getState().removeShield(shield);
                                serverState.notifyObservers();
                                return null;
                            }, "Shield_Timer", shield.getLifespan());
                    }

                    // Collision with wall
                    if (playerCollidesWithWall(gameMap, playerToUpdate)) {
                        playerToUpdate.setPosition(oldPosition);
                    } else {
                        serverState.getState().updateStatePlayer(id, playerToUpdate);
                    }

                    GameObject box = playerCollidesWithBox(serverState.getState().getBoxes(), playerToUpdate);
                    if (box != null) {
                       serverState.getState().removeBox(box);
                    }
                }

                serverState.notifyObservers();
            }

            @Override
            public void connected(Connection incomingConnection) {
                System.out.println("Connected" + incomingConnection.getID());

            }

            @Override
            public void disconnected(Connection outGoingConnection) {
                String id = connections.get(outGoingConnection);
                serverState.getState().removePlayer(id);
                serverState.notifyObservers();
                System.out.println("Disconnected" + outGoingConnection.getID());
            }
        });
    }

    private static boolean playerCollidesWithWall(GameMap map, GameObject obj) {
        return map.getGameObjects().stream()
                .filter(it->it instanceof Wall)
                .filter(it->it.collides(obj)).count() >= 1;
    }

    private static GameObject playerCollidesWithBox(ArrayList<GameObject> boxes, GameObject player) {
        return boxes.stream()
            .filter(it->it.collides(player))
            .findFirst().orElse(null);
    }

    private static void scheduleTask(Callable<Void> method, String name, Long duration) {
        TimerTask task = new TimerTask() {
            public void run() {
                try {
                    method.call();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        Timer timer = new Timer(name);
        timer.schedule(task, duration);
    }
}
