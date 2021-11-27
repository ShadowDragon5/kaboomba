package com.core;

import com.esotericsoftware.kryonet.Server;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.powermock.api.mockito.PowerMockito;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ServerApplicationTest {

    @Mock
    public HardLevel level;

    @Mock
    public Server server;

    @BeforeEach
    public void setUp() {
        level = Mockito.spy(new HardLevel());
        server = Mockito.spy(new Server());
    }

    @AfterEach
    public void tearDown() {
        Mockito.reset(level, server);
    }

    @Test
    @DisplayName(value = "Server starts correctly")
    void main() throws Exception {
        HardLevel myClassMock = Mockito.spy(new HardLevel());
        PowerMockito.whenNew(HardLevel.class).withNoArguments().thenReturn(myClassMock);

        doNothing().when(myClassMock).loadLevel();

        ServerApplication.main();

        verify(myClassMock).loadLevel();
    }
}