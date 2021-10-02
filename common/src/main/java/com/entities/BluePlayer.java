package com.entities;

import com.utils.BluePlayerFactory;
import com.utils.PlayersAbstractFactory;

import java.awt.*;

public class BluePlayer extends Player {
    @Override
    public PlayersAbstractFactory getFactory() {
        return new BluePlayerFactory();
    }

    @Override
    public Color getColor() {
        return new Color(0f,0f,1f);
    }
}
