package com.entities.shields;

import com.entities.Position;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GreenShieldTest {
    GreenShield shield = new GreenShield(new Position());

    @Test
    void shouldReturnCorrectDefaultCorrectTextureFile() {
        Assertions.assertEquals(shield.getTextureFile(), "src/main/resources/shield_green.png");
    }
}
