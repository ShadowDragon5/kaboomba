package com.utils;

import com.core.PlayerColors;
import com.entities.*;

public class DefaultPlayerCreator extends PlayerCreator{
    @Override
    public Player createPlayer(PlayerColors color) {
        switch (color){
            case BLUE:
            {
                return new BombPowerPowerUp(new SpeedPowerUp(new BluePlayer()));
            }

            case GREEN:
                return new GreenPlayer();
        }
        return null;
    }
}
