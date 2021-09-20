package com.core;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;

import java.io.IOException;
import java.util.ArrayList;

public class Application {
    private static final ArrayList<Connection> connections = new ArrayList<>();

    public static void main(String ...args){
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
                if (object instanceof String) {
                    connections.forEach(it-> {
                        it.sendTCP(connection.getID() + ":" + object);
                    });
                    System.out.println(object);
                }
            }

            @Override
            public void connected(Connection incomingConnection) {
                System.out.println("Connected" + incomingConnection.getID());
                connections.add(incomingConnection);
            }

            @Override
            public void disconnected(Connection outGoingConnection) {
                System.out.println("Disconnected" + outGoingConnection.getID());
                connections.removeIf(it->it.getID() == outGoingConnection.getID());
            }
        });
    }
}
