package com.entities;

public class Floor extends Tile{
    public Floor() {
        super();
    }

    public Floor(Position position) {
        super(position);
    }

    public Floor(Position position, float dimension){
        super(position,dimension);
    }

    @Override
    public String getTextureFile() {
        return "floor";
    }
}
