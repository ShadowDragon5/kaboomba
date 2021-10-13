package com.entities;

import com.core.WithLifespan;

public class Bomb extends GameObject implements WithLifespan {
    private Long lifespan = 2000L;

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
}
