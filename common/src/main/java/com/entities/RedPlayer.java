package com.entities;

import com.utils.PlayersAbstractFactory;
import com.utils.RedPlayerFactory;

import java.awt.*;

public class RedPlayer extends Player{
    @Override
    public PlayersAbstractFactory getFactory() {
        return new RedPlayerFactory();
    }

    @Override
    public Color getColor() {
        return new Color(1f, 0,0);
    }
}
