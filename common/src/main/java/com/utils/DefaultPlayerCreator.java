package com.utils;

import com.core.PlayerColors;
import com.entities.BluePlayer;
import com.entities.GreenPlayer;
import com.entities.Player;
import com.entities.RedPlayer;

public class DefaultPlayerCreator extends PlayerCreator{
    @Override
    public Player createFactory(PlayerColors color) {
        switch (color){
            case RED:
                return new RedPlayer();
            case BLUE:
                return new BluePlayer();
            case GREEN:
                return new GreenPlayer();
        }
        return null;
    }
}
