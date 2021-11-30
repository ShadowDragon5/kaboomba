package com.entities.bomb;

import com.core.enums.ExplosionDirection;
import com.entities.Rectangle;

public class GreenBombExplosion extends BombExplosion {

    public GreenBombExplosion(Rectangle position, ExplosionDirection direction, String initiatorId) {
        super(position, direction, initiatorId);
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
