package com.filters;

import com.esotericsoftware.kryonet.Connection;

import java.util.logging.Logger;

public class LogRequestHandler extends RequestHandler {
    @Override
    public void handleRequest(Connection connection, Object object) {
       System.out.println("LogRequestHandler");
       super.handleRequest(connection, object);
    }
}
