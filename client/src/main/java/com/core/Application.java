package com.core;

import com.entities.Player;
import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.google.gson.Gson;

import java.util.UUID;

public class Application {

    private final ServerConnection connection = ServerConnection.getInstance();
    private final Client client;

    public Application() {
        this.client = connection.startListening();
    }

    public static void main(String[] args) {
        Application application = new Application();
        Gson gson = new Gson();
        Client appClient = application.client;

        Game game = new Game(appClient);
        Player player = new Player(UUID.randomUUID().toString());

        appClient.addListener(new Listener() {
            public void received (Connection connection, Object object) {
                if (!(object instanceof String)) {
                    return;
                }

                String response = String.valueOf(object);

                State state = gson.fromJson(response, State.class);
                game.setState(state);
            }
        });

        System.out.println("> " + ClientAction.CONNECTED);
        String playerString = String.format("%s;%s", ClientAction.CONNECTED, gson.toJson(player));
        appClient.sendTCP(playerString);

        game.run();
    }

}
