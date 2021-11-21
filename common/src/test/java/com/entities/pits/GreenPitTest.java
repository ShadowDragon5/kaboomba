package com.entities.pits;

import com.entities.Position;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.awt.*;

public class GreenPitTest {
    GreenPit pit = new GreenPit(new Position());

    @Test
    void shouldReturnCorrectDefaultColor() {
        Color correctColor = new Color(0f, 1f, 0f);
        Assertions.assertEquals(pit.getColor(), correctColor);
    }

    @Test
    void shouldReturnCorrectDefaultCorrectTextureFile() {
        Assertions.assertEquals(pit.getTextureFile(), "src/main/resources/trap_green.png");
    }
}
