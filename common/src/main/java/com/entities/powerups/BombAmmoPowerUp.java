package com.entities.powerups;

import com.entities.players.Player;
import com.entities.Position;

public class BombAmmoPowerUp extends PowerUp {

    public BombAmmoPowerUp(Position position) {
        super(position);
    }

    @Override
    public Player decorate(Player player) {
        return new BombAmmoPowerUpDecorator(player);
    }

    @Override
    public String getTextureFile() {
        return "src/main/resources/powerup_ammo.png";
    }

}
