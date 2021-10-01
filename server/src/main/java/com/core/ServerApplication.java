package com.core;

import com.entities.GameMap;
import com.entities.Player;
import com.entities.Position;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.HashMap;

public class ServerApplication {
    private static final HashMap<Connection, String> connections = new HashMap<>();
    private static State state;

    public static void main(String ...args) {
        state = State.getInstance();
        Gson gson = new Gson();

        GameMap gameMap = new GameMap();
        gameMap.loadMap("src/main/resources/map1.tmx", state);

        Server server = new Server();

        server.start();
        try {
            server.bind(54555);
        } catch (IOException e) {
            System.out.println("Unable to start server");
            e.printStackTrace();
        }

        server.addListener(new Listener() {
            public void received (Connection connection, Object object) {
                if (!(object instanceof String)) {
                    return;
                }

                String[] contents = String.valueOf(object).split(";");
                ClientAction clientAction = ClientAction.valueOf(contents[0]);
                Player playerToUpdate;
                String id;

                if (clientAction == ClientAction.CONNECTED) {
                    String payload = contents[1];
                    Player player = gson.fromJson(payload, Player.class);
                    connections.put(connection, player.ID);
                    state.addPlayer(player);

                    String mapJson = String.format("%s;%s", ServerAction.MAP_INIT, gson.toJson(gameMap));
                    connection.sendTCP(mapJson);

                } else {
                    id = connections.get(connection);
                    playerToUpdate = state.getPlayer(id);

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
                    }
                    state.updateStatePlayer(id, playerToUpdate);
                }

                String stateJson = gson.toJson(state);
                for (Connection client : connections.keySet()) {
                    client.sendTCP(stateJson);
                }


            }

            @Override
            public void connected(Connection incomingConnection) {
                System.out.println("Connected" + incomingConnection.getID());

            }

            @Override
            public void disconnected(Connection outGoingConnection) {
                System.out.println("Disconnected" + outGoingConnection.getID());
            }
        });
    }
}
