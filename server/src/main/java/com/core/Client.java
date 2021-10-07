package com.core;

import com.google.gson.Gson;
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
    public void update(Gson gson) {
        String stateJson = gson.toJson(sub.getState());
        connection.sendTCP(String.format("%s;%s", ServerAction.STATE_UPDATE, stateJson));
    }

}
