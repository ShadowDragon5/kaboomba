package com.entities.tiles;

import com.core.TextureFile;
import com.entities.Rectangle;

public class Wall extends Tile {

    public Wall() {}

    public Wall(Rectangle rectangle) {
        super(rectangle);
    }

    @Override
    public String getTextureFile() {
        return TextureFile.WALL;
    }
}

