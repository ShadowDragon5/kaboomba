package com.entities;

import com.core.WithLifespan;

public abstract class BombExplosion extends GameObject implements WithLifespan {
    @Override
    public Long getLifespan() {
        return 2000L;
    }

    @Override
    public String getTextureFile() {
        return null;
    }

    public BombExplosion(Position position){
        super(position);
    }
}
