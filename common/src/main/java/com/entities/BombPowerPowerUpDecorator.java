package com.entities;

import com.utils.PlayersAbstractFactory;

public class BombPowerPowerUpDecorator extends PowerUpDecorator {

    public BombPowerPowerUpDecorator(Player player) {
        super(player);
    }

    public int getBombPower() {
        return player.getBombPower() + 1;
    }

    @Override
    public PlayersAbstractFactory getFactory() {
        return player.getFactory();
    }
}
