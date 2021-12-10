package com.entities.bomb;

import com.core.TextureFile;
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
        return TextureFile.EXPLOSION_CENTER;
    }

    @Override
    protected String getHorizontalTexture() {
        return TextureFile.EXPLOSION_HORIZONTAL;
    }

    @Override
    protected String getVerticalTexture() {
        return TextureFile.EXPLOSION_VERTICAL;
    }
}
