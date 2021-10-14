package com.entities;

import com.utils.PlayersAbstractFactory;

public class BombAmmoPowerUp extends PowerUpDecorator{
    public BombAmmoPowerUp(Player player) {
        super(player);
    }

    public float getBombPower() {
        return super.getBombAmmo() + 1;
    }

    @Override
    public PlayersAbstractFactory getFactory() {
        return player.getFactory();
    }
}
