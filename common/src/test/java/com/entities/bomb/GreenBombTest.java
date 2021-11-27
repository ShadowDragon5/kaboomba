package com.entities.bomb;

import com.core.enums.ExplosionDirection;
import com.entities.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import java.awt.*;

import static org.mockito.Mockito.*;

public class GreenBombTest {

    public GreenBomb bomb;

    @BeforeEach
    public void beforeEach() {
        bomb = spy(new GreenBomb(new Position()));
    }

    @Test
    void shouldReturnCorrectDefaultBombSettings() {
        Assertions.assertEquals(bomb.getBombPower(), 1);
        Assertions.assertEquals(bomb.getLifespan(), 4000L);
        Assertions.assertEquals(bomb.getColor(), new Color(0.5f,1f,0f));
        Assertions.assertEquals(bomb.getTextureFile(), "src/main/resources/bomb_green.png");
    }

    @Test
    void shouldCreateExplosion() {
        var expectedExplosion = new GreenBombExplosion(new Position(), ExplosionDirection.CENTER, bomb.getInitiatorId());
        var bombExplosion = bomb.createExplosion(new Position(), ExplosionDirection.CENTER);

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
