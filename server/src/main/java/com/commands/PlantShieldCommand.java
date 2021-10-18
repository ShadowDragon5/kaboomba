package com.commands;

import com.core.State;
import com.entities.Player;
import com.entities.Shield;
import com.utils.PlayersAbstractFactory;

import static com.utils.Scheduler.scheduleTask;

public class PlantShieldCommand implements Command {
    private final State state = State.getInstance();
    private final PlayersAbstractFactory playerFactory;
    private final Player player;

    public PlantShieldCommand(PlayersAbstractFactory playerFactory) {
        this.playerFactory = playerFactory;
        this.player = playerFactory.getPlayer();
    }

    @Override
    public void execute() {
        Shield shield = playerFactory.createShield(player);
        state.addShield(shield);
        scheduleTask(() -> {
            state.removeShield(shield);
            return null;
        }, "Shield_Timer", shield.getLifespan());
    }
}
