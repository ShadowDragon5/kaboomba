package com.core;

import com.core.enums.ClientAction;
import com.entities.players.Player;

public interface ICommandAggregator {
    void addCommand(ClientAction clientAction, Player playerToUpdate);
    void addCommand(String action);
}
