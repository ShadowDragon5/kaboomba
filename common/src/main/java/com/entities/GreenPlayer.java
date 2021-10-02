package com.entities;

import com.utils.GreenPlayerFactory;
import com.utils.PlayersAbstractFactory;

import java.awt.*;

public class GreenPlayer extends Player{
    @Override
    public PlayersAbstractFactory getFactory() {
        return new GreenPlayerFactory();
    }

    @Override
    public Color getColor() {
        return new Color(0f,1f,0);
    }
}
