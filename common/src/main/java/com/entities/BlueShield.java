package com.entities;

import java.awt.*;

public class BlueShield extends Shield{
    public BlueShield(Position position) {
        super(position);
    }

    @Override
    public Color getColor(){
        return new Color(0f,0.5f,1f);
    }

    @Override
    public String getTextureFile() {
        return "src/main/resources/shield_blue.png";
    }
}
