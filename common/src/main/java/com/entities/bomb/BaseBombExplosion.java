package com.entities.bomb;

import com.core.enums.ExplosionDirection;
import com.entities.Rectangle;

public class BaseBombExplosion extends BombExplosion {
    public BaseBombExplosion(Rectangle position, ExplosionDirection direction) {
        super(position, direction);
    }

    public BaseBombExplosion(Rectangle position, ExplosionDirection direction, String initiatorId) {
        super(position, direction, initiatorId);
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
