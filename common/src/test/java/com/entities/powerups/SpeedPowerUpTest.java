package com.entities.powerups;

import com.entities.Position;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SpeedPowerUpTest {
    SpeedPowerUp powerUp = new SpeedPowerUp(new Position());

    @Test
    void shouldReturnCorrectDefaultCorrectTextureFile() {
        Assertions.assertEquals(powerUp.getTextureFile(), "src/main/resources/powerup_speed.png");
    }
}
