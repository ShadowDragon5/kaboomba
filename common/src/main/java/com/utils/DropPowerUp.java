package com.utils;

import com.core.State;
import com.entities.BombPowerPowerUp;
import com.entities.Box;
import com.entities.PowerUp;
import com.entities.SpeedPowerUp;

public class DropPowerUp extends BoxExplosion {
    @Override
    public void explosionEffect(Box box) {
        System.out.println("Box dropped power up");
        PowerUp powerup;

        var random = Math.random();
        if (random < 0.5f)
            powerup = new SpeedPowerUp(box.getPosition().clone().snap());
        else
            powerup = new BombPowerPowerUp(box.getPosition().clone().snap());

        State.getInstance().addPowerup(powerup);
    }
}
