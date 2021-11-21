package com.entities.shields;

import com.entities.Position;
import com.entities.powerups.BombAmmoPowerUp;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BlueShieldTest {
    BlueShield shield = new BlueShield(new Position());

    @Test
    void shouldReturnCorrectDefaultCorrectTextureFile() {
        Assertions.assertEquals(shield.getTextureFile(), "src/main/resources/shield_blue.png");
    }
}
