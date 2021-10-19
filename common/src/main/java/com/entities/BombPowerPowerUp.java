package com.entities;

public class BombPowerPowerUp extends PowerUp {

    public BombPowerPowerUp(Position position) {
        super(position);
    }

    @Override
    public Player decorate(Player player) {
        var newPlayer = new BombPowerPowerUpDecorator(player);
        return newPlayer;
    }

    @Override
    public String getTextureFile() {
        return "src/main/resources/powerup_speed.png";
    }

}
