package com.entities.shields;

import com.entities.Position;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;

public class BlueShieldTest {

    private BlueShield shield;

    @BeforeEach
    public void beforeEach() {
        shield = new BlueShield(new Position());
    }

    @Test
    void shouldReturnCorrectDefaultCorrectBlueShieldSettings() {
        Assertions.assertEquals(shield.getTextureFile(), "src/main/resources/shield_blue.png");
        Assertions.assertEquals(shield.getColor(), new Color(0f, 0.5f, 1f));
    }
}
