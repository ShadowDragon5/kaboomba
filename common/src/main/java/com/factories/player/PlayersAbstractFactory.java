package com.factories.player;

import com.entities.bomb.Bomb;
import com.entities.pits.Pit;
import com.entities.players.Player;
import com.entities.shields.Shield;

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
