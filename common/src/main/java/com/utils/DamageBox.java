package com.utils;

import com.core.State;
import com.entities.*;

public class DamageBox extends BoxExplosion {
    public DamageBox() {}

    @Override
    public void explosionEffect(Box box) {
        var damagedBox = new DamagedBox(box.getPosition().clone().snap());
        State.getInstance().removeBox(box);
        State.getInstance().addBox(damagedBox);
        System.out.println("Box is only damaged");
    }
}
