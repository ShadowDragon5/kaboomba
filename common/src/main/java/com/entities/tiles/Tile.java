package com.entities.tiles;

import com.entities.GameObject;
import com.entities.Rectangle;

public abstract class Tile extends GameObject {

    public Tile() {
    }

    public Tile(Rectangle position) {
        super(position);
    }

    public Tile(Rectangle position, float dimension){
        super(position, dimension);
    }
}
