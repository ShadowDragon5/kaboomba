package com.utils;

import com.entities.Floor;
import com.entities.Tile;
import com.entities.Position;
import com.entities.Wall;

public class TileCreator extends Creator {

    @Override
    public Tile createFactory(String gid, Position position, float dimension) {
        switch (gid){
            case "1":
                return new Floor(position);
            case "2":
                return new Wall(position);
            default:
                return null;
        }
    }
}
