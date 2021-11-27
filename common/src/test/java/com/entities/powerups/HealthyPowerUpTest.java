package com.entities.powerups;

import com.entities.Position;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class HealthyPowerUpTest {
    private HealthyPowerUp powerUp;

    @BeforeEach
    public void beforeEach() {
        powerUp = new HealthyPowerUp(new Position());
    }

    @Test
    void shouldReturnCorrectDefaultCorrectTextureFile() {
        Assertions.assertEquals(powerUp.getTextureFile(), "src/main/resources/powerup_health.png");
    }
}
