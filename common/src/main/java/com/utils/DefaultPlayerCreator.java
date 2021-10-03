package com.utils;

import com.core.PlayerColors;
import com.entities.BluePlayer;
import com.entities.GreenPlayer;
import com.entities.Player;

public class DefaultPlayerCreator extends PlayerCreator{
    @Override
    public Player createPlayer(PlayerColors color) {
        switch (color){
            case BLUE:
                return new BluePlayer();
            case GREEN:
                return new GreenPlayer();
        }
        return null;
    }
}
