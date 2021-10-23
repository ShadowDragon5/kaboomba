package com.utils;

import com.core.enums.ArithmeticActions;
import com.core.Globals;
import com.core.enums.PlayerColors;

public class UtilityMethods {
    public static PlayerColors getPlayerColorOrDefault(String color) {

        for (PlayerColors pColor : PlayerColors.values()) {
            if (pColor.name().equals(color)) {
                return pColor;
            }
        }
        return Globals.defaultPlayerColor;
    }

    public static float preciseArithmetics(float a, float b, ArithmeticActions action) {
        int precision = 1000;
        float a1 = a * precision;
        float b1 = b * precision;

        switch (action) {
            case SUM:
                return (a1 + b1) / precision;
            case MIN:
                return (a1 - b1) / precision;
            case DIV:
                return (a1 / b1);
            case MUL:
                return (a1 * b1) / precision / precision;
        }
        throw new ArithmeticException("Failed to do precise arithmetics");
    }
}
