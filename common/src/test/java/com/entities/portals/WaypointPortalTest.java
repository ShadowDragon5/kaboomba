package com.entities.portals;

import com.entities.Position;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class WaypointPortalTest {
    WaypointPortal portal = new WaypointPortal(new Position());

    @Test
    void shouldReturnCorrectDefaultCorrectTextureFile() {
        Assertions.assertEquals(portal.getTextureFile(), "src/main/resources/portal_waypoint.png");
    }
}
