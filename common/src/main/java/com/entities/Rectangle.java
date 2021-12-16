package com.entities;

import com.core.Defaults;
import com.core.enums.Direction;

public class Rectangle {
    private float x;
    private float y;

    private float width;
	private float height;

    public Rectangle() {
        this(0, 0);
    }

    public Rectangle(float x, float y) {
        this(x, y, Defaults.getDimension(), Defaults.getDimension());
    }

    public Rectangle(float x, float y, float w, float h) {
        this.x = x;
        this.y = y;
        this.width = w;
        this.height = h;
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

    public float getWidth() {
		return width;
	}

	public void setWidth(float width) {
		this.width = width;
	}

	public float getHeight() {
		return height;
	}

	public void setHeight(float height) {
		this.height = height;
	}

    public void addX(float dx) {
        this.x += dx;
    }

    public void addY(float dy) {
        this.y += dy;
    }

    public float getSide(Direction dir) {
        return dir == Direction.LEFT ? x :
            dir == Direction.RIGHT ? x + width :
            dir == Direction.UP ? y :
            dir == Direction.DOWN ? y + height : 0;
    }

    public Rectangle clone() {
        return new Rectangle(this.getX(), this.getY(), this.getWidth(), this.getHeight());
    }

    public Rectangle clonePosition() {
        return new Rectangle(this.getX(), this.getY());
    }


    public Rectangle snap() {
        float dd = Defaults.getDimension();
        float flooredX = (float) Math.floor((getX() + getWidth() / 2) / dd);
        setX(flooredX * dd);

        float flooredY = (float) Math.floor((getY() + getHeight() / 2) / dd);
        setY(flooredY * dd);
        return this;
    }

    public Rectangle distanceManhattan(Rectangle otherPos) {
        return new Rectangle(this.x - otherPos.getX(), this.y - otherPos.getY());
    }

    @Override
    public String toString() {
        return String.format("x: %s, y: %s, w: %s, h: %s", getX(), getY(), getWidth(), getHeight());
    }

    @Override
    public boolean equals(Object obj) {
        Rectangle otherPosition = (Rectangle) obj;
        return otherPosition.getX() == this.getX() && otherPosition.getY() == this.getY();
    }
}
