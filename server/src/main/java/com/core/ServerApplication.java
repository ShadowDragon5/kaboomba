package com.core;

import com.entities.Player;
import com.entities.Position;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;
import org.javatuples.Pair;

import java.io.IOException;
import java.util.HashMap;

public class ServerApplication {
    private static final HashMap<Connection, Player> connections = new HashMap<>();
    private static State state;

    public static void main(String ...args){
        ClientActionResolver clientActionResolver = new ClientActionResolver();

        Kryo kryo = new Kryo();
        kryo.register(org.javatuples.Pair.class);

        state = State.getInstance();

        Server server = new Server();
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
                Pair<ClientAction, Object> clientActionObjectPair = (Pair<ClientAction, Object>) object;
                    switch (clientActionObjectPair.getValue0()){
                        case CONNECTED:
                            //Creates new connection and initializes state for new player
                            Player newPlayer = (Player) clientActionObjectPair.getValue1();
                            connections.put(connection, newPlayer);
                            state.addPosition(newPlayer);

                            //Sends new server state to all clients
                            for (Connection client : connections.keySet()) {
                                client.sendTCP(state.getPositions());
                            }
                            break;
                        case MOVE_UP:
                            Player player = connections.get(connection);
                            Position positionToUpdate = state.getPosition(player);
                            positionToUpdate.incrementY();

                            state.updateStatePosition(player, positionToUpdate);
                        default:
                            break;
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
