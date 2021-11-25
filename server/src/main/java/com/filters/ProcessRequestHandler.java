package com.filters;

import com.commands.ConnectedCommand;
import com.core.Defaults;
import com.core.ServerFacade;
import com.core.enums.ClientAction;
import com.entities.InitialPlayerConnection;
import com.entities.players.Player;
import com.esotericsoftware.kryonet.Connection;

import java.util.HashMap;

public class ProcessRequestHandler extends RequestHandler {
    private final ServerFacade facade;
    private final HashMap<Connection, String> connections;

    public ProcessRequestHandler(ServerFacade facade, HashMap<Connection, String> connections) {
        this.facade = facade;
        this.connections = connections;
    }

    @Override
    public void handleRequest(Connection connection, Object object) {
        // System.out.println("ProcessRequestHandler");
        String[] contents = String.valueOf(object).split(";");
        ClientAction clientAction = ClientAction.valueOf(contents[0]);

        // Connection flow
        if (clientAction == ClientAction.CONNECTED) {
            InitialPlayerConnection playerConnection = Defaults.gson.fromJson(contents[1], InitialPlayerConnection.class);
            ConnectedCommand command = new ConnectedCommand(playerConnection, object1 -> {
                facade.connectPlayer(connections, connection, object1);
            });
            facade.getQueuedCommands().add(command);
            return;
        }

        String id = connections.get(connection);
        Player playerToUpdate = facade.getServerState().getState().getPlayer(id);
        facade.getProxyCommandAggregator().addCommand(clientAction, playerToUpdate);

        super.handleRequest(connection, object);
    }
}
