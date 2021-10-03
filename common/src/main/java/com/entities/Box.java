package com.entities;

import com.utils.BoxExplodable;

public class Box extends Tile {

    public Box(Position position) {
        super(position);
    }

    private BoxExplodable boxExplodable;

    public BoxExplodable getBoxExplodable() {
        return boxExplodable;
    }

    public void setBoxExplodable(BoxExplodable boxExplodable) {
        this.boxExplodable = boxExplodable;
    }

    public void onBoxDestroyed(){
        boxExplodable.explode();
    }

    @Override
    public String getTextureFile() {
        return "src/box";
    }
}
