package com.entities;

import com.controllers.BombExplosionController;
import com.core.State;
import com.core.WithLifespan;

import java.util.ArrayList;

import static com.utils.Scheduler.scheduleTask;


public abstract class Bomb extends GameObject implements WithLifespan {
    BombExplosionController bombExplosionController = new BombExplosionController();

    private final Long lifespan = 2000L;

    public Bomb() {

    }

    public Bomb(Position position) {
        super(position);
    }

    @Override
    public String getTextureFile() {
        return "src/main/resources/bomb.png";
    }

    @Override
    public Long getLifespan() {
        return lifespan;
    }

    public abstract BombExplosion createExplosion(Position position);

    public ArrayList<BombExplosion> createBombExplosion(){
        return bombExplosionController.createExplosion(this);
    }

    public void explode() {
        var state = State.getInstance();
        state.removeBomb(this);

        var explosion = this.createBombExplosion();
        state.addBombExplosion(explosion);

        scheduleTask(() -> {
            explosion.forEach(state::removeExplosion);
            return null;
        }, "Explosion_Timer", explosion.get(0).getLifespan());
    }

}
