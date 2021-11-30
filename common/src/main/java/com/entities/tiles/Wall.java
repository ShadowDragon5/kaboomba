package com.entities.tiles;

import com.entities.Rectangle;

public class Wall extends Tile {

    public Wall(){}
    public Wall(Rectangle position) {
        super(position);
    }

    public Wall(Rectangle position, float dimension){
        super(position,dimension);
    }
    @Override
    public String getTextureFile() {
        return "src/main/resources/wall.png";
    }
}

