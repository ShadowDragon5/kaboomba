package com.core;

import com.core.enums.PlayerColors;
import com.entities.*;
import com.entities.bomb.Bomb;
import com.entities.bomb.BombExplosion;
import com.entities.pits.Pit;
import com.entities.players.Player;
import com.entities.powerups.PowerUp;
import com.entities.powerups.PowerUpDecorator;
import com.entities.shields.Shield;
import com.entities.tiles.Tile;
import com.entities.tiles.Wall;
import com.strategies.box.BoxExplosion;
import com.gsonParsers.CustomJsonAdapter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

// collection of global variables
public class Globals {
    private static float defaultDimension = 0.1f;
    private final static int defaultExplosionSize = 1;

    public static PlayerColors defaultPlayerColor = PlayerColors.BLUE;
    // Register gson custom serializers/deserializers
    public static Gson gson = (new GsonBuilder()
        .registerTypeAdapter(Tile.class, new CustomJsonAdapter<Tile>("com.entities.tiles."))
        .registerTypeAdapter(Player.class, new CustomJsonAdapter<Player>("com.entities.players."))
        .registerTypeAdapter(Shield.class, new CustomJsonAdapter<Shield>("com.entities.shields."))
        .registerTypeAdapter(Bomb.class, new CustomJsonAdapter<Bomb>("com.entities.bomb."))
        .registerTypeAdapter(BoxExplosion.class, new CustomJsonAdapter<BoxExplosion>("com.strategies.box."))
        .registerTypeAdapter(Pit.class, new CustomJsonAdapter<Pit>("com.entities.pits."))
        .registerTypeAdapter(BombExplosion.class, new CustomJsonAdapter<BombExplosion>("com.entities.bomb."))
        .registerTypeAdapter(PowerUp.class, new CustomJsonAdapter<PowerUp>("com.entities.powerups."))
        .registerTypeAdapter(PowerUpDecorator.class, new CustomJsonAdapter<PowerUpDecorator>("com.entities.powerups."))
        .registerTypeAdapter(GameObject.class, new CustomJsonAdapter<GameObject>("com.entities."))
    ).create();

    public static float getDefaultDimension() {
        return defaultDimension;
    }

    public static void setDefaultDimension(float dimension) {
        Globals.defaultDimension = dimension;
    }

    public static int getDefaultExplosionSize() {
        return defaultExplosionSize;
    }
}
