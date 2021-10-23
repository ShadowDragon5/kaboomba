package com.testPatterns;

import com.core.enums.PlayerColors;
import com.entities.bomb.Bomb;
import com.entities.pits.Pit;
import com.entities.players.Player;
import com.entities.shields.Shield;
import com.factories.player.DefaultPlayerCreator;
import com.factories.player.PlayerCreator;
import com.factories.player.PlayersAbstractFactory;

public class AbstractFactoryTest {
    public static void main(String[] args){
        //Factory
        PlayerCreator playerCreator = new DefaultPlayerCreator();
        Player player = playerCreator.createPlayer(PlayerColors.BLUE);

        //Abstract factory
        PlayersAbstractFactory factory = player.getFactory();

        Shield shield = factory.createShield(player);
        Pit pit = factory.createPit(player);
        Bomb bomb = factory.createBomb(player);

        //Visualise
        System.out.println("Shield type: " + shield.getClass());
        System.out.println("Pit type: " + pit.getClass());
        System.out.println("Bomb type: " + bomb.getClass());
    }
}
