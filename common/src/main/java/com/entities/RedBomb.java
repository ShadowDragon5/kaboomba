package com.entities;

import java.awt.*;

public class RedBomb extends Bomb{
    public RedBomb(Position position) {
        super(position);
    }

    @Override
    public Color getColor() {
        return new Color(1f,0.4f,0f);
    }
}
