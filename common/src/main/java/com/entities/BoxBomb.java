package com.entities;

import java.awt.*;

public class BoxBomb extends Bomb {

    public BoxBomb() {

    }

    public BoxBomb(Position position) {
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
    public BombExplosion createExplosion(Position position) {
        return new BoxBombExplosion(position);
    }

    @Override
    public Long getLifespan() {
        return 4000L;
    }
}
