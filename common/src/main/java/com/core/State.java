package com.core;

import com.entities.*;
import java.util.ArrayList;

public class State {

    private final ArrayList<Player> players = new ArrayList<>();

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

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public Player getPlayer(String id) {
        return getPlayers().stream()
                .filter(it -> it.ID.equals(id))
                .findFirst().get();
    }

    public void updateStatePlayer(String id, Player newPlayer) {
        getPlayer(id)
            .setPosition(newPlayer.getPosition());
    }

    public void removePlayer(String id) {
        getPlayers().removeIf(it->it.ID.equals(id));
    }
}
