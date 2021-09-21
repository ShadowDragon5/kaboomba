package com.core;

import com.entities.Position;
import org.javatuples.Pair;

public class ClientActionResolver {
    public Object resolve(Pair<ClientAction, Object> actionObjectPair) {
        switch (actionObjectPair.getValue0()){
            case MOVE_UP:
                Position newPosition = (Position) actionObjectPair.getValue1();
                newPosition.incrementY();
                return newPosition;
            default:
                return null;
        }
    }
}
