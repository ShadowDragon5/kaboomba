package com.entities;

import com.utils.PlayersAbstractFactory;

public class SpeedPowerUpDecorator extends PowerUpDecorator {
    public SpeedPowerUpDecorator(Player player) {
        super(player);
    }

    @Override
    public float getSpeed() {
        return player.getSpeed() + 0.005f;
    }

    @Override
    public PlayersAbstractFactory getFactory() {
        return player.getFactory();
    }
}
