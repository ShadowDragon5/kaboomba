package com.entities.bomb;

import com.core.enums.ExplosionDirection;
import com.entities.Position;
import com.entities.bomb.BombExplosion;

public class GreenBombExplosion extends BombExplosion {

    public GreenBombExplosion(Position position, ExplosionDirection direction) {
        super(position, direction);
    }

    @Override
    protected String getCenterTexture() {
        return "src/main/resources/green_explosion_center.png";
    }

    @Override
    protected String getHorizontalTexture() {
        return "src/main/resources/green_explosion_horizontal.png";
    }

    @Override
    protected String getVerticalTexture() {
        return "src/main/resources/green_explosion_vertical.png";
    }
}
