package com.entities.boss;

import com.core.Defaults;
import com.entities.bomb.*;
import com.core.State;
import com.core.enums.*;
import java.util.Random;
import com.entities.shields.BlueShield;

import static com.utils.Scheduler.scheduleTask;

public class ThirdPhase extends BossState {

    public ThirdPhase(String bossID) {
        super(bossID);
        minInterval = 4000l;
        var state = State.getInstance();
        var boss = state.getBoss(bossID);
        boss.setHealth(4);
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
        var state = State.getInstance();
        var boss = state.getBoss(bossID);
        boss.setBossState(new FourthPhase(bossID));
    }

    @Override
    public void previousState() {
        System.out.println("There is not way to go back");
    }
}
