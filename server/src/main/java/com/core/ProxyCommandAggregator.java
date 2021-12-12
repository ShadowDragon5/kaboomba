package com.core;

import com.core.enums.ClientAction;
import com.entities.players.Player;
import com.mediator.Colleague;
import com.mediator.Mediator;

public class ProxyCommandAggregator implements ICommandAggregator, Colleague {
    private CommandAggregator commandAggregator;
    private Mediator mediator;

    public ProxyCommandAggregator(CommandAggregator commandAggregator, Mediator mediator) {
        this.commandAggregator = commandAggregator;
        this.mediator = mediator;
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

    @Override
    public Mediator getMediator() {
        return mediator;
    }

    @Override
    public void sendMessage(String message) {
        mediator.broadcast(this, message);
    }

    @Override
    public void receiveMessage(String message) {
        if(message.equals("INITIATE_STATE_SAVE")) {
            this.addCommand("CLEAR_SAVES");
            this.addCommand("SAVE");
        }
    }
}
