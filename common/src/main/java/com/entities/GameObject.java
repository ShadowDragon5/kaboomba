package com.entities;

import com.core.enums.ArithmeticActions;
import com.core.Defaults;
import com.utils.UtilityMethods;

import java.awt.*;
import java.util.UUID;

public abstract class GameObject {
    public String ID = UUID.randomUUID().toString();
    protected Rectangle rectangle;
    protected float dimensions;
    protected Color color;
    protected String initiatorId;


    public GameObject(Rectangle rectangle, float dimensions) {
        this.rectangle = rectangle;
        this.dimensions = dimensions;
    }

    public GameObject(float dimensions) {
        this.dimensions = dimensions;
    }

    public GameObject(Rectangle rectangle) {
        this.rectangle = rectangle;
        this.dimensions = Defaults.getDimension();
    }

    public GameObject() {
        this.dimensions = Defaults.getDimension();
        this.rectangle = new Rectangle();
    }

    public Rectangle getRectangle() {
        return this.rectangle;
    }

    public float getDimensions() {
        return dimensions;
    }

    public void setDimensions(float dimensions) {
        this.dimensions = dimensions;
    }

    public void setRectangle(Rectangle rectangle) {
        this.rectangle = rectangle;
    }

    public void onCollision(GameObject object){
        System.out.println("Collides with " + object.getClass().toString());
    };

    public boolean collides(GameObject other) {
        float tdh = this.dimensions / 2;
        float odh = other.dimensions / 2;

        if (UtilityMethods.preciseArithmetics(other.rectangle.getX(), odh, ArithmeticActions.SUM) >
            UtilityMethods.preciseArithmetics(this.rectangle.getX(), tdh, ArithmeticActions.MIN) &&
            UtilityMethods.preciseArithmetics(other.rectangle.getX(), odh, ArithmeticActions.MIN) <
            UtilityMethods.preciseArithmetics(this.rectangle.getX(), tdh, ArithmeticActions.SUM) &&
            UtilityMethods.preciseArithmetics(other.rectangle.getY(), odh, ArithmeticActions.SUM) >
            UtilityMethods.preciseArithmetics(this.rectangle.getY(), tdh, ArithmeticActions.MIN) &&
            UtilityMethods.preciseArithmetics(other.rectangle.getY(), odh, ArithmeticActions.MIN) <
            UtilityMethods.preciseArithmetics(this.rectangle.getY(), tdh, ArithmeticActions.SUM)){
            onCollision(other);
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
