package com.entities.players;

import com.factories.player.PlayersAbstractFactory;

public class NullPlayer extends Player {
    public NullPlayer(String id) {
        this.ID = id;
    }

    public NullPlayer() {
    }

    @Override
    public boolean isDead() {
        return true;
    }

    @Override
    public PlayersAbstractFactory getFactory() {
        return null;
    }

    @Override
    public Player clone() {
        return null;
    }
}
