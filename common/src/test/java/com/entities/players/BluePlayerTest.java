package com.entities.players;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.awt.*;

public class BluePlayerTest {
    BluePlayer player = new BluePlayer();

    @Test
    void shouldReturnCorrectDefaultColor() {
        Color correctColor = new Color(0f, 0f, 1f);
        Assertions.assertEquals(player.getColor(), correctColor);
    }

    @Test
    void shouldReturnCorrectDefaultCorrectTextureFile() {
        Assertions.assertEquals(player.getTextureFile(), "src/main/resources/blue_player.png");
    }
}
