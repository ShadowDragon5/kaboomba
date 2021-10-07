package com.testPatterns;

import com.core.PlayerColors;
import com.entities.Bomb;
import com.entities.Pit;
import com.entities.Player;
import com.entities.Shield;
import com.utils.DefaultPlayerCreator;
import com.utils.PlayerCreator;
import com.utils.PlayersAbstractFactory;

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
