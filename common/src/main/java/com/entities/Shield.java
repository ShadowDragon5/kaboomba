package com.entities;

public class Shield extends GameObject{

    public Shield(Position position) {
        super(position);
    }

    public Shield() {

    }

    @Override
    public String getTextureFile() {
        return "src/shield";
    }
}
