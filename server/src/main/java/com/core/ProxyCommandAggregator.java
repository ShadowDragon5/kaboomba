package com.core;

import com.core.enums.ClientAction;
import com.entities.players.Player;

public class ProxyCommandAggregator implements ICommandAggregator{
    private CommandAggregator commandAggregator;

    public ProxyCommandAggregator(CommandAggregator commandAggregator) {
        this.commandAggregator = commandAggregator;
    }

    @Override
    public void addCommand(ClientAction clientAction, Player playerToUpdate) {
        if(playerToUpdate.isDead())
            return;
        commandAggregator.addCommand(clientAction, playerToUpdate);
    }

    @Override
    public void addCommand(String action) {
        commandAggregator.addCommand(action);
    }
}
