package com.entities.powerups;

import com.entities.players.Player;
import com.entities.Rectangle;
import com.entities.tiles.Tile;
import com.entities.visitor.Visitor;

public abstract class PowerUp extends Tile {
    public PowerUp(Rectangle rectangle) {
        super(rectangle);
    }

    public abstract Player decorate(Player player);

    public abstract void accept(Visitor v);

}
