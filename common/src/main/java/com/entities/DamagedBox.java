package com.entities;

import com.utils.DamageBox;
import com.utils.DestroyBox;
import com.utils.DropBomb;
import com.utils.DropPowerUp;

public class DamagedBox extends Box {
    public DamagedBox(Position position) {
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
