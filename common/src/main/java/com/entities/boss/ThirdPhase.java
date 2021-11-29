package com.entities.boss;

import com.core.Defaults;
import com.core.State;
import com.entities.shields.BlueShield;

import static com.utils.Scheduler.scheduleTask;

public class ThirdPhase extends BossState {

    public ThirdPhase(String bossID) {
        super(bossID);
        minInterval = 4000l;
        speed = 0.005f;
    }

    @Override
    public void handleBossActions() {
        var state = State.getInstance();
        var boss = state.getBoss(bossID);

        move();
        if (!canExplode())
            return;
        boom(3);

        // TODO Juliui pagrazinti
        var pos = boss.getPosition().clone().snap();
        pos.addX(Defaults.getDimension());
        BlueShield shield1 = new BlueShield(pos);
        shield1.setInitiatorId(bossID);
        state.addShield(shield1);

        pos = boss.getPosition().clone().snap();
        pos.addX(-Defaults.getDimension());
        BlueShield shield2 = new BlueShield(pos);
        shield2.setInitiatorId(bossID);
        state.addShield(shield2);

        pos = boss.getPosition().clone().snap();
        pos.addY(Defaults.getDimension());
        BlueShield shield3 = new BlueShield(pos);
        shield3.setInitiatorId(bossID);
        state.addShield(shield3);

        pos = boss.getPosition().clone().snap();
        pos.addY(-Defaults.getDimension());
        BlueShield shield4 = new BlueShield(pos);
        shield4.setInitiatorId(bossID);
        state.addShield(shield4);

        scheduleTask(() -> {
            state.removeShield(shield1);
            state.removeShield(shield2);
            state.removeShield(shield3);
            state.removeShield(shield4);
            return null;
        }, "Shield_Timer", 2500l);

    }

    @Override
    public void nextState() {
        var boss = State.getInstance().getBoss(bossID);
        if (boss.getHealth() == 6 || boss.getHealth() == 2)
            boss.setBossState(new FourthPhase(bossID));

    }

    @Override
    public void previousState() {
        var boss = State.getInstance().getBoss(bossID);
        boss.setBossState(new SecondPhase(bossID));;
    }

    @Override
    public String bossStateTexture() {
        return "src/main/resources/bomb_green.png";
    }
}
