package com.filters;

import com.esotericsoftware.kryonet.Connection;

public class ValidationRequestHandler extends RequestHandler {
    @Override
    public void handleRequest(Connection connection, Object object) {
        if (!(object instanceof String)) {
            return;
        }
        super.handleRequest(connection, object);
    }
}
