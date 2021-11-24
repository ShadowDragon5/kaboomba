package com.entities.boss;

public class IdleBossState extends BossState {
    @Override
    public void handleBossActions() {
        System.out.println("IdleBossState");
    }

    @Override
    public void nextState(BombBoss boss) {
        boss.setBossState(new BossBombingState());
    }

    @Override
    public void previousState(BombBoss boss) {
        System.out.println("There is not way to go back");
    }
}
