package com.entities.boss;

import com.entities.players.BossPlayer;

public class BombBoss extends BossPlayer {

    public BombBoss() {
        this.setBossState(new FirstPhase(this.ID));
        this.setRectangle(this.getRectangle().clone().snap());
    }
}
