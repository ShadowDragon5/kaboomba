package com.entities;

public abstract class PowerUpDecorator extends Player {
    protected Player player;

    public PowerUpDecorator(Player player) {
        super(player);
        this.player = player;
    }

    @Override
    public String getTextureFile() {
        return player.getTextureFile();
    }

}
