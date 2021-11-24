package com.entities.bomb;

import com.core.enums.ExplosionDirection;
import com.entities.Position;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import java.awt.*;

import static org.mockito.Mockito.*;

public class GreenBombTest {
    GreenBomb bomb = new GreenBomb(new Position());

    @Test
    void shouldReturnCorrectDefaultBombPower() {
        Assertions.assertEquals(bomb.getBombPower(), 1);
    }

    @Test
    void shouldReturnCorrectDefaultLifeSpan() {
        Assertions.assertEquals(bomb.getLifespan(), 4000l);
    }

    @Test
    void shouldReturnCorrectDefaultColor() {
        Color correctColor = new Color(0.5f,1f,0f);
        Assertions.assertEquals(bomb.getColor(), correctColor);
    }

    @Test
    void shouldReturnCorrectDefaultCorrectTextureFile() {
        Assertions.assertEquals(bomb.getTextureFile(), "src/main/resources/bomb_green.png");
    }

    @Test
    void shouldCreateExplosion() {
        //Arrange
        GreenBomb bomb = new GreenBomb(new Position());
        var expectedExplosion = new GreenBombExplosion(new Position(), ExplosionDirection.CENTER, bomb.getInitiatorId());

        //Act
        var bombExplosion = bomb.createExplosion(new Position(), ExplosionDirection.CENTER);

        //Assert
        Assertions.assertEquals(bombExplosion.getInitiatorId(), expectedExplosion.getInitiatorId());
        Assertions.assertEquals(bombExplosion.getCenterTexture(), expectedExplosion.getCenterTexture());
        Assertions.assertEquals(bombExplosion.getLifespan(), expectedExplosion.getLifespan());
    }

    @Test
    void shouldSetBombPower() {
        //Arrange
        GreenBomb bomb = new GreenBomb(new Position());

        //Act
        bomb.setBombPower(2);

        //Assert
        Assertions.assertEquals(bomb.getBombPower(), 2);
    }

    @Test
    void shouldExplode() {
        //Arrange
        GreenBomb bomb = spy(new GreenBomb(new Position()));
        doNothing().when(bomb).handleExplosion();

        //Act
        bomb.explode();

        //Assert
        verify(bomb).handleExplosion();
    }
}
