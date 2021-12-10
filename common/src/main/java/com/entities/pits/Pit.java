package com.entities.pits;

import com.core.Defaults;
import com.core.WithLifespan;
import com.entities.GameObject;
import com.entities.Rectangle;
import com.entities.players.Player;

public class Pit extends GameObject implements WithLifespan {

    private Long lifespan = 2000L;

    public Pit(Rectangle position) {
        super(position);
    }

    public void triggerPit(Player player) {}

    @Override
    public String getTextureFile() {
        return Defaults.color;
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
