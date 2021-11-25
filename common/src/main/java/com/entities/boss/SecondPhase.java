package com.entities.boss;

import com.entities.bomb.*;
import com.core.State;
import com.core.enums.*;
import java.util.Random;


public class SecondPhase extends BossState {

    public SecondPhase(String bossID) {
        super(bossID);
        minInterval = 5000l;
        var state = State.getInstance();
        var boss = state.getBoss(bossID);
        boss.setHealth(5);
    }

    @Override
    public void handleBossActions() {
        var state = State.getInstance();
        var boss = state.getBoss(bossID);

        var r = new Random();
        boss.move(Direction.values()[r.nextInt(4)]);

        if (!canExplode())
            return;

        var b = new BaseBomb(boss.getPosition());
        b.setInitiatorId(bossID);
        b.setBombPower(3);
        state.addBomb(b);
        b.explode();

        timer = System.currentTimeMillis();
    }

    @Override
    public void nextState() {
        var state = State.getInstance();
        var boss = state.getBoss(bossID);
        boss.setBossState(new ThirdPhase(bossID));
    }

    @Override
    public void previousState() {
        System.out.println("There is not way to go back");
    }
}
