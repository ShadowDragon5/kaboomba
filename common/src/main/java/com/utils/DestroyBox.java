package com.utils;

public class DestroyBox extends BoxExplosion {
    public DestroyBox() {}

    @Override
    public void explosionEffect() {
        System.out.println("Box explodes");
    }
}
