package com.entities;

import java.awt.*;

public class GreenBomb extends Bomb{
    public GreenBomb(){

    }
    public GreenBomb(Position position) {
        super(position);
    }

    @Override
    public Color getColor() {
        return new Color(0.5f,1f,0f);
    }

    @Override
    public String getTextureFile() {
        return "src/main/resources/bomb_green.png";
    }

    @Override
    public BombExplosion createExplosion(Position position) {
        return new GreenBombExplosion(position);
    }
}
