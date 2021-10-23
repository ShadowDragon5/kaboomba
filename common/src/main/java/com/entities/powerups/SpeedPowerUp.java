package com.entities.powerups;

import com.entities.players.Player;
import com.entities.Position;

public class SpeedPowerUp extends PowerUp {

    public SpeedPowerUp(Position position) {
        super(position);
    }

    @Override
    public Player decorate(Player player) {
        return new SpeedPowerUpDecorator(player);
    }

    @Override
    public String getTextureFile() {
        return "src/main/resources/powerup_speed.png";
    }

}
