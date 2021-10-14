package com.utils;

import com.entities.*;

public class DamageBox extends BoxExplosion {
    public DamageBox() {}

    @Override
    public GameObject explosionEffect() {
        System.out.println("Box is only damaged");
        return new DamagedBox();
    }
}
