package com.factories.tile;

import com.entities.Rectangle;
import com.entities.tiles.Tile;

public abstract class TileCreator {
    public abstract Tile createTile(String gid, Rectangle position, float dimension);
}
