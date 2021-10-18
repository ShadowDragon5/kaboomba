package com.entities;

public class BoxBombExplosion extends BombExplosion{
    public BoxBombExplosion(Position position) {
        super(position);
    }

    @Override
    public String getTextureFile() {
        return "src/main/resources/explosion_center.png";
    }
}
