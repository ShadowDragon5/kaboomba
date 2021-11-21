package com.entities.tiles;

import com.core.State;
import com.core.Defaults;
import com.entities.GameObject;
import com.entities.Position;
import com.entities.bomb.BombExplosion;
import com.strategies.box.*;

public class Box extends Tile {
    private BoxExplosion boxExplosion;

    public Box(Position position) {
        super(position);
        randomizeExplosion();
    }

    public Box() {
        super();
        randomizeExplosion();
    }

    public Box(Position position, float dimension) {
        super(position, dimension);
        randomizeExplosion();
    }

    public Box(BoxBuilder builder) {
        super(builder.position, builder.dimension);
        randomizeExplosion();
    }

    public void explode() {
        boxExplosion.explosionEffect(this);
    }

    public void setBoxExplosion(BoxExplosion boxExplosion) {
        this.boxExplosion = boxExplosion;
    }


    @Override
    public String getTextureFile() {
        return "src/main/resources/box.png";
    }

    public void randomizeExplosion() {
        var random = Math.random();
        if (random < 0.4f)
            this.setBoxExplosion(new DestroyBox());
        else if (random < 0.65f)
            this.setBoxExplosion(new DropPowerUp());
        else if (random < 0.90f)
            this.setBoxExplosion(new DamageBox());
        else
            this.setBoxExplosion(new DropBomb());
    }

    @Override
    public void onCollision(GameObject object) {
        if (object instanceof BombExplosion) {
            explode();
            State.getInstance().removeBox(this);
            State.getInstance().getPlayer(object.getInitiatorId()).addScore(Defaults.scoreBox);
        }
    }

    public static class BoxBuilder {
        private final Position position;
        private float dimension;
        private BoxExplosion boxExplosion;

        public BoxBuilder(Position position) {
            this.position = position;
        }

        public BoxBuilder boxExplosion(BoxExplosion boxExplosion) {
            this.boxExplosion = boxExplosion;
            return this;
        }

        public BoxBuilder dimension(float dimension) {
            this.dimension = dimension;
            return this;
        }

        public Box build() {
            Box box = new Box(this);
            return box;
        }
    }
}
