package com.utils;

import com.core.ClientAction;
import org.javatuples.Pair;

import java.util.HashMap;

public class ActionUtils {
    public static Pair<ClientAction, Object> createActionObject(ClientAction action, Object anyObject) {
        return new Pair(action, anyObject);
    }

    public static Pair<ClientAction, Object> createActionObject(ClientAction action) {
        return new Pair(action, null);
    }

}
