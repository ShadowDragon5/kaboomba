package com.entities.boss;

import com.core.State;


public class FirstPhase extends BossState {

    public FirstPhase(String bossID) {
        super(bossID);
        minInterval = 5000l;
    }

    @Override
    public void handleBossActions() {
        boom(2);
    }

    @Override
    public void nextState() {
        var state = State.getInstance();
        var boss = state.getBoss(bossID);
        if (boss.getHealth() == 17 || boss.getHealth() == 12)
            boss.setBossState(new SecondPhase(bossID));
    }

    @Override
    public void previousState() {
        System.out.println("There is not way to go back");
    }

    @Override
    public String bossStateTexture() {
        return super.bossStateTexture();
    }
}
