package com.entities;

public class Position {
    public int x;
    public int y;

    public Position(){
        this.x = 0;
        this.y = 0;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void incrementY(){
        this.y = this.y + 1;
    }

    public void decrementY() {
        this.y = this.y - 1;
    }

    public void incrementX() {
        this.x = this.x + 1;
    }

    public void decrementX() {
        this.x = this.x - 1;
    }
}
