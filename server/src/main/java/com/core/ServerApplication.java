package com.core;

import com.commands.*;
import com.controllers.BombExplosionController;
import com.entities.*;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;
import com.utils.PlayersAbstractFactory;

import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;

public class ServerApplication {
    private static final HashMap<Connection, String> connections = new HashMap<>();
    private static final ServerState serverState = new ServerState();
    private static final Queue<Command> queuedCommands = new ArrayBlockingQueue<Command>(20);

    private static BombExplosionController bombExplosionController;

    public static void main(String... args) throws Exception {
        GameMap gameMap = new GameMap();
        gameMap.loadMap("src/main/resources/map1.tmx");

        BombExplosionController bombExplosionController = new BombExplosionController(gameMap);

        Server server = new Server(1000000, 1000000);

        server.start();
        server.bind(54555);

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                while (!queuedCommands.isEmpty()) {
                    queuedCommands.poll().execute();
                }

                serverState.notifyObservers();
            }
        }, 0, 10);

        server.addListener(new Listener() {
            public void received(Connection connection, Object object) {
                if (!(object instanceof String)) {
                    return;
                }

                String[] contents = String.valueOf(object).split(";");
                ClientAction clientAction = ClientAction.valueOf(contents[0]);

                // Connection flow
                if (clientAction == ClientAction.CONNECTED) {
                    String color = contents[0];
                    ConnectedCommand command = new ConnectedCommand(color, object1 -> {
                        Player player = (Player) object1;
                        connections.put(connection, player.ID);
                        serverState.attach(new Client(serverState, connection, player.ID));
                        serverState.getState().addPlayer(player);

                        String mapJson = String.format("%s;%s", ServerAction.MAP_INIT, Globals.gson.toJson(gameMap));
                        connection.sendTCP(mapJson);
                    });
                    queuedCommands.add(command);
                    return;
                }

                String id = connections.get(connection);
                Player playerToUpdate = serverState.getState().getPlayer(id);
                Position oldPosition = new Position(playerToUpdate.getPosition().getX(), playerToUpdate.getPosition().getY());
                PlayersAbstractFactory playerFactory = playerToUpdate.getFactory();

                Command command = null;

                switch (clientAction) {
                    case MOVE_UP:
                        command = new MoveUpCommand(playerToUpdate);
                        break;
                    case MOVE_DOWN:
                        command = new MoveDownCommand(playerToUpdate);
                        break;
                    case MOVE_LEFT:
                        command = new MoveLeftCommand(playerToUpdate);
                        break;
                    case MOVE_RIGHT:
                        command = new MoveRightCommand(playerToUpdate);
                        break;
                    case PLANT_BOMB:
                        command = new PlantBombCommand(playerFactory, bombExplosionController);
                        break;
                    case PLANT_PIT:
                        command = new PlantPitCommand(playerFactory);
                        break;
                    case PLANT_SHIELD:
                        command = new PlantShieldCommand(playerFactory);
                        break;
                }

                queuedCommands.add(command);

                // Collision with wall
                if (playerCollidesWithWall(gameMap, playerToUpdate)) {
                    playerToUpdate.setPosition(oldPosition);
                } else {
                    serverState.getState().updateStatePlayer(id, playerToUpdate);
                }

                // Collision with wall
                GameObject box = playerCollidesWithBox(serverState.getState().getBoxes(), playerToUpdate);
                if (box != null) {
                    serverState.getState().removeBox(box);
                }
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
                .filter(it -> it instanceof Wall)
                .filter(it -> it.collides(obj)).count() >= 1;
    }

    private static GameObject playerCollidesWithBox(ArrayList<GameObject> boxes, GameObject player) {
        return boxes.stream()
                .filter(it -> it.collides(player))
                .findFirst().orElse(null);
    }
}
