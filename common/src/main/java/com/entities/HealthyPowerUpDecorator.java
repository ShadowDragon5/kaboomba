package com.entities;

import com.utils.PlayersAbstractFactory;

public class HealthyPowerUpDecorator extends PowerUpDecorator{
    public HealthyPowerUpDecorator(Player player) {
        super(player);
    }

    public float getHealth() {
        return super.getHealth() + 1;
    }

    @Override
    public PlayersAbstractFactory getFactory() {
        return player.getFactory();
    }
}
