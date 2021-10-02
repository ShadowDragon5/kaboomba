package com.entities;

import java.awt.*;

public class RedBomb extends Bomb{
    public RedBomb(Position position) {
        super(position);
    }

    @Override
    public Color getColor() {
        return new Color(1f,0f,0f);
    }
}
