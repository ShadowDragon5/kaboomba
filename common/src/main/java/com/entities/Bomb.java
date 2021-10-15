package com.entities;

import com.core.WithLifespan;


public abstract class Bomb extends GameObject implements WithLifespan {
    private final Long lifespan = 2000L;

    public Bomb() {

    }

    public Bomb(Position position) {
        super(position);
    }

    @Override
    public String getTextureFile() {
        return "src/main/resources/bomb.png";
    }

    @Override
    public Long getLifespan() {
        return lifespan;
    }

    public abstract BombExplosion createExplosion(Position position);
}
