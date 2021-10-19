package com.utils;

import com.core.State;
import com.entities.Box;
import com.entities.PowerUp;
import com.entities.SpeedPowerUp;

public class DropPowerUp extends BoxExplosion {
    @Override
    public void explosionEffect(Box box) {
        System.out.println("Box dropped power up");
        PowerUp powerup = new SpeedPowerUp(box.getPosition().clone().snap());
        State.getInstance().addPowerup(powerup);
    }
}
