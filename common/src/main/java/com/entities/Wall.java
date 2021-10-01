package com.entities;

public class Wall extends Tile {

    public Wall(Position position) {
        super(position);
    }

    @Override
    public String getTextureFile() {
        return "Wall";
    }
}

