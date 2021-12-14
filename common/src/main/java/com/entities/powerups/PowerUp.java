package com.entities.powerups;

import com.entities.players.Player;
import com.entities.Position;
import com.entities.tiles.Tile;
import com.entities.visitor.Visitor;

public abstract class PowerUp extends Tile {
    public PowerUp(Position position) {
        super(position);
    }

    public abstract Player decorate(Player player);

    public abstract void accept(Visitor v);

}
