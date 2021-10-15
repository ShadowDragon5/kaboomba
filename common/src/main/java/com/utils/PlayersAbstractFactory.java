package com.utils;

import com.entities.Bomb;
import com.entities.Pit;
import com.entities.Player;
import com.entities.Shield;

public abstract class PlayersAbstractFactory {

    private final Player player;

    public PlayersAbstractFactory(Player player) {
        this.player = player;
    }

    abstract public Bomb createBomb(Player player);

    abstract public Shield createShield(Player player);

    abstract public Pit createPit(Player player);

    public Player getPlayer() {
        return player;
    }
}
