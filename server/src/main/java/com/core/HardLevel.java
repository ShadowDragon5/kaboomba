package com.core;

import com.entities.GameMap;

public class HardLevel extends Level {

    @Override
    protected void loadMap() {
        // TODO add harder map
        GameMap.getInstance().loadMap("src/main/resources/map1.tmx");
    }

    @Override
    protected void setVariables() {
        Defaults.playerHealth = 6;
        Defaults.playerPits = 1;
        Defaults.playerShields = 1;

        Defaults.scoreBox = 25;
        Defaults.scoreReceiveDamage = -750;
        Defaults.scoreDealDamage = 500;
        Defaults.scorePortalCost = -200;
    }
}
