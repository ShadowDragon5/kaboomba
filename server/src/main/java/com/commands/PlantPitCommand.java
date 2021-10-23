package com.commands;

import com.core.State;
import com.entities.pits.Pit;
import com.entities.players.Player;
import com.factories.player.PlayersAbstractFactory;

import static com.utils.Scheduler.scheduleTask;

public class PlantPitCommand implements Command {

    private final PlayersAbstractFactory playerFactory;
    private final Player player;
    private final State state = State.getInstance();

    public PlantPitCommand(Player player) {
        this.playerFactory = player.getFactory();
        this.player = player;
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
