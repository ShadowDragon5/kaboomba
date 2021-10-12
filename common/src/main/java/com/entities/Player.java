package com.entities;

import com.core.Direction;
import com.core.Globals;
import com.utils.PlayersAbstractFactory;

public abstract class Player extends GameObject {

    private final float speed = 0.01f;

    public Player() {
        super();
    }


    @Override
    public String getTextureFile() {
        return "src/main/player";
    }

    public void move(Direction direction) {
        switch (direction) {
            case UP:
                this.position.addY(speed);
                break;

            case DOWN:
                this.position.addY(-speed);
                break;

            case LEFT:
                this.position.addX(-speed);
                break;

            case RIGHT:
                this.position.addX(speed);
                break;
        }
    }

    public abstract PlayersAbstractFactory getFactory();
}
