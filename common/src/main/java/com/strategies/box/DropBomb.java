package com.strategies.box;

import com.core.State;
import com.entities.bomb.BoxBomb;
import com.entities.tiles.Box;

import static com.utils.Scheduler.scheduleTask;

public class DropBomb extends BoxExplosion {
    @Override
    public void explosionEffect(Box box) {
        BoxBomb boxBomb = new BoxBomb(box.getPosition().clone().snap());
        State.getInstance().addBomb(boxBomb);
        scheduleTask(() -> {
            boxBomb.explode();
            return null;
        }, "DropBombExplode_timer",boxBomb.getLifespan());
    }
}
