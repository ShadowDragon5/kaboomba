package com.commands;

import com.core.State;
import com.entities.players.Player;
import com.entities.shields.Shield;
import com.factories.player.PlayersAbstractFactory;

import static com.utils.Scheduler.scheduleTask;

public class PlantShieldCommand implements Command {
    private final State state = State.getInstance();
    private final PlayersAbstractFactory playerFactory;
    private final Player player;

    public PlantShieldCommand(Player player) {
        this.playerFactory = player.getFactory();
        this.player = player;
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
