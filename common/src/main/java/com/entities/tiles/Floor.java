package com.entities.tiles;

import com.entities.Rectangle;

public class Floor extends Tile {
    public Floor() {
        super();
    }

    public Floor(Rectangle rectangle) {
        super(rectangle);
    }

    @Override
    public String getTextureFile() {
        return "src/main/resources/path.png";
    }
}
