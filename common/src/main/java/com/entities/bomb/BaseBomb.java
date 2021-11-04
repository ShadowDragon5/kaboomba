package com.entities.bomb;

import com.core.enums.ExplosionDirection;
import com.entities.Position;

import java.awt.*;

public class BaseBomb extends Bomb {

    public BaseBomb() {

    }

    public BaseBomb(Position position) {
        super(position.snap());
    }

    @Override
    public Color getColor() {
        return new Color(0f, 0.5f, 1f);
    }

    @Override
    public String getTextureFile() {
        return "src/main/resources/bomb.png";
    }

    @Override
    public BombExplosion createExplosion(Position position, ExplosionDirection direction) {
        return new BaseBombExplosion(position, direction);
    }

    @Override
    public Long getLifespan() {
        return 4000L;
    }
}
