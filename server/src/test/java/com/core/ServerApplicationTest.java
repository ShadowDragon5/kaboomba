package com.core;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;


@RunWith(PowerMockRunner.class)
@PrepareForTest(ServerApplication.class)
class ServerApplicationTest {

    @Test
    @DisplayName(value = "Server starts correctly")
    public void main() throws Exception {
        HardLevel mockObject = PowerMockito.mock(HardLevel.class);
        PowerMockito.whenNew(HardLevel.class).withNoArguments().thenReturn(mockObject);

        ServerApplication.main();

        PowerMockito.verifyNew(HardLevel.class);
    }
}