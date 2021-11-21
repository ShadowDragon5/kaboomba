package com.commands;

import com.core.State;
import com.core.Defaults;
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
        if (player.getShieldsPlanted() >= Defaults.playerShields)
            return;
        Shield shield = playerFactory.createShield(player);
        player.setShieldsPlanted(player.getShieldsPlanted() + 1);
        state.addShield(shield);
        scheduleTask(() -> {
            state.removeShield(shield);
            player.setShieldsPlanted(player.getShieldsPlanted() - 1);
            return null;
        }, "Shield_Timer", shield.getLifespan());
    }
}
