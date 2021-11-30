package com.entities.pits;

import com.entities.Rectangle;
import com.entities.players.Player;

import java.awt.*;

public class GreenPit extends Pit {
    public GreenPit(Rectangle position) {
        super(position);
    }

    @Override
    public void triggerPit(Player player) {
        if (this.initiatorId == player.ID)
            return;
        player.decreaseHealth();
    }

    @Override
    public Color getColor() {
        return new Color(0f,1f,0f);
    }

    @Override
    public String getTextureFile() {
        return "src/main/resources/trap_green.png";
    }
}
