package com.utils;

import com.core.Defaults;
import com.core.enums.PlayerColors;

public class UtilityMethods {
    public static PlayerColors getPlayerColorOrDefault(String color) {
        for (PlayerColors pColor : PlayerColors.values()) {
            if (pColor.name().equals(color)) {
                return pColor;
            }
        }
        return Defaults.playerColor;
    }
}
