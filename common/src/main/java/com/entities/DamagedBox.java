package com.entities;

public class DamagedBox extends Box {
    @Override
    public void explode() {}

    @Override
    public String getTextureFile() {
        return "src/main/resources/box_damaged.png";
    }
}
