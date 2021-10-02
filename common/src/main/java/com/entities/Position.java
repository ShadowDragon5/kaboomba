package com.entities;

public class Position {
    private float x;
    private float y;

    public Position(){
        this.x = 0;
        this.y = 0;
    }

    public Position(float x, float y) {
        this.x = x;
        this.y = y;
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

    public void addX(float dx) {
        if(this.x + dx <= 1f && this.x + dx >= -1f)
            this.x += dx;
    }

    public void addY(float dy) {
        if(this.y + dy <= 1f && this.y + dy >= -1f)
            this.y += dy;
    }
}
