package com.entities.players;

import com.entities.GameObject;
import com.entities.bomb.BombExplosion;
import com.entities.boss.BossState;
import com.entities.pits.Pit;
import com.factories.player.BossPlayerFactory;
import com.factories.player.PlayersAbstractFactory;

import java.awt.*;

public class BossPlayer extends Player {
    private BossState bossState;

    public BossPlayer() {
        super();
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
        return "src/main/resources/green_player.png";
    }

    public BossState getBossState() {
        return bossState;
    }

    public void setBossState(BossState bossState) {
        this.bossState = bossState;
    }


    @Override
    public void onCollision(GameObject object) {
        if(object instanceof BombExplosion && object.getInitiatorId().equals(this.ID)) {
            return;
        }

        super.onCollision(object);
    }
}
