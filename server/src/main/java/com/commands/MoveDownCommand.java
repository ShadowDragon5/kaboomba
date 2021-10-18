package com.commands;

import com.core.Direction;
import com.entities.Player;

public class MoveDownCommand implements Command {
    private final Player player;

    public MoveDownCommand(Player player) {
        this.player = player;
    }

    @Override
    public void execute() {
        player.move(Direction.DOWN);
    }
}
