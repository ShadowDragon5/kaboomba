package com.entities.bomb;

import com.core.enums.ExplosionDirection;
import com.entities.Rectangle;

import java.awt.*;

public class BlueBomb extends Bomb {

    public BlueBomb(Rectangle position) {
        super(position);
        setLifespan(1500l);
    }

    @Override
    public Color getColor(){
        return new Color(0f,0.5f,1f);
    }

    @Override
    public String getTextureFile() {
        return "src/main/resources/bomb_blue.png";
    }

    @Override
    public BombExplosion createExplosion(Rectangle position, ExplosionDirection direction) {
        return new BlueBombExplosion(position, direction, initiatorId);
    }
}
