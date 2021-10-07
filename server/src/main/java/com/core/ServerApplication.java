package com.core;

import com.entities.*;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.gsonParsers.CustomJsonAdapter;
import com.utils.*;

import java.util.HashMap;

public class ServerApplication {
    private static final HashMap<Connection, String> connections = new HashMap<>();
    private static ServerState serverState = new ServerState();

    public static void main(String... args) throws Exception {
        GsonBuilder gsonBuilder = new GsonBuilder()
                .registerTypeAdapter(GameObject.class, new CustomJsonAdapter<GameObject>())
                .registerTypeAdapter(Player.class, new CustomJsonAdapter<Player>())
                .registerTypeAdapter(Bomb.class, new CustomJsonAdapter<Bomb>())
                .registerTypeAdapter(Shield.class, new CustomJsonAdapter<Shield>())
                .registerTypeAdapter(BoxExplosion.class, new CustomJsonAdapter<BoxExplosion>("com.utils."))
                .registerTypeAdapter(Pit.class, new CustomJsonAdapter<Pit>());

        Gson gson = gsonBuilder.create();

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

                    String mapJson = String.format("%s;%s", ServerAction.MAP_INIT, gson.toJson(gameMap));
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
                            serverState.getState().addBomb(playerFactory.createBomb(playerToUpdate));
                            break;
                        case PLANT_PIT:
                            serverState.getState().addPit(playerFactory.createPit(playerToUpdate));
                            break;
                        case PLANT_SHIELD:
                            serverState.getState().addShield(playerFactory.createShield(playerToUpdate));

                    }

                    if(playerCollides(gameMap, playerToUpdate)){
                        playerToUpdate.setPosition(oldPosition);
                    }else{
                        serverState.getState().updateStatePlayer(id, playerToUpdate);
                    }
                }

                serverState.notifyObservers(gson);
            }

            @Override
            public void connected(Connection incomingConnection) {
                System.out.println("Connected" + incomingConnection.getID());

            }

            @Override
            public void disconnected(Connection outGoingConnection) {
                String id = connections.get(outGoingConnection);
                serverState.getState().removePlayer(id);
                serverState.notifyObservers(gson);
                System.out.println("Disconnected" + outGoingConnection.getID());
            }
        });
    }

    private static boolean playerCollides(GameMap map, GameObject obj){
        return map.getGameObjects().stream()
                .filter(it->it instanceof Wall)
                .filter(it->it.collides(obj)).count() >= 1;
    }
}
