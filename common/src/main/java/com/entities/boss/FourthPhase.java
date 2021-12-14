package com.entities.boss;

import com.entities.bomb.*;
import com.core.State;
import com.core.TextureFile;

public class FourthPhase extends BossState {

    private int explosionCount = 0;

    public FourthPhase(String bossID) {
        super(bossID);
        minInterval = 6000l;
        speed = 1;
    }

    @Override
    public void handleBossActions() {
        move();

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

    @Override
    public void nextState() {
        var boss = State.getInstance().getBoss(bossID);
        if (boss.getHealth() == 4)
            previousState();
    }

    @Override
    public void previousState() {
        var boss = State.getInstance().getBoss(bossID);
        boss.setBossState(new ThirdPhase(bossID));
    }

    @Override
    protected void boom(int power) {
        var state = State.getInstance();
        var boss = state.getBoss(bossID);

        var b = new BaseBomb(boss.getRectangle());
        b.setInitiatorId(bossID);
        b.setBombPower(power);
        state.addBomb(b);
        b.explode();

        timer = System.currentTimeMillis();
    }

    @Override
    public String bossStateTexture() {
        return TextureFile.BOSS_STAGE_FOUR;
    }
}
