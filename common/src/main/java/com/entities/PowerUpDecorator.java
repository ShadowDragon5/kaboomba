package com.entities;

public abstract class PowerUpDecorator extends Player {
    protected Player player;

    public PowerUpDecorator(Player player) {
        this.player = player;
    }

    public float getSpeed() {
        return super.getSpeed();
    }

    public float getBombAmmo() {
        return super.getBombAmmo();
    }

    public float getBombPower() {
        return super.getBombPower();
    }

    public float getHealth() {
        return super.getHealth();
    }

    @Override
    public String getTextureFile() {
        return player.getTextureFile();
    }

}
