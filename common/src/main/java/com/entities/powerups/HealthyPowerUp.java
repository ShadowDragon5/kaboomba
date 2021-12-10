package com.entities.powerups;

import com.entities.players.Player;
import com.core.TextureFile;
import com.entities.Rectangle;

public class HealthyPowerUp extends PowerUp {

    public HealthyPowerUp(Rectangle position) {
        super(position);
    }

    @Override
    public Player decorate(Player player) {
        return new HealthyPowerUpDecorator(player);
    }

    @Override
    public String getTextureFile() {
        return TextureFile.POWERUP_HEALTH;
    }

}
