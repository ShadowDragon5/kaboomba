package com.utils;

public class DamageBox extends BoxExplosion {
    public DamageBox() {}

    @Override
    public void explosionEffect() {
        System.out.println("Box is only damaged");
    }
}
