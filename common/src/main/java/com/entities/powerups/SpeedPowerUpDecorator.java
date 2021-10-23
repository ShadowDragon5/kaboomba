package com.entities.powerups;

import com.entities.players.Player;

public class SpeedPowerUpDecorator extends PowerUpDecorator {
    public SpeedPowerUpDecorator(Player player) {
        super(player);
    }

    @Override
    public Player clone() {
        return new SpeedPowerUpDecorator(this);
    }

    @Override
    public float getSpeed() {
        return player.getSpeed() + 0.005f;
    }
}
