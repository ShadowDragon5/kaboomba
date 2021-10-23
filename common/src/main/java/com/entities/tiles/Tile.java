package com.entities.tiles;

import com.entities.GameObject;
import com.entities.Position;

public abstract class Tile extends GameObject {

    public Tile() {
    }

    public Tile(Position position) {
        super(position);
    }

    public Tile(Position position, float dimension){
        super(position, dimension);
    }
}
