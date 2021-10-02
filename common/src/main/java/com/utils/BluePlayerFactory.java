package com.utils;

import com.entities.BlueBomb;
import com.entities.Bomb;

public class BluePlayerFactory extends PlayersAbstractFactory {
    @Override
    public Bomb createBomb() {
        return new BlueBomb();
    }
}
