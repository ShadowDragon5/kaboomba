package com.entities;

import com.utils.PlayersAbstractFactory;

public class HealthyPowerUpDecorator extends PowerUpDecorator {
    public HealthyPowerUpDecorator(Player player) {
        super(player);
    }

    @Override
    public Player clone() {
        return new HealthyPowerUpDecorator(this);
    }

    @Override
    public int getHealth() {
        return player.getHealth() + 1;
    }
}
