package com.entities.bomb;

import com.core.enums.ExplosionDirection;
import com.core.TextureFile;
import com.entities.Rectangle;

import java.awt.*;

public class BaseBomb extends Bomb {

    public BaseBomb(Rectangle position) {
        super(position.snap());
        setLifespan(4000L);
    }

    @Override
    public Color getColor() {
        return new Color(0f, 0.5f, 1f);
    }

    @Override
    public String getTextureFile() {
        return TextureFile.BOMB;
    }

    @Override
    public BombExplosion createExplosion(Rectangle position, ExplosionDirection direction) {
        if (initiatorId == null)
            return new BaseBombExplosion(position, direction);
        return new BaseBombExplosion(position, direction, initiatorId);
    }
}
