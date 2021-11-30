package com.entities;

import com.core.enums.ArithmeticActions;
import com.core.Defaults;
import com.utils.UtilityMethods;

public class Rectangle {
    private float x;
    private float y;

    private float width;
	private float height;

    public Rectangle() {
        this.x = 0;
        this.y = 0;
    }

    public Rectangle(float x, float y) {
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
        this.x = UtilityMethods.preciseArithmetics(this.x, dx, ArithmeticActions.SUM);
    }

    public void addY(float dy) {
        this.y = UtilityMethods.preciseArithmetics(this.y, dy, ArithmeticActions.SUM);
    }

    public Rectangle clone() {
        return new Rectangle(this.getX(), this.getY());
    }

    public Rectangle snap() {
        float flooredX = (float) Math.floor(getX() / Defaults.getDimension());
        var snappedX = flooredX + 0.5f;
        setX(UtilityMethods.preciseArithmetics(snappedX, Defaults.getDimension(), ArithmeticActions.MUL));

        float flooredY = (float) Math.floor(getY() / Defaults.getDimension());
        var snappedY = flooredY + 0.5f;
        setY(UtilityMethods.preciseArithmetics(snappedY, Defaults.getDimension(), ArithmeticActions.MUL));
        return this;
    }

    public Rectangle distanceManhattan(Rectangle otherPos) {
        return new Rectangle(this.x - otherPos.getX(), this.y - otherPos.getY());
    }

    @Override
    public String toString() {
        return String.format("x: %s, y:%s", getX(), getY());
    }

    @Override
    public boolean equals(Object obj) {
        Rectangle otherPosition = (Rectangle) obj;
        return otherPosition.getX() == this.getX() && otherPosition.getY() == this.getY();
    }
}
