package com.core;

import com.entities.*;
import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;

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

        //TODO should be refactored to menu
        // Select team
        Scanner sc = new Scanner(System.in);
        System.out.print("Select team: GREEN | BLUE");
        String color = sc.nextLine();
        if (color.equals(""))
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
                        State state = Globals.gson.fromJson(contents[1], State.class);
                        game.setState(state);
                        break;
                    case MAP_INIT:
                        GameMap map = Globals.gson.fromJson(contents[1], GameMap.class);
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
