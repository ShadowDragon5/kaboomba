package com.commands;

import com.chat.*;
import com.core.enums.ServerAction;
import com.esotericsoftware.kryonet.Connection;

import java.util.HashMap;

public class ChatCommand implements Command {
    private final String message;
    private final String playerId;
    private final Connection connection;
    private final HashMap<Connection, String> connections;

    public ChatCommand(HashMap<Connection, String> connections, String message, Connection connection) {
        this.connections = connections;
        this.message = message;
        this.playerId = connections.get(connection);
        this.connection = connection;
    }

    @Override
    public void execute() {
        var context = new ChatContext(playerId);
        if (message.charAt(0) == '/') {
            var interpreter = new CommandExpression(
                    new AttributeExpression(String.valueOf(message.charAt(1)),
                            new ValueExpression(message.substring(2))));
            connection.sendTCP(String.format("%s;%s", ServerAction.CHAT, interpreter.interpret(context)));
        } else {
            var interpreter = new TextExpression(message);
            interpreter.interpret(context);
            connections.keySet().forEach(it->
                    it.sendTCP(String.format("%s;%s", ServerAction.CHAT, interpreter.interpret(context))));
        }
    }
}
