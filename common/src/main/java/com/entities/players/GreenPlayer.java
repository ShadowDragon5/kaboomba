package com.entities.players;

import com.entities.powerups.BombAmmoPowerUp;
import com.entities.powerups.BombPowerPowerUp;
import com.entities.powerups.HealthyPowerUp;
import com.entities.powerups.SpeedPowerUp;
import com.entities.visitor.Visitor;
import com.core.TextureFile;

import com.factories.player.GreenPlayerFactory;
import com.factories.player.PlayersAbstractFactory;

import java.awt.*;

public class GreenPlayer extends Player implements Visitor {
    public GreenPlayer() {
        super();
    }

    public GreenPlayer(Player player) {
        super(player);
    }

    @Override
    public Player clone() {
        return new GreenPlayer(this);
    }

    @Override
    public PlayersAbstractFactory getFactory() {
        return new GreenPlayerFactory(this);
    }

    @Override
    public Color getColor() {
        return new Color(0f,1f,0);
    }

    @Override
    public String getTextureFile() {
        return TextureFile.GREEN_PLAYER;
    }

    @Override
    public Player visitHealthPowerUp(HealthyPowerUp hp) {
        Player upgradedPlayer = this;
        upgradedPlayer.setHealth(upgradedPlayer.getHealth() + 1);

        return upgradedPlayer;
    }

    @Override
    public Player visitSpeedPowerUp(SpeedPowerUp sp) {
        return this;
    }

    @Override
    public Player visitPowerPowerUp(BombPowerPowerUp pp) {
        Player upgradedPlayer = this;
        upgradedPlayer.setBombPower(upgradedPlayer.getBombPower() + 2);
        upgradedPlayer.addScore(400);

        return upgradedPlayer;
    }

    @Override
    public Player visitAmmoPowerUp(BombAmmoPowerUp ap) {
        Player upgradedPlayer = this;
        upgradedPlayer.setBombAmmo(upgradedPlayer.getBombAmmo() + 1);

        return upgradedPlayer;
    }
}
