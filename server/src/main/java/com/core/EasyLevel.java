package com.core;

import com.entities.GameMap;

public class EasyLevel extends Level {

    @Override
    protected void loadMap() {
        GameMap.getInstance().loadMap("src/main/resources/map3.tmx");
    }

    @Override
    protected void setVariables() {
        /* Use defaults
         * Defaults.playerHealth = 4;
         * Defaults.playerPits = 3;
         * Defaults.playerShields = 2;
         * Defaults.scoreBox = 25;
         * Defaults.scoreReceiveDamage = -750;
         * Defaults.scoreDealDamage = 500;
         * Defaults.scorePortalCost = -200;
         */
    }
}
