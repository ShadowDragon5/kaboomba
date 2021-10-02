package com.utils;

import com.core.Globals;
import com.core.PlayerColors;

public class UtilityMethods {
    public static PlayerColors getPlayerColorOrDefault(String color) {

        for (PlayerColors pColor : PlayerColors.values()) {
            if (pColor.name().equals(color)) {
                return pColor;
            }
        }
        return Globals.defaultPlayerColor;
    }
}
