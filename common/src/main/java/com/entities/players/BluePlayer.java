package com.entities.players;

import com.entities.powerups.BombAmmoPowerUp;
import com.entities.powerups.BombPowerPowerUp;
import com.entities.powerups.HealthyPowerUp;
import com.entities.powerups.SpeedPowerUp;
import com.entities.visitor.Visitor;
import com.factories.player.BluePlayerFactory;
import com.factories.player.PlayersAbstractFactory;

import java.awt.*;

public class BluePlayer extends Player implements Visitor {

    public BluePlayer() {
        super();
    }

    public BluePlayer(Player player) {
        super(player);
    }

    @Override
    public Player clone() {
        return new BluePlayer(this);
    }

    @Override
    public PlayersAbstractFactory getFactory() {
        return new BluePlayerFactory(this);
    }

    @Override
    public Color getColor() {
        return new Color(0f,0f,1f);
    }

    @Override
    public Player visitHealthPowerUp(HealthyPowerUp hp) {
        Player upgradedPlayer = this;
        upgradedPlayer.addScore(200);
        upgradedPlayer.setHealth(upgradedPlayer.health + 2);

        return upgradedPlayer;
    }

    @Override
    public Player visitSpeedPowerUp(SpeedPowerUp sp) {
        Player upgradedPlayer = this;
        upgradedPlayer.addScore(200);
        if(upgradedPlayer.getSpeed() < 0.015f) {
            upgradedPlayer.setSpeed(upgradedPlayer.getSpeed() + 0.005f);
        }

        return upgradedPlayer;
    }

    @Override
    public Player visitPowerPowerUp(BombPowerPowerUp pp) {
        return this;
    }

    @Override
    public Player visitAmmoPowerUp(BombAmmoPowerUp ap) {
        Player upgradedPlayer = this;
        upgradedPlayer.setBombAmmo(upgradedPlayer.getBombAmmo() + 1);

        return upgradedPlayer;
    }

    @Override
    public String getTextureFile() {
        return "src/main/resources/blue_player.png";
    }
}
