package com.utils;

import com.entities.Bomb;
import com.entities.Player;
import com.entities.Position;

public abstract class PlayersAbstractFactory {

    abstract public Bomb createBomb(Position position);
    abstract public Bomb createBomb(Player player);
}
