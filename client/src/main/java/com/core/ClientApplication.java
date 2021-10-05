package com.core;

import com.entities.*;
import com.entities.Player;
import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.gsonParsers.CustomJsonAdapter;

import java.util.Scanner;

public class ClientApplication {

    private final ServerConnection connection = ServerConnection.getInstance();
    private final Client client;

    public ClientApplication() {
        this.client = connection.startListening();
    }

    public static void main(String[] args) {
        // Get client
        ClientApplication application = new ClientApplication();
        Client appClient = application.client;

        // Register gson custom serializers/deserializers
        GsonBuilder gsonBuilder = new GsonBuilder()
                .registerTypeAdapter(GameObject.class, new CustomJsonAdapter<GameObject>())
                .registerTypeAdapter(Player.class, new CustomJsonAdapter<Player>())
                .registerTypeAdapter(Bomb.class, new CustomJsonAdapter<Bomb>())
                .registerTypeAdapter(Shield.class, new CustomJsonAdapter<Shield>())
                .registerTypeAdapter(Pit.class, new CustomJsonAdapter<Pit>());

        Gson gson = gsonBuilder.create();

        //Select team - should be refactored to menu
        Scanner sc= new Scanner(System.in);
        System.out.print("Select team: GREEN | BLUE");
        String color = sc.nextLine();
        if(color.equals(""))
            color = Globals.defaultPlayerColor.toString();

        // Initialize game renderer and controls listener
        GameRenderer game = new GameRenderer(appClient);

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

        String playerColorString = String.format("%s;%s", ClientAction.CONNECTED, color);
        appClient.sendTCP(playerColorString);

        // Launch game
        game.run();
    }

}
