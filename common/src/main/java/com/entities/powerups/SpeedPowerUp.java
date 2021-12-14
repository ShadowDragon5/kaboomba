package com.entities.powerups;

import com.entities.players.Player;
import com.entities.Position;
import com.entities.visitor.Visitor;

public class SpeedPowerUp extends PowerUp {

    public SpeedPowerUp(Position position) {
        super(position);
    }

    @Override
    public Player decorate(Player player) {
        return new SpeedPowerUpDecorator(player);
    }

    @Override
    public void accept(Visitor v) {
        v.visitSpeedPowerUp(this);
    }

    @Override
    public String getTextureFile() {
        return "src/main/resources/powerup_speed.png";
    }

}
