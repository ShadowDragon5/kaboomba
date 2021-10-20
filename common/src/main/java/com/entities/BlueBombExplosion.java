package com.entities;

import com.core.ExplosionDirection;

public class BlueBombExplosion extends BombExplosion {
    public BlueBombExplosion(Position position, ExplosionDirection direction) {
        super(position, direction);
    }

    @Override
    protected String getCenterTexture() {
        return "src/main/resources/blue_explosion_center.png";
    }

    @Override
    protected String getHorizontalTexture() {
        return "src/main/resources/blue_explosion_horizontal.png";
    }

    @Override
    protected String getVerticalTexture() {
        return "src/main/resources/blue_explosion_vertical.png";
    }
}
