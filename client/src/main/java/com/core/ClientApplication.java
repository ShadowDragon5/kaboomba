package com.core;

import com.core.enums.ClientAction;
import com.core.enums.ServerAction;
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

        //Select team
        // String color = application.selectTeam();
        // String name = application.inputName();
        String color = "";
        String name = "";

        ClientActionListener inputListener = action -> appClient.sendTCP(action + ";");

        InputController inputController = new InputController(inputListener);
        GameRenderer gameRenderer = new GameRenderer();

        // Initialize game renderer and controls listener
        Game game = new Game(inputController, gameRenderer);

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
                        State state = Defaults.gson.fromJson(contents[1], State.class);
                        State.setNewInstance(state);
                        break;
                    case GAME_INIT:
                        InitialServerResponse response = Defaults.gson.fromJson(contents[1], InitialServerResponse.class);
                        gameRenderer.setMap(response.getGameMap());
                        game.setPlayerId(response.getPlayerId());
                        break;
                }
            }
        });

        String playerColorString = String.format("%s;%s", ClientAction.CONNECTED,
                Defaults.gson.toJson(new InitialPlayerConnection(name, color)));
        appClient.sendTCP(playerColorString);

        // Launch game
        game.run();
    }

    public String selectTeam() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Select team: GREEN | BLUE");
        String color = sc.nextLine();
        if (color.equals(""))
            color = Defaults.playerColor.toString();

        return color;
    }

    public String inputName() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Please write your username:");
        String name = sc.nextLine();

        return name;
    }
}
