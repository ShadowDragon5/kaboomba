package com.entities.pits;

import com.entities.Position;
import com.entities.players.BluePlayer;
import com.entities.players.Player;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.mockito.Mock;

import java.awt.*;

import static org.mockito.Mockito.*;

public class BluePitTest {

    @Mock
    private BluePlayer bp;

    @Test
    void shouldReturnCorrectDefaultColor() {
        BluePit pit = new BluePit(new Position());
        Color correctColor = new Color(0f, 0.5f, 1f);

        Assertions.assertEquals(pit.getColor(), correctColor);
    }

    @Test
    void shouldReturnCorrectDefaultCorrectTextureFile() {
        BluePit pit = new BluePit(new Position());

        Assertions.assertEquals(pit.getTextureFile(), "src/main/resources/trap_blue.png");
    }

    @Test
    void shouldTriggerPit() {
        BluePit pit = new BluePit(new Position());
        Player p = new BluePlayer();

        var spyP = spy(p);

        doNothing().when(spyP).setSpeed(0);

        pit.triggerPit(spyP);

        verify(spyP, times(1)).setSpeed(0);
    }
}
