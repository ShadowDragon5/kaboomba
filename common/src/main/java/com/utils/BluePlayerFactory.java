package com.utils;

import com.entities.*;

public class BluePlayerFactory extends PlayersAbstractFactory {
    public BluePlayerFactory(Player player) {
        super(player);
    }

    @Override
    public Bomb createBomb(Player player) {
        BlueBomb blueBomb = new BlueBomb(player.getPosition().clone().snap());
        blueBomb.initiatorId = player.ID;

        return blueBomb;
    }

    @Override
    public Shield createShield(Player player) {
        BlueShield blueShield = new BlueShield(player.getPosition().clone().snap());
        blueShield.initiatorId = player.ID;

        return blueShield;
    }

    @Override
    public Pit createPit(Player player) {
        BluePit bluePit = new BluePit(player.getPosition().clone().snap());
        bluePit.initiatorId = player.ID;

        return bluePit;
    }
}
