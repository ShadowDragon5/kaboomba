package com.entities;

import com.utils.*;

public class Box extends Tile {
    private BoxExplosion boxExplosion;
    private float dimension;

    public Box(Position position, float dimension){
        super(position, dimension);
    }

    public Box(BoxBuilder builder) {
        super(builder.position, builder.dimension);
        randomizeExplosion();
    }

    public void explode() {
        boxExplosion.explosionEffect();
    }

    public void setBoxExplosion(BoxExplosion boxExplosion) {
        this.boxExplosion = boxExplosion;
    }

    @Override
    public String getTextureFile() {
        return "src/main/resources/box.png";
    }

    private void randomizeExplosion(){
        var random = Math.random();
        if(random < 0.4f)                               // 40% chance to destroy box
            this.setBoxExplosion(new DestroyBox());
        else if (random < 0.65)                         // 25% chance to drop power up
            this.setBoxExplosion(new DropPowerUp());
        else if (random < 0.90)                         // 25% chance to damage box
            this.setBoxExplosion(new DamageBox());
        else                                            // 10% chance to drop bomb
            this.setBoxExplosion(new DropBomb());
    }

    public static class BoxBuilder
    {
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
