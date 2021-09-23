package com.core;

import com.entities.Player;
import com.entities.Position;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;
import com.google.gson.Gson;
import org.javatuples.Pair;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class ServerApplication {
    private static final HashMap<Connection, Player> connections = new HashMap<>();
    private static State state;

    public static void main(String ...args){
        state = State.getInstance();
        Gson gson = new Gson();


        Server server = new Server();

        Kryo kryo = server.getKryo();

        kryo.register(Object[].class);
        kryo.register(State.class);
        kryo.register(Player.class);
        kryo.register(ClientAction.class);
        kryo.register(Position.class);
        kryo.register(List.of().getClass());
        kryo.register(Arrays.class);
        kryo.register(ArrayList.class);
        kryo.register(org.javatuples.Pair.class);

        server.start();
        try {
            server.bind(54555);
        } catch (IOException e) {
            System.out.println("Unable to start server");
            e.printStackTrace();
        }

        // CONNECTED Player("vardas");
        // MOVE_UP null
        // MOVE_DOWN null
        server.addListener(new Listener() {
            public void received (Connection connection, Object object) {
                if(!(object instanceof String)){
                    return;
                }

                String[] contents = String.valueOf(object).split(";");
                ClientAction clientAction = ClientAction.valueOf(contents[0]);

                switch (clientAction){
                    case CONNECTED:
                        String payload = contents[1];
                        Player player = gson.fromJson(payload, Player.class);
                        connections.put(connection, player);
                        state.addPosition(player);

                        for (Connection client : connections.keySet()) {
                            String positions = gson.toJson(state);
                            client.sendTCP(positions);
                        }
                        break;
                    case MOVE_UP:
                        Player player1 = connections.get(connection);
                        Position positionToUpdate = state.getPosition(player1);
                        positionToUpdate.incrementY();

                        state.updateStatePosition(player1, positionToUpdate);

                        for (Connection client : connections.keySet()) {
                            String positions = gson.toJson(state);
                            client.sendTCP(positions);
                        }
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
