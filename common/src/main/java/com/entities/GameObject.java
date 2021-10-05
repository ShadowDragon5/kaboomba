package com.entities;

import com.core.Globals;

import java.awt.*;
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

    public boolean collides(GameObject other) {
//        float tdh = this.dimensions / 2;
//        float odh = other.dimensions / 2;
//
//        if (other.position.getX() + odh <= this.position.getX() - tdh &&// other right vs this left
//            other.position.getX() - odh >= this.position.getX() + tdh)  // other left vs this right
//            return true;
//
//        if (other.position.getY() + odh <= this.position.getY() - tdh &&// other right vs this left
//            other.position.getY() - odh >= this.position.getY() + tdh)  // other left vs this right
//            return true;

        return other.position.getX() == this.position.getX() && other.position.getY() == this.position.getY();
    }

    public abstract String getTextureFile();
    public Color getColor(){
        return new Color(1f,1f,1f);
    }

    public void setInitiatorId(String initiatorId) {
        this.initiatorId = initiatorId;
    }

}
