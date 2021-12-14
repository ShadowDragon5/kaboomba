package com.entities.bomb;

import com.core.State;
import com.core.TextureFile;
import com.core.enums.ExplosionDirection;
import com.entities.Rectangle;

import java.awt.*;

import static com.utils.Scheduler.scheduleTask;

public class GreenBomb extends Bomb {

    public GreenBomb(Rectangle position) {
        super(position);
        setLifespan(4000l);
    }

    @Override
    public void explode() {
        handleExplosion();

        scheduleTask(() -> {
            State.getInstance().removeBomb(this);
            handleExplosion();
            return null;
        }, "Explosion_Timer", 2300l);
    }

    @Override
    public Color getColor() {
        return new Color(0.5f,1f,0f);
    }

    @Override
    public String getTextureFile() {
        return TextureFile.BOMB_GREEN;
    }

    @Override
    public BombExplosion createExplosion(Rectangle position, ExplosionDirection direction) {
        return new GreenBombExplosion(position, direction, initiatorId);
    }
}
