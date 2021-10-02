package com.entities;

public class Box extends Tile {

    public Box(Position position) {
        super(position);
    }

    @Override
    public String getTextureFile() {
        return "src/box";
    }
}
