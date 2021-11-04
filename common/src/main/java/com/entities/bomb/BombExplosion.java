package com.entities.bomb;

import com.core.enums.ExplosionDirection;
import com.core.WithLifespan;
import com.entities.GameObject;
import com.entities.Position;

public abstract class BombExplosion extends GameObject implements WithLifespan {

    private ExplosionDirection direction;

    public BombExplosion (Position position, ExplosionDirection direction) {
        super(position);
        this.direction = direction;
    }

    public BombExplosion (Position position, ExplosionDirection direction, String initiatorId) {
        super(position);
        this.direction = direction;
        this.initiatorId = initiatorId;
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
