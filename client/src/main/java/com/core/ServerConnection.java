package com.core;

import com.entities.Player;
import com.entities.Position;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Client;
import org.javatuples.Pair;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ServerConnection {

    private Client client;

    private ServerConnection(){}

    private static ServerConnection serverConnection = null;

    public static ServerConnection getInstance() {
        if(serverConnection == null) {
            return new ServerConnection();
        }

        return serverConnection;
    }

    public Client startListening(){
        Client client = new Client();

        Kryo kryo = client.getKryo();
        kryo.register(Object[].class);
        kryo.register(State.class);
        kryo.register(Player.class);
        kryo.register(ClientAction.class);
        kryo.register(Position.class);
        kryo.register(List.of().getClass());
        kryo.register(Arrays.class);
        kryo.register(ArrayList.class);
        kryo.register(org.javatuples.Pair.class);

        client.start();

        try {
            client.connect(5000, "localhost", 54555);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return client;
    }
}
