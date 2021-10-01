package com.utils;

import com.entities.*;

public abstract class BoxCreator extends TileCreator {

    @Override
    public Box createTile(Position position) {
        return new Box(position);
    }

}
