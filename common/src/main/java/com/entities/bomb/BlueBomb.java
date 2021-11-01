package com.entities.bomb;

import com.core.State;
import com.core.enums.ExplosionDirection;
import com.entities.Position;

import java.awt.*;

import static com.utils.Scheduler.scheduleTask;

public class BlueBomb extends Bomb {
    public BlueBomb(Position position) {
        super(position);
    }

    @Override
    public Long getLifespan() {
        return 1500l;
    }

    @Override
    public Color getColor(){
        return new Color(0f,0.5f,1f);
    }

    @Override
    public String getTextureFile() {
        return "src/main/resources/bomb_blue.png";
    }

    @Override
    public BombExplosion createExplosion(Position position, ExplosionDirection direction) {
        return new BlueBombExplosion(position, direction);
    }
}
