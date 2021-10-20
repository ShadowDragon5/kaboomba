package com.entities;

public class HealthyPowerUp extends PowerUp {

    public HealthyPowerUp(Position position) {
        super(position);
    }

    @Override
    public Player decorate(Player player) {
        return new HealthyPowerUpDecorator(player);
    }

    @Override
    public String getTextureFile() {
        return "src/main/resources/powerup_health.png";
    }

}
