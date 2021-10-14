package com.entities;

import com.core.WithLifespan;

public class Shield extends GameObject implements WithLifespan {
    private Long lifespan = 1000L;

    public Shield(Position position) {
        super(position);
    }

    public Shield() {

    }

    @Override
    public String getTextureFile() {
        return "src/shield";
    }

    @Override
    public Long getLifespan() {
        return lifespan;
    }
}
