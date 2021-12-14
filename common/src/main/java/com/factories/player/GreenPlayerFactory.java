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
        GreenBomb greenBomb = new GreenBomb(player.getRectangle().clone().snap());
        greenBomb.setInitiatorId(player.ID);
        greenBomb.setBombPower(player.getBombPower());

        return greenBomb;
    }

    @Override
    public Shield createShield(Player player) {
        GreenShield greenShield = new GreenShield(player.getRectangle().clone().snap());
        greenShield.setInitiatorId(player.ID);

        return greenShield;
    }

    @Override
    public Pit createPit(Player player) {
        GreenPit greenPit = new GreenPit(player.getRectangle().clone().snap());
        greenPit.setInitiatorId(player.ID);

        return greenPit;
    }
}
