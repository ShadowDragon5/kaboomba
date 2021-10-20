package com.entities;

import com.utils.PlayersAbstractFactory;

public class BombPowerPowerUpDecorator extends PowerUpDecorator {

    public BombPowerPowerUpDecorator(Player player) {
        super(player);
    }

    @Override
    public Player clone() {
        return new BombPowerPowerUpDecorator(this);
    }

    @Override
    public int getBombPower() {
        return player.getBombPower() + 1;
    }
}
