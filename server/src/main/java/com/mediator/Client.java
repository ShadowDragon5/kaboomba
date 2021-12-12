package com.mediator;

import com.core.Defaults;
import com.core.enums.ServerAction;
import com.esotericsoftware.kryonet.Connection;

public class Client implements Colleague{
    private Mediator mediator;
    private Connection connection;
    private String id;

    public Client(Mediator mediator, Connection connection, String id) {
        this.mediator = mediator;
        this.connection = connection;
        this.id = id;
    }

    @Override
    public Mediator getMediator() {
        return mediator;
    }

    @Override
    public void sendMessage(String message) {
        mediator.broadcast(this, message);
    }

    @Override
    public void receiveMessage(String message) {
        try {
            String stateJson = Defaults.gson.toJson(mediator.getState());
            connection.sendTCP(String.format("%s;%s", ServerAction.STATE_UPDATE, stateJson));
        } catch (Exception exc) {
            System.out.println(exc.getMessage());
        }
    }
}
