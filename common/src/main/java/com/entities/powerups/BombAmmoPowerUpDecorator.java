package com.entities.powerups;

import com.entities.players.Player;

public class BombAmmoPowerUpDecorator extends PowerUpDecorator {
    public BombAmmoPowerUpDecorator(Player player) {
        super(player);
    }

    @Override
    public Player clone() {
        return new BombAmmoPowerUpDecorator(this);
    }

    @Override
    public int getBombAmmo() {
        return player.getBombAmmo() + 1;
    }
}
