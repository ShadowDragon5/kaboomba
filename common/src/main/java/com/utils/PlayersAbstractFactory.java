package com.utils;

import com.entities.Bomb;
import com.entities.Pit;
import com.entities.Player;
import com.entities.Shield;

public abstract class PlayersAbstractFactory {

    abstract public Bomb createBomb(Player player);
    abstract public Shield createShield(Player player);
    abstract public Pit createPit(Player player);

}
