package com.entities.shields;

import com.core.TextureFile;
import com.entities.Rectangle;

import java.awt.*;

public class BlueShield extends Shield {
    public BlueShield(Rectangle position) {
        super(position);
    }

    @Override
    public Color getColor() {
        return new Color(0f,0.5f,1f);
    }

    @Override
    public String getTextureFile() {
        return TextureFile.SHIELD_BLUE;
    }
}
