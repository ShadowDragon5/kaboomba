package com.core;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;
import org.javatuples.Pair;

import java.io.IOException;
import java.util.ArrayList;

public class ServerApplication {
    private static final ArrayList<Connection> connections = new ArrayList<>();

    public static void main(String ...args){
        ClientActionResolver clientActionResolver = new ClientActionResolver();

        Server server = new Server();
        server.start();
        try {
            server.bind(54555);
        } catch (IOException e) {
            System.out.println("Unable to start server");
            e.printStackTrace();
        }

        server.addListener(new Listener() {
            public void received (Connection connection, Pair<ClientAction, Object> object) {
                var resolvedObject = clientActionResolver.resolve(object);

                connections.forEach(it-> {
                        it.sendTCP(resolvedObject);
                    });
                
                System.out.println(resolvedObject);
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
