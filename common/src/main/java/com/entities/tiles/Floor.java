package com.entities.tiles;

import com.entities.Rectangle;

public class Floor extends Tile{
    public Floor() {
        super();
    }

    public Floor(Rectangle position) {
        super(position);
    }

    public Floor(Rectangle position, float dimension){
        super(position,dimension);
    }

    @Override
    public String getTextureFile() {
        return "src/main/resources/path.png";
    }
}
