package com.entities;

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
