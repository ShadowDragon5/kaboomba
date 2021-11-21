package com.entities.players;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.awt.*;

public class GreenPlayerTest {
    GreenPlayer player = new GreenPlayer();

    @Test
    void shouldReturnCorrectDefaultColor() {
        Color correctColor = new Color(0f, 1f, 0f);
        Assertions.assertEquals(player.getColor(), correctColor);
    }

    @Test
    void shouldReturnCorrectDefaultCorrectTextureFile() {
        Assertions.assertEquals(player.getTextureFile(), "src/main/resources/green_player.png");
    }
}
