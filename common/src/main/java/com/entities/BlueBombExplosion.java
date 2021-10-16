package com.entities;

public class BlueBombExplosion extends BombExplosion{
    public BlueBombExplosion(Position position) {
        super(position);
    }

    @Override
    public String getTextureFile() {
        return "src/main/resources/blue_explosion_center.png";
    }
}
