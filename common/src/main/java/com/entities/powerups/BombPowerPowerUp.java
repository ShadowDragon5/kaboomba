package com.entities.powerups;

import com.entities.players.Player;
import com.entities.Position;
import com.entities.visitor.Visitor;

public class BombPowerPowerUp extends PowerUp {

    public BombPowerPowerUp(Position position) {
        super(position);
    }

    @Override
    public Player decorate(Player player) {
        return new BombPowerPowerUpDecorator(player);
    }

    @Override
    public void accept(Visitor v) {
        v.visitPowerPowerUp(this);
    }

    @Override
    public String getTextureFile() {
        return "src/main/resources/powerup_explosion.png";
    }

}
