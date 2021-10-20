package com.entities;

import com.utils.PlayersAbstractFactory;

public class BombAmmoPowerUpDecorator extends PowerUpDecorator{
    public BombAmmoPowerUpDecorator(Player player) {
        super(player);
    }

    @Override
    public int getBombAmmo() {
        return player.getBombAmmo() + 1;
    }
}
