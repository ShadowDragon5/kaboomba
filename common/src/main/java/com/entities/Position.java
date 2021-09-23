package com.entities;

import java.util.Random;

public class Position {
    private float x;
    private float y;

    public Position(){
        Random random = new Random(100);
        this.x = random.nextFloat() * 2 - 1;
        this.y = random.nextFloat() * 2 - 1;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public void incrementY(){
        this.y = this.y + 0.05f;
    }

    public void decrementY() {
        this.y = this.y - 0.05f;
    }

    public void incrementX() {
        this.x = this.x + 0.05f;
    }

    public void decrementX() {
        this.x = this.x - 0.05f;
    }
}
