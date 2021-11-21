package com.core;

public abstract class Level {
    public final void loadLevel() {
        loadMap();
        setVariables();
    }

    abstract void loadMap();
    abstract void setVariables();
}
