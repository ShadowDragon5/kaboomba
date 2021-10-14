package com.utils;

import com.entities.GameObject;

public class DestroyBox extends BoxExplosion {
    public DestroyBox() {}

    @Override
    public GameObject explosionEffect() {
        System.out.println("Box explodes");
        return null;
    }
}
