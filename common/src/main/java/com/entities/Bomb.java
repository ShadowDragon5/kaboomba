package com.entities;

public class Bomb extends GameObject{
    public String initiatorId;

    public Bomb(Position position) {
        super(position);
    }

    public void setInitiatorId(String initiatorId) {
        this.initiatorId = initiatorId;
    }

    @Override
    public String getTextureFile() {
        return "src/resources/bomb";
    }
}
