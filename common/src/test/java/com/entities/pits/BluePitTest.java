package com.entities.pits;

import com.entities.Position;
import com.entities.bomb.BlueBomb;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import java.awt.*;

public class BluePitTest {
    BluePit pit = new BluePit(new Position());

    @Test
    void shouldReturnCorrectDefaultColor() {
        Color correctColor = new Color(0f, 0.5f, 1f);
        Assertions.assertEquals(pit.getColor(), correctColor);
    }

    @Test
    void shouldReturnCorrectDefaultCorrectTextureFile() {
        Assertions.assertEquals(pit.getTextureFile(), "src/main/resources/trap_blue.png");
    }
}
