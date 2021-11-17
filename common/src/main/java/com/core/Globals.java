package com.core;

import com.core.enums.PlayerColors;
import com.entities.*;
import com.entities.bomb.Bomb;
import com.entities.bomb.BombExplosion;
import com.entities.pits.Pit;
import com.entities.players.Player;
import com.entities.portals.Portal;
import com.entities.portals.effects.PortalEffect;
import com.entities.powerups.PowerUp;
import com.entities.powerups.PowerUpDecorator;
import com.entities.shields.Shield;
import com.entities.tiles.Tile;
import com.strategies.box.BoxExplosion;
import com.gsonParsers.CustomJsonAdapter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

// collection of global variables
public class Globals {
    private static float defaultDimension = 0.1f;

    public final static int defaultExplosionSize = 1;
    public final static String defaultColor = "RED";

    public static PlayerColors defaultPlayerColor = PlayerColors.BLUE;
    // Register gson custom serializers/deserializers
    public static Gson gson = (new GsonBuilder()
        .registerTypeAdapter(Tile.class, new CustomJsonAdapter<Tile>())
        .registerTypeAdapter(Portal.class, new CustomJsonAdapter<Tile>())
        .registerTypeAdapter(PortalEffect.class, new CustomJsonAdapter<Tile>())
        .registerTypeAdapter(Player.class, new CustomJsonAdapter<Player>())
        .registerTypeAdapter(Shield.class, new CustomJsonAdapter<Shield>())
        .registerTypeAdapter(Bomb.class, new CustomJsonAdapter<Bomb>())
        .registerTypeAdapter(BoxExplosion.class, new CustomJsonAdapter<BoxExplosion>())
        .registerTypeAdapter(Pit.class, new CustomJsonAdapter<Pit>())
        .registerTypeAdapter(BombExplosion.class, new CustomJsonAdapter<BombExplosion>())
        .registerTypeAdapter(PowerUp.class, new CustomJsonAdapter<PowerUp>())
        .registerTypeAdapter(PowerUpDecorator.class, new CustomJsonAdapter<PowerUpDecorator>())
        .registerTypeAdapter(GameObject.class, new CustomJsonAdapter<GameObject>())
    ).create();

    public static float getDefaultDimension() {
        return defaultDimension;
    }

    public static void setDefaultDimension(float dimension) {
        Globals.defaultDimension = dimension;
    }
}
