package com.entities;

import com.core.Direction;

public class Player extends GameObject {

    private final float speed = 0.1f;

    public Player() {
        super(new Position());
    }

    @Override
    public String getTextureFile() {
        return "Player";
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

}
