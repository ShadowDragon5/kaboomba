package com.entities;

import com.core.ExplosionDirection;
import com.core.WithLifespan;

public abstract class BombExplosion extends GameObject implements WithLifespan {
    private ExplosionDirection direction;
    public BombExplosion (Position position, ExplosionDirection direction) {
        super(position);
        this.direction = direction;
    }
    @Override
    public Long getLifespan() {
        return 2000L;
    }

    @Override
    public String getTextureFile() {
        switch (this.direction) {
            case CENTER:
                return getCenterTexture();
            case HORIZONTAL:
                return getHorizontalTexture();
            case VERTICAL:
                return getVerticalTexture();
            default:
                return getCenterTexture();
        }
    }

    protected abstract String getCenterTexture();
    protected abstract String getHorizontalTexture();
    protected abstract String getVerticalTexture();

    public BombExplosion(Position position){
        super(position);
    }
}
