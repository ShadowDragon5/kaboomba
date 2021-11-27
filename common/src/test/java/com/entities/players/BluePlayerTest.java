package com.entities.players;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;

public class BluePlayerTest {
    private BluePlayer player;

    @BeforeEach
    public void beforeEach() {
        player = new BluePlayer();
    }

    @Test
    void shouldReturnCorrectDefaultSettings() {
        Assertions.assertEquals(player.getColor(), new Color(0f, 0f, 1f));
        Assertions.assertEquals(player.getTextureFile(), "src/main/resources/blue_player.png");
    }
}
