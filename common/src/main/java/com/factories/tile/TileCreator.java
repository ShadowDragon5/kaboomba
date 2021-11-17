package com.factories.tile;

import com.entities.Position;
import com.entities.tiles.Tile;

public abstract class TileCreator {
    public abstract Tile createTile(String gid, Position position, float dimension);
}
