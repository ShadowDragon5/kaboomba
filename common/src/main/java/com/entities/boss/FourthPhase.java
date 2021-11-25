package com.entities.boss;

import com.entities.bomb.*;
import com.core.State;
import com.core.enums.*;
import java.util.Random;

public class FourthPhase extends BossState {

    private int explosionCount = 0;

    public FourthPhase(String bossID) {
        super(bossID);
        minInterval = 7000l;
        var state = State.getInstance();
        var boss = state.getBoss(bossID);
        boss.setSpeed(0.015f);
        boss.setHealth(3);
    }

    @Override
    public void handleBossActions() {
        var state = State.getInstance();
        var boss = state.getBoss(bossID);

        var r = new Random();
        boss.move(Direction.values()[r.nextInt(4)]);

        long currentTime = System.currentTimeMillis();
        switch(explosionCount) {
            case 0:
                if ((currentTime - this.timer) >= minInterval) {
                    boom(2);
                    explosionCount++;
                    timer = currentTime;
                }
                break;
            case 1:
                if ((currentTime - this.timer) >= 1000l) {
                    boom(2);
                    explosionCount++;
                    timer = currentTime;
                }
                break;
            case 2:
                if ((currentTime - this.timer) >= 1000l) {
                    boom(4);
                    explosionCount = 0;
                    timer = currentTime;
                }
                break;
        }

    }

    private void boom(int power) {
        var state = State.getInstance();
        var boss = state.getBoss(bossID);

        var b = new BaseBomb(boss.getPosition());
        b.setInitiatorId(bossID);
        b.setBombPower(power);
        state.addBomb(b);
        b.explode();
    }

    @Override
    public void nextState() {
        var state = State.getInstance();
        var boss = state.getBoss(bossID);
        boss.setBossState(null);
    }

    @Override
    public void previousState() {
        System.out.println("There is not way to go back");
    }
}
