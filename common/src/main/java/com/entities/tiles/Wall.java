package com.entities.tiles;

import com.entities.Position;

public class Wall extends Tile {

    public Wall(){}
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

