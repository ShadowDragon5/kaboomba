package com.utils;

import com.core.enums.ArithmeticActions;
import com.core.enums.PlayerColors;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

class UtilityMethodsTest {

    @DisplayName(value = "should correctly assign player colors")
    @ParameterizedTest(name = "{index} => when given color is {0} Player color is {1}")
    @MethodSource("providePlayerColors")
    void getPlayerColorOrDefault(String providedColor, PlayerColors playerColor) {
        PlayerColors result = UtilityMethods.getPlayerColorOrDefault(providedColor);
        Assertions.assertEquals(result.toString(), playerColor.toString());
    }

    private static Stream<Arguments> providePlayerColors() {
        return Stream.of(
                Arguments.of("BLUE", PlayerColors.BLUE),
                Arguments.of("GREEN", PlayerColors.GREEN),
                Arguments.of("RANDOM123", PlayerColors.BLUE),
                Arguments.of("RED", PlayerColors.BLUE)
        );
    }

    @DisplayName(value = "should successfully do precise arithmetics")
    @ParameterizedTest(name = "{index} => when given a: {0}, b:{1} and action:{2} result is {1}")
    @MethodSource("provideNumbers")
    void preciseArithmetics(float a, float b, ArithmeticActions action, float result) {
        float preciseResult = UtilityMethods.preciseArithmetics(a, b, action);
        Assertions.assertEquals(preciseResult, result);
    }

    private static Stream<Arguments> provideNumbers() {
        return Stream.of(
                Arguments.of(0.66f, 0.33f, ArithmeticActions.SUM, 0.99f),
                Arguments.of(0.6f, 0.3f, ArithmeticActions.MUL, 0.18f),
                Arguments.of(0.005f, 0.001f, ArithmeticActions.MIN, 0.004f),
                Arguments.of(0.10f, 0.05f, ArithmeticActions.DIV, 2f)
        );
    }

    @Test
    void preciseArithmeticsShouldThrowError() {
        Exception exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            UtilityMethods.preciseArithmetics(1f,2f, ArithmeticActions.valueOf("123"));
        });

        String expectedMessage = "No enum constant com.core.enums.ArithmeticActions.123";
        String actualMessage = exception.getMessage();

        Assertions.assertTrue(actualMessage.contains(expectedMessage));
    }
}