package com.commands;

import com.controllers.BombExplosionController;
import com.core.State;
import com.entities.Bomb;
import com.entities.Player;
import com.utils.PlayersAbstractFactory;

import static com.core.Scheduler.scheduleTask;

public class PlantBombCommand implements Command {

    private final State state = State.getInstance();

    private final PlayersAbstractFactory playerFactory;
    private final Player player;
    private final BombExplosionController explosionController;

    public PlantBombCommand(PlayersAbstractFactory playerFactory, BombExplosionController explosionController) {
        this.playerFactory = playerFactory;
        this.player = playerFactory.getPlayer();
        this.explosionController = explosionController;
    }

    @Override
    public void execute() {
        Bomb bomb = playerFactory.createBomb(player);
        state.addBomb(bomb);

        scheduleTask(() -> {
            ExplodePlantedBombCommand removeExplosionCommand =
                    new ExplodePlantedBombCommand(bomb, explosionController);
            removeExplosionCommand.execute();
            return null;
        }, "Bomb_Timer", bomb.getLifespan());
    }
}
