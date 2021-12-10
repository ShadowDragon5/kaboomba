package com.entities.powerups;

import com.entities.players.Player;
import com.core.TextureFile;
import com.entities.Rectangle;

public class BombPowerPowerUp extends PowerUp {

    public BombPowerPowerUp(Rectangle position) {
        super(position);
    }

    @Override
    public Player decorate(Player player) {
        return new BombPowerPowerUpDecorator(player);
    }

    @Override
    public String getTextureFile() {
        return TextureFile.POWERUP_EXPLOSION;
    }

}
