package com.entities;

import com.utils.PlayersAbstractFactory;

public class BombAmmoPowerUpDecorator extends PowerUpDecorator{
    public BombAmmoPowerUpDecorator(Player player) {
        super(player);
    }

    public int getBombPower() {
        return player.getBombAmmo() + 1;
    }

    @Override
    public PlayersAbstractFactory getFactory() {
        return player.getFactory();
    }
}
