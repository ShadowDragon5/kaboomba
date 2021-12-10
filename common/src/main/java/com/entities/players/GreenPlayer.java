package com.entities.players;

import com.core.TextureFile;
import com.factories.player.GreenPlayerFactory;
import com.factories.player.PlayersAbstractFactory;

import java.awt.*;

public class GreenPlayer extends Player {
    public GreenPlayer() {
        super();
    }

    public GreenPlayer(Player player) {
        super(player);
    }

    @Override
    public Player clone() {
        return new GreenPlayer(this);
    }

    @Override
    public PlayersAbstractFactory getFactory() {
        return new GreenPlayerFactory(this);
    }

    @Override
    public Color getColor() {
        return new Color(0f,1f,0);
    }

    @Override
    public String getTextureFile() {
        return TextureFile.GREEN_PLAYER;
    }
}
