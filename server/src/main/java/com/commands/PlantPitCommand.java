package com.commands;

import com.core.State;
import com.core.Defaults;
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
        if (player.getPitsPlanted() >= Defaults.playerPits)
            return;
        Pit pit = playerFactory.createPit(player);
        player.setPitsPlanted(player.getPitsPlanted() + 1);
        state.addPit(pit);
        scheduleTask(() -> {
            state.removePit(pit);
            player.setPitsPlanted(player.getPitsPlanted() - 1);
            return null;
        }, "Pit_Timer", pit.getLifespan());
    }
}
