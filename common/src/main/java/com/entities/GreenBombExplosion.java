package com.entities;

public class GreenBombExplosion extends BombExplosion {

    public GreenBombExplosion(Position position) {
        super(position);
    }

    @Override
    public String getTextureFile() {
        return "src/main/resources/green_explosion_center.png";
    }
}
