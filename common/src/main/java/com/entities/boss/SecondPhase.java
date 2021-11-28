package com.entities.boss;

import com.core.State;

public class SecondPhase extends BossState {

    public SecondPhase(String bossID) {
        super(bossID);
        minInterval = 5000l;
        speed = 0.005f;
    }

    @Override
    public void handleBossActions() {
        move();
        boom(3);
    }

    @Override
    public void nextState() {
        var boss = State.getInstance().getBoss(bossID);
        if (boss.getHealth() == 14)
            previousState();
        if(boss.getHealth() == 10)
            boss.setBossState(new ThirdPhase(bossID));
    }

    @Override
    public void previousState() {
        var boss = State.getInstance().getBoss(bossID);
        boss.setBossState(new FirstPhase(bossID));
    }

    @Override
    public String bossStateTexture() {
        return "src/main/resources/bomb_blue.png";
    }
}
