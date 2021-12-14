package com.entities;

import com.core.enums.Direction;

import java.awt.*;
import java.util.UUID;

public abstract class GameObject {
    public String ID = UUID.randomUUID().toString();
    protected Rectangle rectangle;
    protected Color color;
    protected String initiatorId;

    public GameObject(Rectangle rectangle) {
        this.rectangle = rectangle;
    }

    public GameObject() {
    }

    public Rectangle getRectangle() {
        return this.rectangle;
    }

    public void setRectangle(Rectangle rectangle) {
        this.rectangle = rectangle;
    }

    public void onCollision(GameObject object) {
        System.out.println("Collides with " + object.getClass().toString());
    };

    public boolean collides(GameObject other) {
        if (rectangle.getSide(Direction.RIGHT) > other.rectangle.getSide(Direction.LEFT)
            && rectangle.getSide(Direction.LEFT) < other.rectangle.getSide(Direction.RIGHT)
            && rectangle.getSide(Direction.DOWN) > other.rectangle.getSide(Direction.UP)
            && rectangle.getSide(Direction.UP) < other.rectangle.getSide(Direction.DOWN))
        {
            onCollision(other);
            return true;
        }
        return false;
    }

    // current object collision with other
    public Direction getCollisionDirection(GameObject other) {
        float[] dd = {
            this.rectangle.getSide(Direction.DOWN) - other.rectangle.getSide(Direction.UP),
            this.rectangle.getSide(Direction.UP) - other.rectangle.getSide(Direction.DOWN),
            this.rectangle.getSide(Direction.LEFT) - other.rectangle.getSide(Direction.RIGHT),
            this.rectangle.getSide(Direction.RIGHT) - other.rectangle.getSide(Direction.LEFT),
        };

        int min = 0;
        for (int i = 1; i < 4; i++) {
            if (Math.abs(dd[min]) > Math.abs(dd[i])) {
                min = i;
            }
        }

        switch (min) {
            case 0:
                return Direction.DOWN;
            case 1:
                return Direction.UP;
            case 2:
                return Direction.LEFT;
            case 3:
                return Direction.RIGHT;
            default:
                return Direction.UP;
        }
    }

    public abstract String getTextureFile();

    public Color getColor() {
        return new Color(1f, 1f, 1f);
    }

    public void setInitiatorId(String initiatorId) {
        this.initiatorId = initiatorId;
    }

    public String getInitiatorId() { 
        return initiatorId;
    }
}
