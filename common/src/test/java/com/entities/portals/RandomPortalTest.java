package com.entities.portals;

import com.entities.Position;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class RandomPortalTest {
    RandomPortal portal = new RandomPortal(new Position());

    @Test
    void shouldReturnCorrectDefaultCorrectTextureFile() {
        Assertions.assertEquals(portal.getTextureFile(), "src/main/resources/portal_random.png");
    }
}
