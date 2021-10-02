package com.core;

public class Globals {
    private static float defaultDimension = 0.1f;
    public static float getDefaultDimension(){
        return defaultDimension;
    }
    public static void setDefaultDimension( float dimension ){
        Globals.defaultDimension = dimension;
    }

    public static PlayerColors defaultPlayerColor = PlayerColors.RED;
}
