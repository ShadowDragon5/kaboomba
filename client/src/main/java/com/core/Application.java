package com.core;

import com.entities.*;
import com.entities.Player;
import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.google.gson.Gson;

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
        Player player = new Player();

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

        String playerString = String.format("%s;%s", ClientAction.CONNECTED, gson.toJson(player));
        appClient.sendTCP(playerString);

        // while (!game.isReady()) {
            // TODO loading screen
        // }

        game.run();
    }

}
