package com.entities;

import com.core.enums.ArithmeticActions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyFloat;
import static org.mockito.Mockito.*;

class PositionTest {
    @DisplayName("should snap correctly")
    @ParameterizedTest
    @MethodSource("providePositions")
    void snap(Position positionToSpy, Position positionResult) {
        Position spyPosition = getPositionSpy(positionToSpy);
        Position newPosition = spyPosition.snap();

        assertEquals(newPosition.getX(), positionResult.getX());
        assertEquals(newPosition.getY(), positionResult.getY());

        verify(spyPosition).setX(anyFloat());
        verify(spyPosition).setY(anyFloat());
    }

    private static Stream<Arguments> providePositions() {
        return Stream.of(
                Arguments.of(new Position(10f, 10f), new Position(10.05f, 10.05f)),
                Arguments.of(new Position(10.3f, 10.39f), new Position(10.35f, 10.35f)),
                Arguments.of(new Position(10.46f, 10.89f), new Position(10.45f, 10.85f))
        );
    }

    /**
     * Create spy for position
     * @param position
     * @return
     */
    private Position getPositionSpy(Position position) {
        return spy(new Position(position.getX(), position.getY()));
    }
}