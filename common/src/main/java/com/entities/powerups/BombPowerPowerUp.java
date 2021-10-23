package com.entities.powerups;

import com.entities.players.Player;
import com.entities.Position;

public class BombPowerPowerUp extends PowerUp {

    public BombPowerPowerUp(Position position) {
        super(position);
    }

    @Override
    public Player decorate(Player player) {
        return new BombPowerPowerUpDecorator(player);
    }

    @Override
    public String getTextureFile() {
        return "src/main/resources/powerup_explosion.png";
    }

}
