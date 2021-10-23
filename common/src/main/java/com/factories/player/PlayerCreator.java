package com.factories.player;

import com.core.enums.PlayerColors;
import com.entities.players.Player;

public abstract class PlayerCreator {

    public abstract Player createPlayer(PlayerColors color);
}
