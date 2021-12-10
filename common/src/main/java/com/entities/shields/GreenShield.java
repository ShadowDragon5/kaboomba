package com.entities.shields;

import com.core.TextureFile;
import com.entities.Rectangle;

import java.awt.*;

public class GreenShield extends Shield{
    public GreenShield(Rectangle rectangle) {
        super(rectangle);
    }

    @Override
    public Color getColor() {
        return new Color(0f,1f,0f);
    }

    @Override
    public String getTextureFile() {
        return TextureFile.SHIELD_GREEN;
    }
}
