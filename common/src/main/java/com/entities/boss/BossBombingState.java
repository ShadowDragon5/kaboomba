package com.entities.boss;

public class BossBombingState extends BossState{
    @Override
    public void handleBossActions() {
        System.out.println("BossBombingState");
    }

    @Override
    public void nextState(BombBoss boss) {
        System.out.println("There is no next state");
    }

    @Override
    public void previousState(BombBoss boss) {
        boss.setBossState(new IdleBossState());
    }
}
