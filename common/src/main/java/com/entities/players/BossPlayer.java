package com.entities.players;

import com.entities.GameObject;
import com.entities.bomb.BombExplosion;
import com.entities.boss.BossState;
import com.entities.powerups.BombAmmoPowerUp;
import com.entities.powerups.BombPowerPowerUp;
import com.entities.powerups.HealthyPowerUp;
import com.entities.powerups.SpeedPowerUp;
import com.entities.visitor.Visitor;
import com.factories.player.BossPlayerFactory;
import com.factories.player.PlayersAbstractFactory;
import com.core.Defaults;
import com.core.State;

import java.awt.*;

public class BossPlayer extends Player implements Visitor {
    private BossState bossState;

    public BossPlayer() {
        super();
        this.health = 20;
        this.setSpeed(0.005f);
    }

    public BossPlayer(BossPlayer boss) {
        super(boss);
        this.bossState = boss.bossState;
    }

    @Override
    public Player clone() {
        return new BossPlayer(this);
    }

    @Override
    public PlayersAbstractFactory getFactory() {
        return new BossPlayerFactory(this);
    }

    @Override
    public Color getColor() {
        return new Color(0f,1f,0);
    }

    @Override
    public String getTextureFile() {
        return bossState.bossStateTexture();
    }

    public BossState getBossState() {
        return bossState;
    }

    public void setBossState(BossState bossState) {
        this.bossState = bossState;
        setSpeed(bossState.speed);
    }

    @Override
    public boolean decreaseHealth() {
        boolean decreased = super.decreaseHealth();
        if (isDead()) {
            State.getInstance().removeBoss(this.ID);
            return true;
        }
        if (decreased) {
            bossState.nextState();
        }

        return decreased;
    }


    @Override
    public void onCollision(GameObject object) {
        if(object instanceof BombExplosion) {
            if (object.getInitiatorId().equals(this.ID)) return;
            if (decreaseHealth()) {
                var player = State.getInstance().getPlayer(object.getInitiatorId());
                player.addScore(Defaults.scoreDealDamage);
                bossState.targetPlayer = player;
            }
            return;
        }
        super.onCollision(object);
    }

    @Override
    public Player visitHealthPowerUp(HealthyPowerUp hp) {
        Player upgradedPlayer = this;
        upgradedPlayer.setHealth(upgradedPlayer.health + 2);

        return upgradedPlayer;
    }

    @Override
    public Player visitSpeedPowerUp(SpeedPowerUp sp) {
        Player upgradedPlayer = this;
        if(upgradedPlayer.getSpeed() < 0.015f) {
            upgradedPlayer.setSpeed(upgradedPlayer.getSpeed() + 0.005f);
        }

        return upgradedPlayer;
    }

    @Override
    public Player visitPowerPowerUp(BombPowerPowerUp pp) {
        Player upgradedPlayer = this;
        upgradedPlayer.setBombPower(upgradedPlayer.getBombPower() + 2);

        return upgradedPlayer;
    }

    @Override
    public Player visitAmmoPowerUp(BombAmmoPowerUp ap) {
        Player upgradedPlayer = this;
        upgradedPlayer.setBombAmmo(upgradedPlayer.getBombAmmo() + 1);

        return upgradedPlayer;
    }
}
