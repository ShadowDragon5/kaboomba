package com.entities.pits;

import com.entities.Position;

import java.awt.*;

public class BluePit extends Pit{
    public BluePit(Position position) {
        super(position);
    }

    @Override
    public Color getColor(){
        return new Color(0f,0.5f,1f);
    }

    @Override
    public String getTextureFile() {
        return "src/main/resources/trap_blue.png";
    }
}
