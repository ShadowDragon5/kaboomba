package com.entities.powerups;

import com.entities.players.Player;
import com.entities.visitor.IVisitor;
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
    public void accept(IVisitor v) {
        v.visitSpeedPowerUp(this);
    }

    @Override
    public String getTextureFile() {
        return TextureFile.POWERUP_SPEED;
    }

}
