package com.entities.bomb;

import com.core.State;
import com.core.enums.ExplosionDirection;
import com.entities.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.mockito.Mock;
import org.mockito.Spy;

import static org.mockito.Mockito.*;

import java.awt.*;

public class BlueBombTest {

    @Mock
    private State state;

    @Spy
    private final BlueBomb bomb = new BlueBomb(new Position());

    @Test
    void shouldReturnCorrectDefaultBombSettings() {
        Assertions.assertEquals(bomb.getBombPower(), 1);
        Assertions.assertEquals(bomb.getLifespan(), 1500L);
        Assertions.assertEquals(bomb.getColor(), new Color(0f, 0.5f, 1f));
        Assertions.assertEquals(bomb.getTextureFile(), "src/main/resources/bomb_blue.png");
    }


    @Test
    void shouldCreateExplosion() {
        var expectedExplosion = new BlueBombExplosion(new Position(), ExplosionDirection.CENTER, bomb.getInitiatorId());
        var bombExplosion = bomb.createExplosion(new Position(), ExplosionDirection.CENTER);

        Assertions.assertEquals(bombExplosion.getInitiatorId(), expectedExplosion.getInitiatorId());
        Assertions.assertEquals(bombExplosion.getCenterTexture(), expectedExplosion.getCenterTexture());
        Assertions.assertEquals(bombExplosion.getLifespan(), expectedExplosion.getLifespan());
    }

    @Test
    void shouldExplode() {
        doNothing().when(bomb).handleExplosion();
        when(State.getInstance()).thenReturn(null);

        bomb.explode();

        verify(bomb).handleExplosion();
    }
}
