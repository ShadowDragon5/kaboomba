package com.entities;

public class SpeedPowerUp extends PowerUp {

    public SpeedPowerUp(Position position) {
        super(position);
    }

    @Override
    public Player decorate(Player player) {
        var newPlayer = new SpeedPowerUpDecorator(player);
        return newPlayer;
    }

    @Override
    public String getTextureFile() {
        return "src/main/resources/powerup_speed.png";
    }

}
