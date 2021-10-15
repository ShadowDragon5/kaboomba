package com.commands;

import com.controllers.BombExplosionController;
import com.core.State;
import com.entities.Bomb;

import static com.core.Scheduler.scheduleTask;

public class ExplodePlantedBombCommand implements Command {

    private final Bomb bomb;
    private final State state = State.getInstance();
    private final BombExplosionController explosionController;

    public ExplodePlantedBombCommand(Bomb bomb, BombExplosionController explosionController) {
        this.bomb = bomb;
        this.explosionController = explosionController;
    }
    @Override
    public void execute() {
        state.removeBomb(bomb);

        var explosion = explosionController.createExplosion(bomb);
        state.addBombExplosion(explosion);

        scheduleTask(() -> {
            explosion.forEach(state::removeExplosion);
            return null;
        }, "Explosion_Timer", explosion.get(0).getLifespan());
    }
}
