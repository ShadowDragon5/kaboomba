package com.factories.player;

import com.entities.bomb.Bomb;
import com.entities.bomb.GreenBomb;
import com.entities.pits.GreenPit;
import com.entities.pits.Pit;
import com.entities.players.Player;
import com.entities.shields.GreenShield;
import com.entities.shields.Shield;

public class GreenPlayerFactory extends PlayersAbstractFactory {
    public GreenPlayerFactory(Player player) {
        super(player);
    }

    @Override
    public Bomb createBomb(Player player) {
        GreenBomb greenBomb = new GreenBomb(player.getPosition().clone().snap());
        greenBomb.initiatorId = player.ID;
        greenBomb.setBombPower(player.getBombPower());

        return greenBomb;
    }

    @Override
    public Shield createShield(Player player) {
        GreenShield greenShield = new GreenShield(player.getPosition().clone().snap());
        greenShield.initiatorId = player.ID;

        return greenShield;
    }

    @Override
    public Pit createPit(Player player) {
        GreenPit greenPit = new GreenPit(player.getPosition().clone().snap());
        greenPit.initiatorId = player.ID;

        return greenPit;
    }
}
