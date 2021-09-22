package com.entities;

public class Position {
    private float x;
    private float y;

    public Position(){
        this.x = 0;
        this.y = 0;
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
