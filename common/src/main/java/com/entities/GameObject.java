package com.entities;

import com.core.ArithmeticActions;
import com.core.Globals;
import com.utils.UtilityMethods;

import java.awt.*;
import java.util.ArrayList;
import java.util.UUID;

public abstract class GameObject {
    public final String ID = UUID.randomUUID().toString();
    protected Position position;
    protected float dimensions;
    protected Color color;
    public String initiatorId;


    public GameObject(Position position, float dimensions) {
        this.position = position;
        this.dimensions = dimensions;
    }

    public GameObject(float dimensions) {
        this.dimensions = dimensions;
    }

    public GameObject(Position position) {
        this.position = position;
        this.dimensions = Globals.getDefaultDimension();
    }

    public GameObject() {
        this.dimensions = Globals.getDefaultDimension();
        this.position = new Position();
    }

    public Position getPosition() {
        return this.position;
    }

    public float getDimensions() {
        return dimensions;
    }

    public void setDimensions(float dimensions) {
        this.dimensions = dimensions;
    }

    public void setPosition(Position p) {
        this.position = p;
    }

    public void onCollision(GameObject object){
        System.out.println("Collides with " + object.getClass().toString());
    };

    public boolean collides(GameObject other) {
        float tdh = this.dimensions / 2;
        float odh = other.dimensions / 2;

        if (UtilityMethods.preciseArithmetics(other.position.getX(), odh, ArithmeticActions.SUM) >
            UtilityMethods.preciseArithmetics(this.position.getX(), tdh, ArithmeticActions.MIN) &&
            UtilityMethods.preciseArithmetics(other.position.getX(), odh, ArithmeticActions.MIN) <
            UtilityMethods.preciseArithmetics(this.position.getX(), tdh, ArithmeticActions.SUM) &&
            UtilityMethods.preciseArithmetics(other.position.getY(), odh, ArithmeticActions.SUM) >
            UtilityMethods.preciseArithmetics(this.position.getY(), tdh, ArithmeticActions.MIN) &&
            UtilityMethods.preciseArithmetics(other.position.getY(), odh, ArithmeticActions.MIN) <
            UtilityMethods.preciseArithmetics(this.position.getY(), tdh, ArithmeticActions.SUM)){
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
}
