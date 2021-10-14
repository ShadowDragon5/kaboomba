package com.entities;

import com.utils.PlayersAbstractFactory;

public class BombPowerPowerUp extends PowerUpDecorator {

    public BombPowerPowerUp(Player player) {
        super(player);
    }

    public float getBombPower() {
        return super.getBombPower() + 1;
    }

    @Override
    public PlayersAbstractFactory getFactory() {
        return player.getFactory();
    }
}
