package com.entities.powerups;

import com.entities.players.Player;
import com.factories.player.PlayersAbstractFactory;

public abstract class PowerUpDecorator extends Player {
    protected Player player;

    public PowerUpDecorator(Player player) {
        super(player);
        this.player = player;
    }

    @Override
    public int getBombPower() {
        return player.getBombPower();
    }

    @Override
    public int getHealth() {
        return player.getHealth();
    }

    @Override
    public int getBombAmmo() {
        return player.getBombAmmo();
    }

    @Override
    public float getSpeed() {
        return player.getSpeed();
    }

    @Override
    public String getTextureFile() {
        return player.getTextureFile();
    }

    @Override
    public PlayersAbstractFactory getFactory() {
        return player.getFactory();
    }

}
