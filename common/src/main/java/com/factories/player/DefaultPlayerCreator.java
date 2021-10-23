package com.factories.player;

import com.core.enums.PlayerColors;
import com.entities.players.BluePlayer;
import com.entities.players.GreenPlayer;
import com.entities.players.Player;

public class DefaultPlayerCreator extends PlayerCreator {
    @Override
    public Player createPlayer(PlayerColors color) {
        switch (color){
            case BLUE:
            {
                return new BluePlayer();
            }

            case GREEN:
                return new GreenPlayer();
        }
        return null;
    }
}
