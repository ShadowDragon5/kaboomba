package com.entities.boss;

import com.entities.bomb.*;
import com.core.State;


public class FirstPhase extends BossState {

    public FirstPhase(String bossID) {
        super(bossID);
        minInterval = 5000l;
    }

    @Override
    public void handleBossActions() {
        if (!canExplode())
            return;

        var state = State.getInstance();
        var boss = state.getBoss(bossID);

        var b = new BaseBomb(boss.getPosition());
        b.setInitiatorId(bossID);
        b.setBombPower(2);
        state.addBomb(b);
        b.explode();

        timer = System.currentTimeMillis();
    }

    @Override
    public void nextState() {
        var state = State.getInstance();
        var boss = state.getBoss(bossID);
        boss.setBossState(new SecondPhase(bossID));
    }

    @Override
    public void previousState() {
        System.out.println("There is not way to go back");
    }
}
