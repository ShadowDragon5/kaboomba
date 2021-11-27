package com.entities.pits;

import com.entities.Position;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;

public class GreenPitTest {
    private GreenPit pit;

    @BeforeEach
    public void beforeEach() {
       pit = new GreenPit(new Position());
    }

    @Test
    void shouldReturnCorrectDefaultSettings() {
        Assertions.assertEquals(pit.getColor(), new Color(0f, 1f, 0f));
        Assertions.assertEquals(pit.getTextureFile(), "src/main/resources/trap_green.png");
    }
}
