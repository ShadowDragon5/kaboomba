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
        this.rectangle = new Rectangle();
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
            && rectangle.getSide(Direction.LEFT)  < other.rectangle.getSide(Direction.RIGHT))
            // && rectangle.getSide(Direction.DOWN)  < other.rectangle.getSide(Direction.UP)
            // && rectangle.getSide(Direction.UP)    > other.rectangle.getSide(Direction.DOWN))
        {
            onCollision(other);
            System.out.println("Collides with " + other.getClass().toString());
            return true;
        }

        return false;
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
