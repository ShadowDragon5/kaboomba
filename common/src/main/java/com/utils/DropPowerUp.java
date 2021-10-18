package com.utils;

import com.entities.Box;
import com.entities.GameObject;

public class DropPowerUp extends BoxExplosion {

    public DropPowerUp() {
    }

    @Override
    public void explosionEffect(Box box) {
        System.out.println("Box dropped power up");
    }
}
