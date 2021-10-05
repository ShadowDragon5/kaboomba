package com.entities;

public class Bomb extends GameObject{
    public Bomb(Position position) {
        super(position);
    }

    @Override
    public String getTextureFile() {
        return "src/resources/bomb";
    }

}
