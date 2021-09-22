package com.core;

import com.entities.Player;
import com.entities.Position;
import org.javatuples.Pair;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class State {
    private final ArrayList<Pair<Player, Position>> positions = new ArrayList<>();

    private State(){}
    private static State state;

    public static State getInstance() {
        if(state == null){
            return new State();
        }

        return state;
    }

    public void addPosition(Player player) {
        positions.add(new Pair<>(player, new Position()));
    }

    /*
        Player1, [x:0, y:0]
        Player2, [x:0, y:0]
     */
    public ArrayList<Pair<Player, Position>> getPositions() {
        return positions;
    }

    public Position getPosition(Player player) {
        return getPositions().stream()
                .filter(it->it.getValue0().getName().equals(player.getName()))
                .findFirst().get().getValue1();
    }

    public void updateStatePosition(Player player, Position newPosition) {
        int idx = positions.stream()
                .map(Pair::getValue0)
                .collect(Collectors.toList())
                .indexOf(player);

        var newPair = positions.get(idx).setAt1(newPosition);
        positions.set(idx, newPair);
    }


}
