package com.utils;

import com.entities.Tile;
import com.entities.Position;

public abstract class TileCreator {

    /*
     * @position - world coordinates
     */
    public abstract Tile createTile(Position position);
}
