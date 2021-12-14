package com.entities.boss;

import com.core.State;
import com.core.enums.Direction;
import com.entities.bomb.BaseBomb;
import com.entities.players.Player;

public abstract class BossState {
    protected String bossID;
    protected long timer;
    protected long minInterval;
    public float speed;
    public Player targetPlayer;

    public BossState(String bossID) {
        speed = 0f;
        this.bossID = bossID;
        if (!State.getInstance().getPlayers().isEmpty())
            targetPlayer = State.getInstance().getPlayers().get(0);
    }

    public abstract void handleBossActions();
    public abstract void nextState();
    public abstract void previousState();

    public boolean canExplode() {
        long currentTime = System.currentTimeMillis();
        return (currentTime - this.timer) >= minInterval;
    }

    protected void boom(int power) {
        if (!canExplode())
            return;

        var state = State.getInstance();
        var boss = state.getBoss(bossID);

        var b = new BaseBomb(boss.getRectangle().clone());
        b.setInitiatorId(bossID);
        b.setBombPower(power);
        state.addBomb(b);
        b.explode();

        timer = System.currentTimeMillis();
    }

    protected void move() {
        var boss = State.getInstance().getBoss(bossID);

        var distance = targetPlayer.getRectangle().distanceManhattan(boss.getRectangle());
        Direction direction;
        if (Math.abs(distance.getX()) > Math.abs(distance.getY())) {
            direction = distance.getX() >= 0 ? Direction.RIGHT : Direction.LEFT;
        } else {
            direction = distance.getY() >= 0 ? Direction.DOWN : Direction.UP;
        }
        boss.move(direction);
    }

    public abstract String bossStateTexture();
}
