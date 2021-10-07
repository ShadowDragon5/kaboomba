package com.testPatterns;

import com.entities.Position;
import com.entities.Tile;
import com.utils.DefaultTileCreator;
import com.utils.TileCreator;

public class FactoryTest {
    public static void main(String[] args){
        TileCreator tileCreator = new DefaultTileCreator();

        Tile wallTile = tileCreator.createTile("1",new Position(), 0.1f);
        Tile floorTile = tileCreator.createTile("2",new Position(), 0.1f);
        Tile boxTile = tileCreator.createTile("3",new Position(), 0.1f);

        System.out.println("Tile creator created " + wallTile.getClass() + " when gid=1");
        System.out.println("Tile creator created " + floorTile.getClass() + " when gid=2");
        System.out.println("Tile creator created " + boxTile.getClass() + " when gid=3");

    }
}
