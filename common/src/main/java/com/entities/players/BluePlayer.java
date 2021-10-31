package com.entities.players;

import com.factories.player.BluePlayerFactory;
import com.factories.player.PlayersAbstractFactory;

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
        return "src/main/resources/blue_player.png";
    }
}