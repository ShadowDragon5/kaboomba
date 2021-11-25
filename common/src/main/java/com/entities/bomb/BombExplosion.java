package com.entities.bomb;

import com.core.enums.ExplosionDirection;
import com.core.WithLifespan;
import com.entities.GameObject;
import com.entities.Position;

public abstract class BombExplosion extends GameObject implements WithLifespan {

    private ExplosionDirection direction;
    protected Long lifespan = 2000L; 

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
        return lifespan;
    }

    @Override
    public void setLifespan(Long lifespan) {
        this.lifespan = lifespan;
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
