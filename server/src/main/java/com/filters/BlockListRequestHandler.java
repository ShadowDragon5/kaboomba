package com.filters;

import com.esotericsoftware.kryonet.Connection;

public class BlockListRequestHandler extends RequestHandler {
    @Override
    public void handleRequest(Connection connection, Object object) {
        System.out.println("BlockListRequestHandler");
        super.handleRequest(connection, object);
    }
}