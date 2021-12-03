package com.factories.tile;

import com.entities.*;
import com.entities.portals.RandomPortal;
import com.entities.portals.WaypointPortal;
import com.entities.tiles.Box;
import com.entities.tiles.Floor;
import com.entities.tiles.Tile;
import com.entities.tiles.Wall;

public class DefaultTileCreator extends TileCreator {

    @Override
    public Tile createTile(String gid, Rectangle rectangle) {
        switch (gid) {
            case "1":
                return new Floor(rectangle);
            case "2":
                return new Wall(rectangle);
            case "3":
                return new Box.BoxBuilder(rectangle).build();
            case "4":
                return new WaypointPortal(rectangle);
            case "5":
                return new RandomPortal(rectangle);
            default:
                return null;
        }
    }
}
