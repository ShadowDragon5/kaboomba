package com.factories.player;

import com.entities.Position;
import com.entities.bomb.Bomb;
import com.entities.players.BluePlayer;
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

    public Position position;

    @BeforeEach
    public void setUp() {
        position = spy(new Position(10f, 10f));

        bluePlayer = spy(new BluePlayer());
        bluePlayer.setPosition(position);

        playerFactory = new BluePlayerFactory(bluePlayer);
    }

    @AfterEach
    public void tearDown() {
        Mockito.reset(bluePlayer, position);
    }

    @Test
    void createBomb() {
        doReturn(10).when(bluePlayer).getBombPower();
        doReturn(position).when(bluePlayer).getPosition();
        doReturn(position).when(position).clone();
        doReturn(position).when(position).snap();

        Bomb bomb = playerFactory.createBomb(bluePlayer);

        assertEquals(bomb.getInitiatorId(), bluePlayer.ID);
        assertEquals(bomb.getBombPower(), 10);
        assertEquals(bomb.getPosition().getX(), 10f);
        assertEquals(bomb.getPosition().getY(), 10f);

        verify(bluePlayer).getBombPower();
        verify(bluePlayer).getPosition();
        verify(bluePlayer.getPosition(), times(2)).clone();
        verify(bluePlayer.getPosition()).snap();
    }

    @Test
    void createShield() {
        // Add similar test
    }

    @Test
    void createPit() {
        // Add similar test
    }
}