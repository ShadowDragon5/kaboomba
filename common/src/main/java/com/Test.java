package com;

import com.core.ServerAction;
import com.entities.GameMap;
import com.entities.GameObject;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.gsonParsers.GameObjectAdapter;

import java.awt.image.BufferedImage;

public class Test {
    public static void main(String[] args){
        GameMap gameMap = new GameMap();
        gameMap.loadMap("/Users/mi/Desktop/kaboomba/server/src/main/resources/map1.tmx", null);

        GsonBuilder gsonBilder = new GsonBuilder();
        gsonBilder.registerTypeAdapter(GameObject.class, new GameObjectAdapter());
        Gson gson = gsonBilder.create();

        String mapJson = String.format(gson.toJson(gameMap));

        GameMap map = gson.fromJson(mapJson, GameMap.class);
    }
}
