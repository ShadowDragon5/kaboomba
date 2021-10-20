package com.utils;

import com.core.State;
import com.entities.Box;
import com.entities.GameObject;

public class DestroyBox extends BoxExplosion {
    public DestroyBox() {}

    @Override
    public void explosionEffect(Box box) {
        State.getInstance().removeBox(box);
    }
}
