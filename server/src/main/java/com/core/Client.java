package com.core;

import com.core.enums.ServerAction;
import com.esotericsoftware.kryonet.Connection;

public class Client implements Observer {
    private Subject sub;
    private Connection connection;
    private String id;

    public Client(Subject sub, Connection connection, String id) {
        this.sub = sub;
        this.connection = connection;
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public Connection getConnection() {
        return connection;
    }

    @Override
    public void update() {
        try {
            String stateJson = Defaults.gson.toJson(sub.getState());
            connection.sendTCP(String.format("%s;%s", ServerAction.STATE_UPDATE, stateJson));
        } catch (Exception exc) {
            // System.out.println(exc.getMessage());
        }
    }

}
