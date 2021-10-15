package com.commands;

import com.core.Direction;
import com.entities.Player;

public class MoveLeftCommand implements Command {
    private final Player player;

    public MoveLeftCommand(Player player) {
        this.player = player;
    }
    @Override
    public void execute() {
        player.move(Direction.LEFT);
    }
}
