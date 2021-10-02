package com.utils;

import com.entities.Position;
import com.entities.Tile;

public abstract class Creator {

    public abstract Tile createFactory(String gid, Position position, float dimension);
}