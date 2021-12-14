package com.entities.powerups;

import com.entities.players.Player;
import com.entities.Position;
import com.entities.visitor.Visitor;

public class BombAmmoPowerUp extends PowerUp {

    public BombAmmoPowerUp(Position position) {
        super(position);
    }

    @Override
    public Player decorate(Player player) {
        return new BombAmmoPowerUpDecorator(player);
    }

    @Override
    public void accept(Visitor v) {
        v.visitAmmoPowerUp(this);
    }

    @Override
    public String getTextureFile() {
        return "src/main/resources/powerup_ammo.png";
    }

}
