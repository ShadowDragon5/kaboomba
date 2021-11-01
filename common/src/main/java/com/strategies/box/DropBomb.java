package com.strategies.box;

import com.core.State;
import com.entities.bomb.BaseBomb;
import com.entities.tiles.Box;

import static com.utils.Scheduler.scheduleTask;

public class DropBomb extends BoxExplosion {
    @Override
    public void explosionEffect(Box box) {
        BaseBomb baseBomb = new BaseBomb(box.getPosition().clone().snap());
        State.getInstance().addBomb(baseBomb);
        scheduleTask(() -> {
            baseBomb.explode();
            return null;
        }, "DropBombExplode_timer", baseBomb.getLifespan());
    }
}
