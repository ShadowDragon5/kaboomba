package com.entities;

public class Pit extends GameObject {
    public Pit(Position position) {
        super(position);
    }

    @Override
    public String getTextureFile() {
        return "src/pit";
    }
}
