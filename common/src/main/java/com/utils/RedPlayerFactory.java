package com.utils;

import com.entities.*;

public class RedPlayerFactory extends PlayersAbstractFactory {
    @Override
    public Bomb createBomb(Position position) {
        return new RedBomb(position);
    }

    @Override
    public Bomb createBomb(Player player) {
        RedBomb redBomb = new RedBomb(new Position(player.getPosition().getX(), player.getPosition().getY()));
        redBomb.initiatorId = player.ID;

        return redBomb;
    }
}
