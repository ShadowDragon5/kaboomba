package com.entities;

import com.core.ArithmeticActions;
import com.utils.UtilityMethods;

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
        // if(UtilityMethods.preciseArithmetics(this.x, dx, ArithmeticActions.MIN) <= 1f
        //         && UtilityMethods.preciseArithmetics(this.x, dx, ArithmeticActions.SUM) >= -1f)
            this.x = UtilityMethods.preciseArithmetics(this.x, dx, ArithmeticActions.SUM);
    }

    public void addY(float dy) {
        // if(UtilityMethods.preciseArithmetics(this.y, dy, ArithmeticActions.MIN) <= 1f
        //         && UtilityMethods.preciseArithmetics(this.y, dy, ArithmeticActions.SUM) >= -1f)
            this.y = UtilityMethods.preciseArithmetics(this.y, dy, ArithmeticActions.SUM);
    }

    @Override
    public String toString() {
        return String.format("x: %s, y:%s", getX(), getY());
    }
}
