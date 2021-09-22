package com.core;

import com.entities.Player;
import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.utils.ActionUtils;

public class Application {

    private final ServerConnection connection = ServerConnection.getInstance();
    private final Client client;
    private State state;

    public Application(){
        this.client = connection.startListening();
    }

    public static void main(String[] args) {
        Application application = new Application();

        Client appClient = application.client;
        Player player = new Player("testName");

        appClient.addListener(new Listener() {
            public void received (Connection connection, Object object) {
                if (object instanceof State) {
                    application.state = (State) object;
                }
            }
        });

        appClient.sendTCP(ActionUtils.createActionObject(ClientAction.CONNECTED, player));

        Game game = new Game(application.state, appClient);
        game.run();
    }

}