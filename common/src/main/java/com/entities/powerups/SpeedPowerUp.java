package com.entities.powerups;

import com.entities.players.Player;
import com.core.TextureFile;
import com.entities.Rectangle;

public class SpeedPowerUp extends PowerUp {

    public SpeedPowerUp(Rectangle position) {
        super(position);
    }

    @Override
    public Player decorate(Player player) {
        return new SpeedPowerUpDecorator(player);
    }

    @Override
    public String getTextureFile() {
        return TextureFile.POWERUP_SPEED;
    }

}
