package com.strategies.box;

import com.entities.tiles.Box;

public class DestroyBox extends BoxExplosion {
    public DestroyBox() {}

    @Override
    public void explosionEffect(Box box) {
//        State.getInstance().removeBox(box);
        System.out.println("Box is destroyed");
    }
}
