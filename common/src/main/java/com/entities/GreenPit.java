package com.entities;

import java.awt.*;

public class GreenPit extends Pit{
    public GreenPit(Position position) {
        super(position);
    }

    @Override
    public Color getColor() {
        return new Color(0f,1f,0f);
    }
}
