package com.commands;

import com.core.State;
import com.entities.bomb.Bomb;
import com.entities.players.Player;
import com.factories.player.PlayersAbstractFactory;

import static com.utils.Scheduler.scheduleTask;

public class PlantBombCommand implements Command {

    private final State state = State.getInstance();

    private final PlayersAbstractFactory playerFactory;
    private final Player player;

    public PlantBombCommand(Player player) {
        this.playerFactory = player.getFactory();
        this.player = player;
    }

    @Override
    public void execute() {
        if (player.getBombsPlanted() >= player.getBombAmmo())
            return;
        Bomb bomb = playerFactory.createBomb(player);
        player.setBombsPlanted(player.getBombsPlanted() + 1);
        state.addBomb(bomb);
        scheduleTask(() -> {
            bomb.explode();
            player.setBombsPlanted(player.getBombsPlanted() - 1);
            return null;
        }, "Bomb_Timer", bomb.getLifespan());
    }
}
