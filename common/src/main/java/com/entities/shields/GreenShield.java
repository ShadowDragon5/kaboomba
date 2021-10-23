package com.entities.shields;

import com.entities.Position;

import java.awt.*;

public class GreenShield extends Shield{
    public GreenShield(Position position) {
        super(position);
    }

    @Override
    public Color getColor() {
        return new Color(0f,1f,0f);
    }

    @Override
    public String getTextureFile() {
        return "src/main/resources/shield_green.png";
    }
}
