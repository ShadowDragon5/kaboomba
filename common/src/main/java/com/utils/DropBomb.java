package com.utils;

import com.entities.*;

public class DropBomb extends BoxExplosion {
    @Override
    public GameObject explosionEffect() {
        System.out.println("Box drops bomb");
        return new Bomb();
    }
}
