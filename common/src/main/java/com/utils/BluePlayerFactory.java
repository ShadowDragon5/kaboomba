package com.utils;

import com.entities.*;

public class BluePlayerFactory extends PlayersAbstractFactory {
    public BluePlayerFactory(Player player) {
        super(player);
    }

    @Override
    public Bomb createBomb(Player player) {
        BlueBomb blueBomb = new BlueBomb(new Position(player.getPosition().getX(), player.getPosition().getY()));
        blueBomb.initiatorId = player.ID;

        return blueBomb;
    }

    @Override
    public Shield createShield(Player player) {
        BlueShield blueShield = new BlueShield(new Position(player.getPosition().getX(), player.getPosition().getY()));
        blueShield.initiatorId = player.ID;

        return blueShield;
    }

    @Override
    public Pit createPit(Player player) {
        BluePit bluePit = new BluePit(new Position(player.getPosition().getX(), player.getPosition().getY()));
        bluePit.initiatorId = player.ID;

        return bluePit;
    }
}
