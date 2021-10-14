package com.entities;

import com.utils.PlayersAbstractFactory;

public class SpeedPowerUp extends PowerUpDecorator {


    public SpeedPowerUp(Player player) {
        super(player);
    }

    public float getSpeed() {
        return super.getSpeed() + 0.01f;
    }

    @Override
    public PlayersAbstractFactory getFactory() {
        return player.getFactory();
    }
}
