package com.entities;

import com.utils.*;

public class Box extends Tile {
    private BoxExplosion boxExplosion;

    public Box(Position position) {
        super(position);
        randomizeExplosion();
    }

    public Box(Position position, float dimension) {
        super(position, dimension);
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
}
