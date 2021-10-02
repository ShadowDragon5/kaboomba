package com.entities;

public class Wall extends Tile {

    public Wall(Position position) {
        super(position);
    }

    public Wall(Position position, float dimension){
        super(position,dimension);
    }
    @Override
    public String getTextureFile() {
        return "src/main/resources/wall.png";
    }
}

