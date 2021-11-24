package com.entities.boss;

public abstract class BossState {
    public abstract void handleBossActions();
    public abstract void nextState(BombBoss boss);
    public abstract void previousState(BombBoss boss);
}
