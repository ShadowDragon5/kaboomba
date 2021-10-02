package com.utils;

import com.entities.Floor;
import com.entities.Tile;
import com.entities.Position;
import com.entities.Wall;

public class DefaultTileCreator extends TileCreator {

    @Override
    public Tile createFactory(String gid, Position position, float dimension) {
        switch (gid){
            case "1":
                return new Floor(position, dimension);
            case "2":
                return new Wall(position, dimension);
            default:
                return null;
        }
    }
}
