package com.commands;

import com.core.enums.Direction;
import com.entities.players.Player;

public class MoveRightCommand implements Command {
    private final Player player;

    public MoveRightCommand(Player player) {
        this.player = player;
    }

    @Override
    public void execute() {
        player.move(Direction.RIGHT);
    }
}
