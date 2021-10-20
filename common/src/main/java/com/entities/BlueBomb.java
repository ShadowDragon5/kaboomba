package com.entities;

import com.core.ExplosionDirection;

import java.awt.*;

public class BlueBomb extends Bomb{
    public BlueBomb(Position position) {
        super(position);
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
    public BombExplosion createExplosion(Position position, ExplosionDirection direction) {
        return new BlueBombExplosion(position, direction);
    }
}
