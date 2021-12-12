package com.core;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;
import com.filters.*;
import com.mediator.Mediator;
import com.mediator.ServerMediator;

import javax.print.attribute.standard.Media;
import java.util.HashMap;

public class ServerListener extends Listener {
    private final HashMap<Connection, String> connections = new HashMap<>();
    private final Mediator serverMediator;
    private final ServerFacade facade;
    private final RequestHandler requestHandlerChain;

    public ServerListener(ServerFacade facade) {
        this.facade = facade;
        this.serverMediator = facade.getMediator();
        requestHandlerChain = new LogRequestHandler();

        requestHandlerChain
                .setSuccessor(new ValidationRequestHandler())
                .setSuccessor(new BlockListRequestHandler())
                .setSuccessor(new ProcessRequestHandler(facade, connections));
    }


    public void received(Connection connection, Object object) {
        requestHandlerChain.handleRequest(connection, object);
    }

    @Override
    public void connected(Connection incomingConnection) {
        System.out.println("Connected" + incomingConnection.getID());
    }

    @Override
    public void disconnected(Connection outGoingConnection) {
        String id = connections.get(outGoingConnection);
        serverMediator.getState().removePlayer(id);
        serverMediator.broadcast(null, "NOTIFY_OBSERVERS");
        facade.getProxyCommandAggregator().addCommand("CLEAR_SAVES");
        facade.getProxyCommandAggregator().addCommand("SAVE");
        System.out.println("Disconnected" + outGoingConnection.getID());
    }
}
