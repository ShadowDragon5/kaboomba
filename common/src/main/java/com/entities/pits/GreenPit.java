package com.entities.pits;

import com.entities.Position;

import java.awt.*;

public class GreenPit extends Pit{
    public GreenPit(Position position) {
        super(position);
    }

    @Override
    public Color getColor() {
        return new Color(0f,1f,0f);
    }

    @Override
    public String getTextureFile() {
        return "src/main/resources/trap_green.png";
    }
}
