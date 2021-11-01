package com.entities.players;

import com.core.enums.Direction;
import com.core.State;
import com.entities.bomb.BombExplosion;
import com.entities.pits.Pit;
import com.entities.tiles.Box;
import com.entities.GameObject;
import com.entities.Position;
import com.entities.powerups.PowerUp;
import com.entities.tiles.Wall;
import com.factories.player.PlayersAbstractFactory;

public abstract class Player extends GameObject {

    private final float speed = 0.01f;
    private int health = 3;
    private final int bombPower = 1;
    private final int bombAmmo = 1;
    private int bombsPlanted = 0;

    private Position oldPosition;
    private long lastDamageReceived;

    public Player() {
        super();
    }

    public Player(Player player) {
        super();
        this.setPosition(player.getPosition().clone());
        this.setOldPosition(player.getOldPosition().clone());
        this.health = player.getHealth();
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

    public abstract Player clone();

    public Position getOldPosition() {
        if (this.oldPosition == null)
            this.oldPosition = this.position.clone();
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

    public int getBombsPlanted() {
        return bombsPlanted;
    }

    public void setBombsPlanted(int bombsPlanted) {
        this.bombsPlanted = bombsPlanted;
    }

    public void decreaseHealth() {
        long currentTime = System.currentTimeMillis();
        if ((currentTime - this.lastDamageReceived) >= 2000L)
        {
            this.lastDamageReceived = currentTime;
            this.health--;
            System.out.println("Ouch!");
        }
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
        if(object instanceof BombExplosion || object instanceof Pit) {
            decreaseHealth();
        }
    }
}
