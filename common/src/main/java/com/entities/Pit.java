package com.entities;

import com.core.WithLifespan;

public class Pit extends GameObject implements WithLifespan {
    private Long lifespan = 2000L;
    public Pit(Position position) {
        super(position);
    }

    @Override
    public String getTextureFile() {
        return "src/pit";
    }

    @Override
    public Long getLifespan() {
        return lifespan;
    }
}
