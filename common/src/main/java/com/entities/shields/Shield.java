package com.entities.shields;

import com.core.WithLifespan;
import com.entities.GameObject;
import com.entities.Position;

public class Shield extends GameObject implements WithLifespan {
    private Long lifespan = 3000L;

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

    @Override
    public void setLifespan(Long lifespan) {
        this.lifespan = lifespan;
    }
}
