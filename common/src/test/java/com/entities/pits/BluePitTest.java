package com.entities.pits;

import com.entities.Position;
import com.entities.players.BluePlayer;
import com.entities.players.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.mockito.Mock;

import java.awt.*;

import static org.mockito.Mockito.*;

public class BluePitTest {

    @Mock
    private BluePlayer bp;

    private BluePit pit;

    @BeforeEach
    public void beforeEach() {
        pit = new BluePit(new Position());
    }

    @Test
    void shouldReturnCorrectDefaultSettings() {
        Assertions.assertEquals(pit.getColor(), new Color(0f, 0.5f, 1f));
        Assertions.assertEquals(pit.getTextureFile(), "src/main/resources/trap_blue.png");
    }

    @Test
    void shouldTriggerPit() {
        Player p = new BluePlayer();

        var spyP = spy(p);

        doNothing().when(spyP).setSpeed(0);

        pit.triggerPit(spyP);

        verify(spyP, times(1)).setSpeed(0);
    }
}
