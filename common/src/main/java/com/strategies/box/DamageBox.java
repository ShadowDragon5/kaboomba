package com.strategies.box;

import com.core.State;
import com.entities.tiles.Box;
import com.entities.tiles.DamagedBox;

public class DamageBox extends BoxExplosion {
    public DamageBox() {}

    @Override
    public void explosionEffect(Box box) {
        var damagedBox = new DamagedBox(box.getPosition().clone().snap());
        State.getInstance().addBox(damagedBox);
        State.getInstance().removeBox(box);
    }
}
