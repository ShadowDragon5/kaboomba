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
    public Tile createTile(String gid, Rectangle position, float dimension) {
        switch (gid) {
            case "1":
                return new Floor(position, dimension);
            case "2":
                return new Wall(position, dimension);
            case "3":
                return new Box.BoxBuilder(position).dimension(dimension).build();
            case "4":
                return new WaypointPortal(position);
            case "5":
                return new RandomPortal(position);
            default:
                return null;
        }
    }
}
