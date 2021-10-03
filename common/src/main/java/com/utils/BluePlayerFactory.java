package com.utils;

import com.entities.*;

public class BluePlayerFactory extends PlayersAbstractFactory {
    @Override
    public Bomb createBomb(Player player) {
        BlueBomb blueBomb = new BlueBomb(new Position(player.getPosition().getX(), player.getPosition().getY()));
        blueBomb.initiatorId = player.ID;

        return blueBomb;
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
