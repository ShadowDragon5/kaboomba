package com.core;

import com.esotericsoftware.kryonet.Client;

import java.io.IOException;

public class ServerConnection {

    // private Client client;

    private ServerConnection() {}

    private static ServerConnection serverConnection = null;

    public static ServerConnection getInstance() {
        if(serverConnection == null) {
            return new ServerConnection();
        }

        return serverConnection;
    }

    public ClientProxy startListening(){
        ClientProxy client = new ClientProxy(1000000,1000000);

        // Kryo kryo = client.getKryo();
        // kryo.register(Object[].class);
        client.start();

        try {
            client.connect(5000, "194.5.157.112", 54555);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return client;
    }
}
