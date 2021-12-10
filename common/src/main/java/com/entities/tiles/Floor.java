package com.entities.tiles;

import com.core.TextureFile;
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
        return TextureFile.PATH;
    }
}
