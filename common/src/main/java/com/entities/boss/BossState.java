package com.entities.boss;

public abstract class BossState {
    protected String bossID;
    protected long timer;
    protected long minInterval;


    public BossState(String bossID) {
        this.bossID = bossID;
    }

    public abstract void handleBossActions();
    public abstract void nextState();
    public abstract void previousState();

    public boolean canExplode() {
        long currentTime = System.currentTimeMillis();
        return (currentTime - this.timer) >= minInterval;
    }
}
