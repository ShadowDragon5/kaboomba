package com.utils;

import com.entities.Bomb;
import com.entities.RedBomb;

public class RedPlayerFactory extends PlayersAbstractFactory {
    @Override
    public Bomb createBomb() {
        return new RedBomb();
    }
}
