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
        GreenShield greenShield = new GreenShield(new Position(player.getPosition().getX(), player.getPosition().getY()));
        greenShield.initiatorId = player.ID;

        return greenShield;
    }

    @Override
    public Pit createPit(Player player) {
        GreenPit greenPit = new GreenPit(new Position(player.getPosition().getX(), player.getPosition().getY()));
        greenPit.initiatorId = player.ID;

        return greenPit;
    }
}
