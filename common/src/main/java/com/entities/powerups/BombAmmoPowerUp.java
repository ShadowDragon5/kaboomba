package com.entities.powerups;

import com.entities.players.Player;
import com.entities.visitor.Visitor;
import com.core.TextureFile;
import com.entities.Rectangle;

public class BombAmmoPowerUp extends PowerUp {

    public BombAmmoPowerUp(Rectangle position) {
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
        return TextureFile.POWERUP_AMMO;
    }

}
