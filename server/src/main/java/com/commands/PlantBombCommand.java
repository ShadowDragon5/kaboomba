package com.commands;

import com.core.State;
import com.entities.Bomb;
import com.entities.Player;
import com.utils.PlayersAbstractFactory;

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
        Bomb bomb = playerFactory.createBomb(player);
        state.addBomb(bomb);
         scheduleTask(() -> {
             bomb.explode();
             return null;
         }, "Bomb_Timer", bomb.getLifespan());
    }
}
