package com.factories.player;

import com.entities.Position;
import com.entities.bomb.Bomb;
import com.entities.bomb.GreenBomb;
import com.entities.pits.Pit;
import com.entities.players.Player;
import com.entities.powerups.HealthyPowerUpDecorator;
import com.entities.shields.Shield;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

class GreenPlayerFactoryTest {
    @Mock
    Player player;
    @InjectMocks
    GreenPlayerFactory greenPlayerFactory;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testCreateBomb() {
        when(player.getBombPower()).thenReturn(0);
        when(player.getPosition()).thenReturn(new Position(0f, 0f));

        Bomb result = greenPlayerFactory.createBomb(new HealthyPowerUpDecorator(new Position(0f, 0f), 0f));
        Assertions.assertEquals(new GreenBomb(new Position(0f, 0f), 0f), result);
    }

    @Test
    void testCreateShield() {
        when(player.getPosition()).thenReturn(new Position(0f, 0f));

        Shield result = greenPlayerFactory.createShield(new HealthyPowerUpDecorator(new Position(0f, 0f), 0f));
        Assertions.assertEquals(new Shield(new Position(0f, 0f), 0f), result);
    }

    @Test
    void testCreatePit() {
        when(player.getPosition()).thenReturn(new Position(0f, 0f));

        Pit result = greenPlayerFactory.createPit(new HealthyPowerUpDecorator(new Position(0f, 0f), 0f));
        Assertions.assertEquals(new Pit(new Position(0f, 0f), 0f), result);
    }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme