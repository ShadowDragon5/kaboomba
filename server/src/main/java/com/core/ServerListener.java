package com.core;

import com.commands.Command;
import com.commands.ConnectedCommand;
import com.core.enums.ClientAction;
import com.entities.InitialPlayerConnection;
import com.entities.players.Player;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import java.util.HashMap;
import java.util.Queue;

public class ServerListener extends Listener {
    private final HashMap<Connection, String> connections = new HashMap<>();
    private final Queue<Command> queuedCommands;
    private final ServerState serverState;
    private final ServerFacade facade;

    public ServerListener(ServerFacade facade) {
        this.facade = facade;
        this.queuedCommands = facade.getQueuedCommands();
        this.serverState = facade.getServerState();
    }

    public void received(Connection connection, Object object) {
        if (!(object instanceof String)) {
            return;
        }

        String[] contents = String.valueOf(object).split(";");
        ClientAction clientAction = ClientAction.valueOf(contents[0]);

        // Connection flow
        if (clientAction == ClientAction.CONNECTED) {
            InitialPlayerConnection playerConnection = Defaults.gson.fromJson(contents[1], InitialPlayerConnection.class);
            ConnectedCommand command = new ConnectedCommand(playerConnection, object1 -> {
                facade.connectPlayer(connections, connection, object1);
            });
            queuedCommands.add(command);
            return;
        }

        String id = connections.get(connection);
        Player playerToUpdate = serverState.getState().getPlayer(id);
        facade.getProxyCommandAggregator().addCommand(clientAction, playerToUpdate);
    }

    @Override
    public void connected(Connection incomingConnection) {
        System.out.println("Connected" + incomingConnection.getID());
    }

    @Override
    public void disconnected(Connection outGoingConnection) {
        String id = connections.get(outGoingConnection);
        serverState.getState().removePlayer(id);
        serverState.notifyObservers();
        facade.getProxyCommandAggregator().addCommand("CLEAR_SAVES");
        facade.getProxyCommandAggregator().addCommand("SAVE");
        System.out.println("Disconnected" + outGoingConnection.getID());
    }
}
