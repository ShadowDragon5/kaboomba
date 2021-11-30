package com.entities.powerups;

import com.entities.players.Player;
import com.entities.Rectangle;
import com.entities.tiles.Tile;

public abstract class PowerUp extends Tile {
    public PowerUp(Rectangle position) {
        super(position);
    }

    public abstract Player decorate(Player player);

}
