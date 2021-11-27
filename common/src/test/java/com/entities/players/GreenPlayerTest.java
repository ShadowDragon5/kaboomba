package com.entities.players;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;

public class GreenPlayerTest {
    private GreenPlayer player;

    @BeforeEach
    public void beforeEach() {
        player = new GreenPlayer();
    }

    @Test
    void shouldReturnCorrectDefaultColor() {
        Assertions.assertEquals(player.getColor(), new Color(0f, 1f, 0f));
        Assertions.assertEquals(player.getTextureFile(), "src/main/resources/green_player.png");
    }
}
