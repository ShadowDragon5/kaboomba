package com.utils;

import com.core.State;
import com.entities.*;

public class DropPowerUp extends BoxExplosion {
    @Override
    public void explosionEffect(Box box) {
        PowerUp powerup;

        var random = Math.random();
        if (random < 0.30f)
            powerup = new SpeedPowerUp(box.getPosition().clone().snap());
        else if (random < 0.60f)
            powerup = new BombPowerPowerUp(box.getPosition().clone().snap());
        else if (random < 0.90f)
            powerup = new BombAmmoPowerUp(box.getPosition().clone().snap());
        else
            powerup = new HealthyPowerUp(box.getPosition().clone().snap());

        State.getInstance().addPowerup(powerup);
    }
}
