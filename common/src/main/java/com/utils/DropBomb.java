package com.utils;

import com.core.State;
import com.entities.*;

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
