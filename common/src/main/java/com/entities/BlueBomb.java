package com.entities;

import java.awt.*;

public class BlueBomb extends Bomb{
    public BlueBomb(Position position) {
        super(position);
    }

    @Override
    public Color getColor() {
        return new Color(0f,0f,1f);
    }
}
