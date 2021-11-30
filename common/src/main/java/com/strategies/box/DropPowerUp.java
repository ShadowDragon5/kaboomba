package com.strategies.box;

import com.core.State;
import com.entities.powerups.*;
import com.entities.tiles.Box;

public class DropPowerUp extends BoxExplosion {
    @Override
    public void explosionEffect(Box box) {
        PowerUp powerup;

        var random = Math.random();
        if (random < 0.30f)
            powerup = new SpeedPowerUp(box.getRectangle().clone().snap());
        else if (random < 0.60f)
            powerup = new BombPowerPowerUp(box.getRectangle().clone().snap());
        else if (random < 0.90f)
            powerup = new BombAmmoPowerUp(box.getRectangle().clone().snap());
        else
            powerup = new HealthyPowerUp(box.getRectangle().clone().snap());

        State.getInstance().addPowerup(powerup);
    }
}
