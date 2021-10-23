package com.commands;

import com.core.enums.Direction;
import com.entities.players.Player;

public class MoveUpCommand implements Command {

    private final Player player;

    public MoveUpCommand(Player player) {
        this.player = player;
    }
    @Override
    public void execute() {
        player.move(Direction.UP);
    }
}
