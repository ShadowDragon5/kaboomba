package com.entities;

import com.utils.BluePlayerFactory;
import com.utils.PlayersAbstractFactory;

import java.awt.*;

public class BluePlayer extends Player {

    public BluePlayer() {
        super();
    }

    public BluePlayer(Player player) {
        super(player);
    }

    @Override
    public Player clone() {
        return new BluePlayer(this);
    }

    @Override
    public PlayersAbstractFactory getFactory() {
        return new BluePlayerFactory(this);
    }

    @Override
    public Color getColor() {
        return new Color(0f,0f,1f);
    }

    @Override
    public String getTextureFile() {
        return "src/main/resources/player_blue.png";
    }
}
