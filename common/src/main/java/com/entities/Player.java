package com.entities;

import com.core.Direction;
import com.core.State;
import com.utils.PlayersAbstractFactory;

public abstract class Player extends GameObject {

    private final float speed = 0.01f;
    private final int health = 1;
    private final int bombPower = 1;
    private final int bombAmmo = 2;

    private Position oldPosition;

    public Player() {
        super();
    }

    public Player(Player player) {
        super();
        this.setPosition(player.getPosition().clone());
        this.setOldPosition(player.oldPosition.clone());
        this.ID = player.ID;
    }

    public abstract PlayersAbstractFactory getFactory();

    public void move(Direction direction) {
        setOldPosition(new Position(this.getPosition().getX(), this.getPosition().getY()));

        switch (direction) {
            case UP:
                this.position.addY(getSpeed());
                break;
            case DOWN:
                this.position.addY(-getSpeed());
                break;
            case LEFT:
                this.position.addX(-getSpeed());
                break;
            case RIGHT:
                this.position.addX(getSpeed());
                break;
        }
    }

    public Position getOldPosition() {
        return oldPosition;
    }

    public void setOldPosition(Position oldPosition) {
        this.oldPosition = oldPosition;
    }

    public float getSpeed() {
        return speed;
    }

    public int getHealth() {
        return health;
    }

    public int getBombPower() {
        return bombPower;
    }

    public int getBombAmmo() {
        return bombAmmo;
    }

    @Override
    public String getTextureFile() {
        return "src/main/player";
    }

    @Override
    public void onCollision(GameObject object) {
        if (object instanceof Box || object instanceof Wall) {
            this.setPosition(oldPosition.clone().snap());
        }
        if(object instanceof PowerUp) {
            State.getInstance().removePowerup(object);
            State.getInstance().replacePlayer(this, ((PowerUp) object).decorate(this));
        }
    }
}
