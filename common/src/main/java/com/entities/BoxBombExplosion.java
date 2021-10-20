package com.entities;

import com.core.ExplosionDirection;

public class BoxBombExplosion extends BombExplosion{
    public BoxBombExplosion(Position position, ExplosionDirection direction) {
        super(position, direction);
    }

    @Override
    protected String getCenterTexture() {
        return "src/main/resources/explosion_center.png";
    }

    @Override
    protected String getHorizontalTexture() {
        return "src/main/resources/explosion_horizontal.png";
    }

    @Override
    protected String getVerticalTexture() {
        return "src/main/resources/explosion_vertical.png";
    }
}
