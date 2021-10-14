package com.entities;

import com.core.Direction;
import com.core.Globals;
import com.utils.PlayersAbstractFactory;

public abstract class Player extends GameObject {

    private final float speed = 0.01f;
    private final float health = 1;
    private final float bombPower = 2;
    private final float bombAmmo = 2;

    public Player() {
        super();
    }

    public float getSpeed() {
        return speed;
    }

    public float getHealth() {
        return health;
    }

    public float getBombPower() {
        return bombPower;
    }

    public float getBombAmmo() {
        return bombAmmo;
    }


    @Override
    public String getTextureFile() {
        return "src/main/player";
    }

    public void move(Direction direction) {
        switch (direction) {
            case UP:
                this.position.addY(getSpeed());
                break;

            case DOWN:
                this.position.addY(-getSpeed());
                break;

            case LEFT:
                this.position.addX(-getSpeed());
                break;

            case RIGHT:
                this.position.addX(getSpeed());
                break;
        }
    }

    public abstract PlayersAbstractFactory getFactory();
}
