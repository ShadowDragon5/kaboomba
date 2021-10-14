package com.utils;

import com.entities.GameObject;

public class DropPowerUp extends BoxExplosion {

    public DropPowerUp() {
    }

    @Override
    public GameObject explosionEffect() {
        System.out.println("Box dropped power up");
        // return new PowerUp();
        return null;
    }
}
