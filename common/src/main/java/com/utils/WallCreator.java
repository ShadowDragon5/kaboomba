package com.utils;

import com.entities.*;

public abstract class WallCreator extends TileCreator {

    @Override
    public Wall createTile(Position position) {
        return new Wall(position);
    }

}

