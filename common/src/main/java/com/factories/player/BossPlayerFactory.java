package com.factories.player;

import com.entities.bomb.BlueBomb;
import com.entities.bomb.Bomb;
import com.entities.pits.BluePit;
import com.entities.pits.Pit;
import com.entities.players.Player;
import com.entities.shields.BlueShield;
import com.entities.shields.Shield;

public class BossPlayerFactory extends PlayersAbstractFactory {
    public BossPlayerFactory(Player player) {
        super(player);
    }

    @Override
    public Bomb createBomb(Player player) {
        BlueBomb blueBomb = new BlueBomb(player.getPosition().clone().snap());
        blueBomb.setInitiatorId(player.ID);
        blueBomb.setBombPower(player.getBombPower());

        return blueBomb;
    }

    @Override
    public Shield createShield(Player player) {
        BlueShield blueShield = new BlueShield(player.getPosition().clone().snap());
        blueShield.setInitiatorId(player.ID);

        return blueShield;
    }

    @Override
    public Pit createPit(Player player) {
        BluePit bluePit = new BluePit(player.getPosition().clone().snap());
        bluePit.setInitiatorId(player.ID);

        return bluePit;
    }
}
