package com.utils;

import com.entities.*;

public class GreenPlayerFactory extends PlayersAbstractFactory{

    @Override
    public Bomb createBomb(Position position) {
        return new GreenBomb(position);
    }

    @Override
    public Bomb createBomb(Player player) {
        GreenBomb greenBomb = new GreenBomb(new Position(player.getPosition().getX(), player.getPosition().getY()));
        greenBomb.initiatorId = player.ID;

        return greenBomb;
    }
}
