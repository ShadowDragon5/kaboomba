package com.entities.bomb;

import com.core.State;
import com.core.enums.ExplosionDirection;
import com.entities.Position;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;


import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

import java.awt.*;

public class BlueBombTest {

    @Mock
    private State state;

    @Test
    void shouldReturnCorrectDefaultBombPower() {
        //Arrange
        BlueBomb bomb = new BlueBomb(new Position());

        //Assert
        Assertions.assertEquals(bomb.getBombPower(), 1);
    }

    @Test
    void shouldReturnCorrectDefaultLifeSpan() {
        //Arrange
        BlueBomb bomb = new BlueBomb(new Position());

        //Assert
        Assertions.assertEquals(bomb.getLifespan(), 1500l);
    }

    @Test
    void shouldReturnCorrectDefaultColor() {
        //Arrange
        BlueBomb bomb = new BlueBomb(new Position());
        Color correctColor = new Color(0f, 0.5f, 1f);

        //Assert
        Assertions.assertEquals(bomb.getColor(), correctColor);
    }

    @Test
    void shouldReturnCorrectTextureFile() {
        //Arrange
        BlueBomb bomb = new BlueBomb(new Position());

        //Assert
        Assertions.assertEquals(bomb.getTextureFile(), "src/main/resources/bomb_blue.png");
    }

    @Test
    void shouldCreateExplosion() {
        //Arrange
        BlueBomb bomb = new BlueBomb(new Position());
        var expectedExplosion = new BlueBombExplosion(new Position(), ExplosionDirection.CENTER, bomb.getInitiatorId());

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
        BlueBomb bomb = new BlueBomb(new Position());

        //Act
        bomb.setBombPower(2);

        //Assert
        Assertions.assertEquals(bomb.getBombPower(), 2);
    }

    @Test
    void shouldExplode() {
        //Arrange
        BlueBomb bomb = spy(new BlueBomb(new Position()));
        doNothing().when(bomb).handleExplosion();

        //Act
        when(State.getInstance()).thenReturn(null);
        bomb.explode();

        //Assert
        verify(bomb).handleExplosion();
    }




}
