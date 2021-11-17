package com.entities.powerups;

import com.entities.players.Player;
import com.entities.Position;
import com.entities.tiles.Tile;

public abstract class PowerUp extends Tile {
    public PowerUp(Position position) {
        super(position);
    }

    public abstract Player decorate(Player player);

}
