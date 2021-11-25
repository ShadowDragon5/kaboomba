package com.entities.players;

import com.entities.GameObject;
import com.entities.bomb.BombExplosion;
import com.entities.boss.BossState;
import com.factories.player.BossPlayerFactory;
import com.factories.player.PlayersAbstractFactory;
import com.core.Defaults;
import com.core.State;

import java.awt.*;

public class BossPlayer extends Player {
    private BossState bossState;

    public BossPlayer() {
        super();
        this.health = 5;
        this.setSpeed(0.005f);
    }

    public BossPlayer(Player player) {
        super(player);
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
        return "src/main/resources/bomb.png";
    }

    public BossState getBossState() {
        return bossState;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void setBossState(BossState bossState) {
        if (bossState == null) {
            State.getInstance().removeBoss(this.ID);
            return;
        }
        this.bossState = bossState;
    }

    @Override
    public boolean decreaseHealth() {
        boolean decreased = super.decreaseHealth();
        if (decreased && isDead()) {
            bossState.nextState();
        }
        return decreased;
    }


    @Override
    public void onCollision(GameObject object) {
        if(object instanceof BombExplosion && object.getInitiatorId().equals(this.ID)) {
            return;
        }

        super.onCollision(object);
    }
}
