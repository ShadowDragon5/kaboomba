package com.entities.players;

import com.core.enums.Direction;
import com.core.State;
import com.core.Defaults;
import com.entities.bomb.BombExplosion;
import com.entities.pits.Pit;
import com.entities.powerups.*;
import com.entities.tiles.Box;
import com.entities.GameObject;
import com.entities.powerups.PowerUp;

import com.entities.tiles.Wall;
import com.factories.player.PlayersAbstractFactory;

public abstract class Player extends GameObject {

    private float speed = 1.5f;
    protected int health = Defaults.playerHealth;
    private int bombPower = 1;
    private int bombAmmo = 1;
    private int bombsPlanted = 0;
    private int pitsPlanted = 0;
    private int shieldsPlanted = 0;
    private int score = 0;

    private long lastTimeTeleported;
    private String name;
    private long lastDamageReceived;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBombPower(int bombPower) {
        this.bombPower = bombPower;
    }

    public void setBombAmmo(int bombAmmo) {
        this.bombAmmo = bombAmmo;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public Player() {
        super();
    }

    public Player(Player player) {
        super();
        this.setRectangle(player.getRectangle().clone());
        this.health = player.getHealth();
        this.score = player.getScore();
        this.name = player.getName();
        this.ID = player.ID;
    }

    public abstract PlayersAbstractFactory getFactory();

    public void move(Direction direction) {
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

    public int getScore() {
        return score;
    }

    public void addScore(int score) {
        this.score += score;
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
        return Defaults.color;
    }

    @Override
    public void onCollision(GameObject object) {
        if (object instanceof Box || object instanceof Wall) {
            switch (object.getCollisionDirection(this)) {
				case DOWN:
                    rectangle.setY(object.getRectangle().getSide(Direction.DOWN));
					break;
				case UP:
                    rectangle.setY(
                        object.getRectangle().getSide(Direction.UP) - rectangle.getHeight());
					break;
				case LEFT:
                    rectangle.setX(
                        object.getRectangle().getSide(Direction.LEFT) - rectangle.getWidth());
					break;
				case RIGHT:
                    rectangle.setX(object.getRectangle().getSide(Direction.RIGHT));
					break;
				default:
					break;
            }
        }

        if(object instanceof PowerUp) {

            State.getInstance().removePowerup(object);
            if (object instanceof HealthyPowerUp) {
                if (this instanceof GreenPlayer) {
                    State.getInstance().replacePlayer(this, ((GreenPlayer) this).visitHealthPowerUp((HealthyPowerUp) object));
                } else if (this instanceof BluePlayer) {
                    State.getInstance().replacePlayer(this, ((BluePlayer) this).visitHealthPowerUp((HealthyPowerUp) object));
                } else {
                    State.getInstance().replacePlayer(this, ((BossPlayer) this).visitHealthPowerUp((HealthyPowerUp) object));
                }
            }
            else if (object instanceof SpeedPowerUp) {
                if (this instanceof GreenPlayer) {
                    State.getInstance().replacePlayer(this, ((GreenPlayer) this).visitSpeedPowerUp((SpeedPowerUp) object));
                } else if (this instanceof BluePlayer) {
                    State.getInstance().replacePlayer(this, ((BluePlayer) this).visitSpeedPowerUp((SpeedPowerUp) object));
                } else {
                    State.getInstance().replacePlayer(this, ((BossPlayer) this).visitSpeedPowerUp((SpeedPowerUp) object));
                }
            }
            else if (object instanceof BombAmmoPowerUp) {
                if (this instanceof GreenPlayer) {
                    State.getInstance().replacePlayer(this, ((GreenPlayer) this).visitAmmoPowerUp((BombAmmoPowerUp) object));
                } else if (this instanceof BluePlayer) {
                    State.getInstance().replacePlayer(this, ((BluePlayer) this).visitAmmoPowerUp((BombAmmoPowerUp) object));
                } else {
                    State.getInstance().replacePlayer(this, ((BossPlayer) this).visitAmmoPowerUp((BombAmmoPowerUp) object));
                }
            }
            else if (object instanceof BombPowerPowerUp) {
                if (this instanceof GreenPlayer) {
                    State.getInstance().replacePlayer(this, ((GreenPlayer) this).visitPowerPowerUp((BombPowerPowerUp) object));
                } else if (this instanceof BluePlayer) {
                    State.getInstance().replacePlayer(this, ((BluePlayer) this).visitPowerPowerUp((BombPowerPowerUp) object));
                } else {
                    State.getInstance().replacePlayer(this, ((BossPlayer) this).visitPowerPowerUp((BombPowerPowerUp) object));
                }
            }

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
