package com.entities.bomb;

import com.controllers.BombExplosionController;
import com.core.enums.ExplosionDirection;
import com.core.State;
import com.core.TextureFile;
import com.core.WithLifespan;
import com.entities.GameObject;
import com.entities.Rectangle;

import java.util.ArrayList;

import static com.utils.Scheduler.scheduleTask;


public abstract class Bomb extends GameObject implements WithLifespan {
    BombExplosionController bombExplosionController = new BombExplosionController();
    private float bombPower = 1;

    private Long lifespan = 2000L;

    public Bomb() {
    }

    public Bomb(Rectangle rectangle) {
        super(rectangle.clone());
    }

    @Override
    public String getTextureFile() {
        return TextureFile.BOMB;
    }

    @Override
    public Long getLifespan() {
        return lifespan;
    }

    @Override
    public void setLifespan(Long lifespan) {
        this.lifespan = lifespan;
    }

    public abstract BombExplosion createExplosion(Rectangle rectangle, ExplosionDirection direction);

    public ArrayList<BombExplosion> createBombExplosion(){
        return bombExplosionController.createExplosion(this);
    }

    public void explode() {
        State.getInstance().removeBomb(this);
        handleExplosion();
    }

    public void setBombPower(float bombPower) {
        this.bombPower = bombPower;
    }
    public float getBombPower() {
        return bombPower;
    }

    protected void handleExplosion() {
        var explosion = this.createBombExplosion();
        State.getInstance().addBombExplosion(explosion);

        scheduleTask(() -> {
            explosion.forEach(State.getInstance()::removeExplosion);
            return null;
        }, "Explosion_Timer", explosion.get(0).getLifespan());
    }

}
