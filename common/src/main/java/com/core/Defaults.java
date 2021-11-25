package com.core;

import com.core.enums.PlayerColors;
import com.entities.*;
import com.entities.bomb.Bomb;
import com.entities.bomb.BombExplosion;
import com.entities.boss.BossState;
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
public class Defaults {
    private static float dimension = 0.1f;

    public final static String color = "RED";
    public final static PlayerColors playerColor = PlayerColors.BLUE;

    public static int playerHealth = 3;
    public static int playerPits = 2;
    public static int playerShields = 2;

    public static int scoreBox = 50;
    public static int scoreReceiveDamage = -500;
    public static int scoreDealDamage = 1000;
    public static int scorePortalCost = -100;

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
        .registerTypeAdapter(BossState.class, new CustomJsonAdapter<BossState>())
        .registerTypeAdapter(GameObject.class, new CustomJsonAdapter<GameObject>())
    ).create();

    public static float getDimension() {
        return dimension;
    }

    public static void setDimension(float dimension) {
        Defaults.dimension = dimension;
    }
}
