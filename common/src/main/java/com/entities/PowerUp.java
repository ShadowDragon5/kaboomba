package com.entities;

public abstract class PowerUp extends Tile {
    private PowerUpDecorator decorator;

    public PowerUp(Position position) {
        super(position);
    }

    public abstract Player decorate(Player player);

}
