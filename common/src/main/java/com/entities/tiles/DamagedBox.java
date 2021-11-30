package com.entities.tiles;

import com.entities.Rectangle;
import com.strategies.box.DestroyBox;
import com.strategies.box.DropBomb;
import com.strategies.box.DropPowerUp;

public class DamagedBox extends Box {
    public DamagedBox(Rectangle position) {
        super(position);
    }

    @Override
    public String getTextureFile() {
        return "src/main/resources/box_damaged.png";
    }

    @Override
    public void randomizeExplosion() {
        var random = Math.random();
        if (random < 0.5f)
            this.setBoxExplosion(new DestroyBox());
        else if (random < 0.75f)
            this.setBoxExplosion(new DropPowerUp());
        else
            this.setBoxExplosion(new DropBomb());
    }
}
