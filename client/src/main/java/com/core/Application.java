package com.core;

import com.entities.*;
import com.entities.Player;
import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.gsonParsers.GameObjectAdapter;
import com.gsonParsers.PlayerAdapter;
import com.utils.DefaultPlayerCreator;
import com.utils.PlayerCreator;

import java.util.Scanner;

public class Application {

    private final ServerConnection connection = ServerConnection.getInstance();
    private final Client client;

    public Application() {
        this.client = connection.startListening();
    }

    public static void main(String[] args) {
        // Get client
        Application application = new Application();
        Client appClient = application.client;

        // Register gson custom serializers/deserializers
        GsonBuilder gsonBuilder = new GsonBuilder()
                .registerTypeAdapter(GameObject.class, new GameObjectAdapter())
                .registerTypeAdapter(Player.class, new PlayerAdapter());
        Gson gson = gsonBuilder.create();

        //Select team - should be refactored to menu
        Scanner sc= new Scanner(System.in);
        System.out.print("Select team: RED | GREEN | BLUE");
        String color = sc.nextLine();
        PlayerColors playerColor = PlayerColors.valueOf(color);

        // Initialize game renderer and controls listener
        GameRenderer game = new GameRenderer(appClient);

        // Create player using abstract factory
        PlayerCreator playerCreator = new DefaultPlayerCreator();
        Player player = playerCreator.createFactory(playerColor);

        // Server callbacks listener
        appClient.addListener(new Listener() {
            public void received (Connection connection, Object object) {
                if (!(object instanceof String)) {
                    return;
                }

                String[] contents = String.valueOf(object).split(";");
                ServerAction serverAction = ServerAction.valueOf(contents[0]);

                switch (serverAction) {
                    case STATE_UPDATE:
                        State state = gson.fromJson(contents[1], State.class);
                        game.setState(state);
                        break;
                    case MAP_INIT:
                        GameMap map = gson.fromJson(contents[1], GameMap.class);
                        game.setMap(map);
                        break;
                }
            }
        });

        String playerString = String.format("%s;%s", ClientAction.CONNECTED, gson.toJson(player, Player.class));
        appClient.sendTCP(playerString);

        // Launch game
        game.run();
    }

}
