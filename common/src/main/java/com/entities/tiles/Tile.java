package com.entities.tiles;

import com.entities.GameObject;
import com.entities.Rectangle;

public abstract class Tile extends GameObject {

    public Tile() {
    }

    public Tile(Rectangle rectangle) {
        super(rectangle);
    }
}
