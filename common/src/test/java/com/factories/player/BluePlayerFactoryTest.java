package com.factories.player;

import com.entities.Rectangle;
import com.entities.bomb.Bomb;
import com.entities.pits.Pit;
import com.entities.players.BluePlayer;
import com.entities.shields.Shield;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
class BluePlayerFactoryTest {

    public BluePlayerFactory playerFactory;

    public BluePlayer bluePlayer;

    public Rectangle position;

    @BeforeEach
    public void setUp() {
        position = spy(new Rectangle(10f, 10f));

        bluePlayer = spy(new BluePlayer());
        bluePlayer.setRectangle(position);

        playerFactory = new BluePlayerFactory(bluePlayer);

        doReturn(10).when(bluePlayer).getBombPower();
        doReturn(position).when(bluePlayer).getRectangle();
        doReturn(position).when(position).clone();
        doReturn(position).when(position).snap();
    }

    @AfterEach
    public void tearDown() {
        Mockito.reset(bluePlayer, position);
    }

    @Test
    void createBomb() {
        Bomb bomb = playerFactory.createBomb(bluePlayer);

        assertEquals(bomb.getInitiatorId(), bluePlayer.ID);
        assertEquals(bomb.getBombPower(), 10);
        assertEquals(bomb.getRectangle().getX(), 10f);
        assertEquals(bomb.getRectangle().getY(), 10f);

        verify(bluePlayer).getBombPower();
        verify(bluePlayer).getRectangle();
        verify(bluePlayer.getRectangle(), times(2)).clone();
        verify(bluePlayer.getRectangle()).snap();
    }

    @Test
    void createShield() {
        Shield shield = playerFactory.createShield(bluePlayer);

        assertEquals(shield.getInitiatorId(), bluePlayer.ID);
        assertEquals(shield.getRectangle().getX(), 10f);
        assertEquals(shield.getRectangle().getY(), 10f);

        verify(bluePlayer).getRectangle();
        verify(bluePlayer.getRectangle()).clone();
        verify(bluePlayer.getRectangle()).snap();
    }


    @Test
    void createPit() {
        Pit pit = playerFactory.createPit(bluePlayer);

        assertEquals(pit.getInitiatorId(), bluePlayer.ID);
        assertEquals(pit.getRectangle().getX(), 10f);
        assertEquals(pit.getRectangle().getY(), 10f);

        verify(bluePlayer).getRectangle();
        verify(bluePlayer.getRectangle()).clone();
        verify(bluePlayer.getRectangle()).snap();
    }
}
