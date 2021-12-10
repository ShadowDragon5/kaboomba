package com.entities.bomb;

import com.core.TextureFile;
import com.core.enums.ExplosionDirection;
import com.entities.Rectangle;

public class BlueBombExplosion extends BombExplosion {
    public BlueBombExplosion(Rectangle position, ExplosionDirection direction, String initiatorId) {
        super(position, direction, initiatorId);
    }

    @Override
    protected String getCenterTexture() {
        return TextureFile.BLUE_EXPLOSION_CENTER;
    }

    @Override
    protected String getHorizontalTexture() {
        return TextureFile.BLUE_EXPLOSION_HORIZONTAL;
    }

    @Override
    protected String getVerticalTexture() {
        return TextureFile.BLUE_EXPLOSION_VERTICAL;
    }
}
