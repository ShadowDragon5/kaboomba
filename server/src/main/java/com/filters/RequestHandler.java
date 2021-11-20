package com.filters;

import com.esotericsoftware.kryonet.Connection;

public abstract class RequestHandler {
    public RequestHandler successor;

    public void handleRequest(Connection connection, Object object) {
        if(successor != null){
            successor.handleRequest(connection, object);
        }
    }

    public RequestHandler getSuccessor() {
        return successor;
    }

    public RequestHandler setSuccessor(RequestHandler request) {
        this.successor = request;
        return request;
    }
}
