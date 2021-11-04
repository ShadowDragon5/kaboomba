package com.entities.pits;

import com.core.WithLifespan;
import com.entities.GameObject;
import com.entities.Position;
import com.entities.players.Player;

public class Pit extends GameObject implements WithLifespan {
    private Long lifespan = 2000L;
    public Pit(Position position) {
        super(position);
    }

    public void triggerPit(Player player) {}

    @Override
    public String getTextureFile() {
        return "src/pit";
    }

    @Override
    public Long getLifespan() {
        return lifespan;
    }
}
