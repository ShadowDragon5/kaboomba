package com.entities.bomb;

import com.core.enums.ExplosionDirection;
import com.entities.Rectangle;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.mockito.Mockito;

import static org.mockito.Mockito.*;

import java.awt.*;

class BlueBombTest {
    public Bomb bomb;

    @BeforeEach
    public void setUp() {
        bomb = Mockito.spy(new BlueBomb(new Rectangle()));
    }

    @AfterEach
    public void tearDown() {
        Mockito.reset(bomb);
    }

    @Test
    void shouldReturnCorrectDefaultBombSettings() {
        Assertions.assertEquals(bomb.getBombPower(), 1);
        Assertions.assertEquals(bomb.getLifespan(), 1500L);
        Assertions.assertEquals(bomb.getColor(), new Color(0f, 0.5f, 1f));
        Assertions.assertEquals(bomb.getTextureFile(), "src/main/resources/bomb_blue.png");
    }


    @Test
    void shouldCreateExplosion() {
        var expectedExplosion = new BlueBombExplosion(new Rectangle(), ExplosionDirection.CENTER, bomb.getInitiatorId());
        var bombExplosion = bomb.createExplosion(new Rectangle(), ExplosionDirection.CENTER);

        Assertions.assertEquals(bombExplosion.getInitiatorId(), expectedExplosion.getInitiatorId());
        Assertions.assertEquals(bombExplosion.getCenterTexture(), expectedExplosion.getCenterTexture());
        Assertions.assertEquals(bombExplosion.getLifespan(), expectedExplosion.getLifespan());
    }

    @Test
    void shouldExplode() {
        doNothing().when(bomb).handleExplosion();

        bomb.explode();

        verify(bomb).handleExplosion();
    }
}
