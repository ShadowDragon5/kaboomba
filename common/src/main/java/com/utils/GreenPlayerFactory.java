package com.utils;

import com.entities.*;

public class GreenPlayerFactory extends PlayersAbstractFactory{
    @Override
    public Bomb createBomb(Player player) {
        GreenBomb greenBomb = new GreenBomb(new Position(player.getPosition().getX(), player.getPosition().getY()));
        greenBomb.initiatorId = player.ID;

        return greenBomb;
    }

    @Override
    public Shield createShield(Player player) {
        return null;
    }

    @Override
    public Pit createPit(Player player) {
        return null;
    }
}
