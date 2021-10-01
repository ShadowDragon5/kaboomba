package com.core;

import com.entities.*;
import java.util.ArrayList;
// import java.util.stream.Collectors;
// import com.entities.Position;
// import org.javatuples.Pair;

public class State {

    private final ArrayList<GameObject> players = new ArrayList<>();
    private final ArrayList<GameObject> objects = new ArrayList<>();

    private State() {}
    private static State state;

    public static State getInstance() {
        if(state == null) {
            return new State();
        }
        return state;
    }

    public void addPlayer(Player player) {
        players.add(player);
    }
    public void addObjects(GameObject gameObject) {
        objects.add(gameObject);
    }

    /*
        Player1, [x:0, y:0]
        Player2, [x:0, y:0]
     */
    public ArrayList<GameObject> getPlayers() {
        return players;
    }

    public Player getPlayer(String id) {
        return (Player) getPlayers().stream()
                .filter(it -> it.ID.equals(id))
                .findFirst().get();
    }

    public void updateStatePlayer(String id, Player newPlayer) {
        // GameObject oldPlayer = getPlayers().stream()
        //                         .filter(it -> it.ID.equals(id))
        //                         .findFirst().get();
        // oldPlayer.setPosition(newPlayer.getPosition());

        getPlayer(id)
            .setPosition(newPlayer.getPosition());
    }


}
