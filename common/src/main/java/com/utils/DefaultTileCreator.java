package com.utils;

import com.entities.*;

public class DefaultTileCreator extends TileCreator {

    @Override
    public Tile createTile(String gid, Position position, float dimension) {
        switch (gid){
            case "1":
                return new Floor(position, dimension);
            case "2":
                return new Wall(position, dimension);
            case "3":
                return new Box(position, dimension);
            default:
                return null;
        }
    }
}
