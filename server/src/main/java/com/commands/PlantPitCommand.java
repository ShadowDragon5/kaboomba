package com.commands;

import com.core.State;
import com.entities.Pit;
import com.entities.Player;
import com.utils.PlayersAbstractFactory;

import static com.core.Scheduler.scheduleTask;

public class PlantPitCommand implements Command {

    private final PlayersAbstractFactory playerFactory;
    private final Player player;
    private final State state = State.getInstance();

    public PlantPitCommand(PlayersAbstractFactory playersAbstractFactory) {
        this.playerFactory = playersAbstractFactory;
        this.player = playersAbstractFactory.getPlayer();
    }

    @Override
    public void execute() {
        Pit pit = playerFactory.createPit(player);
        state.addPit(pit);
        scheduleTask(() -> {
            state.removePit(pit);
            return null;
        }, "Pit_Timer", pit.getLifespan());
    }
}