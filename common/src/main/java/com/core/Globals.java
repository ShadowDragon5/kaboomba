package com.core;

import com.entities.*;
import com.utils.*;
import com.gsonParsers.CustomJsonAdapter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

// collection of global variables
public class Globals {
    private static float defaultDimension = 0.1f;

    public static PlayerColors defaultPlayerColor = PlayerColors.BLUE;
    // Register gson custom serializers/deserializers
    public static Gson gson = (new GsonBuilder()
        .registerTypeAdapter(GameObject.class, new CustomJsonAdapter<GameObject>())
        .registerTypeAdapter(Player.class, new CustomJsonAdapter<Player>())
        .registerTypeAdapter(Bomb.class, new CustomJsonAdapter<Bomb>())
        .registerTypeAdapter(Shield.class, new CustomJsonAdapter<Shield>())
        .registerTypeAdapter(BoxExplosion.class, new CustomJsonAdapter<BoxExplosion>("com.utils."))
        .registerTypeAdapter(Pit.class, new CustomJsonAdapter<Pit>())
        ).create();

    public static float getDefaultDimension() {
        return defaultDimension;
    }

    public static void setDefaultDimension(float dimension) {
        Globals.defaultDimension = dimension;
    }
}
