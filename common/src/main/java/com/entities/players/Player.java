package com.entities.players;

import com.core.enums.Direction;
import com.core.State;
import com.core.Defaults;
import com.entities.bomb.BombExplosion;
import com.entities.pits.Pit;
import com.entities.tiles.Box;
import com.entities.GameObject;
import com.entities.Position;
import com.entities.powerups.PowerUp;
import com.entities.tiles.Wall;
import com.factories.player.PlayersAbstractFactory;

public abstract class Player extends GameObject {

    private float speed = 0.01f;
    private int health = Defaults.playerHealth;
    private final int bombPower = 1;
    private final int bombAmmo = 1;
    private int bombsPlanted = 0;
    private int pitsPlanted = 0;
    private int shieldsPlanted = 0;
    private int score = 0;

    private long lastTimeTeleported;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

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
        this.score = player.getScore();
        this.name = player.getName();
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

    public int getScore() {
        return score;
    }

    public void addScore(int score) {
        this.score += score;
    }

    public void setOldPosition(Position oldPosition) {
        this.oldPosition = oldPosition;
    }

    public float getSpeed() {
        return speed;
    }
    public void setSpeed(float speed) {
        this.speed = speed;
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

    public int getPitsPlanted() {
        return pitsPlanted;
    }

    public void setPitsPlanted(int pitsPlanted) {
        this.pitsPlanted = pitsPlanted;
    }

    public int getShieldsPlanted() {
        return shieldsPlanted;
    }

    public void setShieldsPlanted(int shieldsPlanted) {
        this.shieldsPlanted = shieldsPlanted;
    }

    public void setLastTimeTeleported(long lastTimeTeleported) {
        this.lastTimeTeleported = lastTimeTeleported;
    }

    public boolean canTeleport() {
        long currentTime = System.currentTimeMillis();
        return (currentTime - this.lastTimeTeleported) >= 5000L ;
    }
  
    public boolean decreaseHealth() {
        long currentTime = System.currentTimeMillis();
        if ((currentTime - this.lastDamageReceived) <= 2000L)
            return false;

        this.lastDamageReceived = currentTime;
        this.health--;
        this.addScore(Defaults.scoreReceiveDamage);
        return true;
    }

    public boolean isDead() {
        return getHealth() <= 0;
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
        if(object instanceof BombExplosion) {
            String initiatorId = object.getInitiatorId();
            if (decreaseHealth() && initiatorId != this.ID) {
                State.getInstance().getPlayer(initiatorId).addScore(Defaults.scoreDealDamage);
            }
        }
        if(object instanceof Pit) {
            ((Pit) object).triggerPit(this);
        }
    }
}
