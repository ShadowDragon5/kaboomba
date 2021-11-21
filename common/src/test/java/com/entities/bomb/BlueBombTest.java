package com.entities.bomb;

import com.entities.Position;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import java.awt.*;

public class BlueBombTest {
    BlueBomb bomb = new BlueBomb(new Position());

    @Test
    void shouldReturnCorrectDefaultBombPower() {
        Assertions.assertEquals(bomb.getBombPower(), 1);
    }

    @Test
    void shouldReturnCorrectDefaultLifeSpan() {
        Assertions.assertEquals(bomb.getLifespan(), 1500l);
    }

    @Test
    void shouldReturnCorrectDefaultColor() {
        Color correctColor = new Color(0f, 0.5f, 1f);
        Assertions.assertEquals(bomb.getColor(), correctColor);
    }

    @Test
    void shouldReturnCorrectDefaultCorrectTextureFile() {
        Assertions.assertEquals(bomb.getTextureFile(), "src/main/resources/bomb_blue.png");
    }

}
