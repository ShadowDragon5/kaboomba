package com.entities.players;

import com.core.enums.Direction;
import com.core.State;
import com.core.Defaults;
import com.entities.bomb.BombExplosion;
import com.entities.pits.Pit;
import com.entities.tiles.Box;
import com.entities.GameObject;
import com.entities.Rectangle;
import com.entities.powerups.PowerUp;
import com.entities.tiles.Wall;
import com.factories.player.PlayersAbstractFactory;

public abstract class Player extends GameObject {

    private float speed = 1.6f;
    protected int health = Defaults.playerHealth;
    private final int bombPower = 1;
    private final int bombAmmo = 1;
    private int bombsPlanted = 0;
    private int pitsPlanted = 0;
    private int shieldsPlanted = 0;
    private int score = 0;

    private long lastTimeTeleported;
    private String name;
    protected Rectangle oldRectangle;
    private long lastDamageReceived;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Player() {
        super();
    }

    public Player(Player player) {
        super();
        this.setRectangle(player.getRectangle().clone());
        this.setOldRectangle(player.getOldRectangle().clone());
        this.health = player.getHealth();
        this.score = player.getScore();
        this.name = player.getName();
        this.ID = player.ID;
    }

    public abstract PlayersAbstractFactory getFactory();

    public void move(Direction direction) {
        setOldRectangle(new Rectangle(this.getRectangle().getX(), this.getRectangle().getY()));

        switch (direction) {
            case UP:
                this.rectangle.addY(-getSpeed());
                break;
            case DOWN:
                this.rectangle.addY(getSpeed());
                break;
            case LEFT:
                this.rectangle.addX(-getSpeed());
                break;
            case RIGHT:
                this.rectangle.addX(getSpeed());
                break;
        }
    }

    public abstract Player clone();

    public Rectangle getOldRectangle() {
        if (this.oldRectangle == null)
            this.oldRectangle = this.rectangle.clone();
        return oldRectangle;
    }

    public int getScore() {
        return score;
    }

    public void addScore(int score) {
        this.score += score;
    }

    public void setOldRectangle(Rectangle oldRectangle) {
        this.oldRectangle = oldRectangle;
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
            // this.setRectangle(oldRectangle.clone().snap());
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
