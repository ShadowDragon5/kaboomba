package com.entities.shields;

import com.entities.Rectangle;

import java.awt.*;

public class BlueShield extends Shield{
    public BlueShield(Rectangle position) {
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
