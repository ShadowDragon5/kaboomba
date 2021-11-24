package com.entities.boss;

import com.entities.players.BossPlayer;

public class BombBoss extends BossPlayer {

    public BombBoss() {
        this.setBossState(new IdleBossState());
        this.setPosition(this.getPosition().clone().snap());
    }
}
