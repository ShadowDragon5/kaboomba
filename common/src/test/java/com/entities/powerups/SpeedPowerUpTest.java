package com.entities.powerups;

import com.entities.Rectangle;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SpeedPowerUpTest {
    private SpeedPowerUp powerUp;

    @BeforeEach
    public void beforeEach() {
        powerUp = new SpeedPowerUp(new Rectangle());
    }

    @Test
    void shouldReturnCorrectDefaultCorrectTextureFile() {
        Assertions.assertEquals(powerUp.getTextureFile(), "src/main/resources/powerup_speed.png");
    }
}
