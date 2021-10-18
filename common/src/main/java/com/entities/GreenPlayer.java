package com.entities;

import com.utils.GreenPlayerFactory;
import com.utils.PlayersAbstractFactory;

import java.awt.*;

public class GreenPlayer extends Player{
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
        return "src/main/resources/player_green.png";
    }
}
