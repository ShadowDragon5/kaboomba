package com.entities.bomb;

import com.core.TextureFile;
import com.core.enums.ExplosionDirection;
import com.entities.Rectangle;

public class GreenBombExplosion extends BombExplosion {

    public GreenBombExplosion(Rectangle position, ExplosionDirection direction, String initiatorId) {
        super(position, direction, initiatorId);
    }

    @Override
    protected String getCenterTexture() {
        return TextureFile.GREEN_EXPLOSION_CENTER;
    }

    @Override
    protected String getHorizontalTexture() {
        return TextureFile.GREEN_EXPLOSION_HORIZONTAL;
    }

    @Override
    protected String getVerticalTexture() {
        return TextureFile.GREEN_EXPLOSION_VERTICAL;
    }
}
