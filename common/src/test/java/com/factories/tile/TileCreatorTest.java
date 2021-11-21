package com.factories.tile;

import com.entities.Position;
import com.entities.tiles.Tile;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TileCreatorTest {
    TileCreator tileCreator = new DefaultTileCreator();
    Tile floorTile = tileCreator.createTile("1", new Position(), 0.1f);
    Tile wallTile = tileCreator.createTile("2", new Position(), 0.1f);
    Tile boxTile = tileCreator.createTile("3", new Position(), 0.1f);
    Tile waypointPortal = tileCreator.createTile("4", new Position(), 0.1f);
    Tile randomPortal = tileCreator.createTile("5", new Position(), 0.1f);


    @Test
    void shouldReturnFloorTile() {
        Assertions.assertEquals(floorTile.getTextureFile(), "src/main/resources/path.png");
    }

    @Test
    void shouldReturnWallTile() {
        Assertions.assertEquals(wallTile.getTextureFile(), "src/main/resources/wall.png");
    }

    @Test
    void shouldReturnBoxTile() {
        Assertions.assertEquals(boxTile.getTextureFile(), "src/main/resources/box.png");
    }

    @Test
    void shouldReturnWaypointTile() {
        Assertions.assertEquals(waypointPortal.getTextureFile(), "src/main/resources/portal_waypoint.png");
    }

    @Test
    void shouldReturnRandomTile() {
        Assertions.assertEquals(randomPortal.getTextureFile(), "src/main/resources/portal_random.png");
    }
}
