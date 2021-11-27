package com.entities.shields;

import com.entities.Position;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;

public class GreenShieldTest {

    private GreenShield shield;

    @BeforeEach
    public void beforeEach() {
        shield = new GreenShield(new Position());
    }

    @Test
    void shouldReturnCorrectDefaultCorrectGreenShieldSettings() {
        Assertions.assertEquals(shield.getTextureFile(), "src/main/resources/shield_green.png");
        Assertions.assertEquals(shield.getColor(), new Color(0f, 1f, 0f));
    }
}
