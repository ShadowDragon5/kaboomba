package com.utils;

import com.entities.BlueBomb;
import com.entities.Bomb;
import com.entities.Player;
import com.entities.Position;

public class BluePlayerFactory extends PlayersAbstractFactory {
    @Override
    public Bomb createBomb(Position position) {
        return new BlueBomb(position);
    }

    @Override
    public Bomb createBomb(Player player) {
        BlueBomb blueBomb = new BlueBomb(new Position(player.getPosition().getX(), player.getPosition().getY()));
        blueBomb.initiatorId = player.ID;

        return blueBomb;
    }
}
