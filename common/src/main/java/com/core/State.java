package com.core;

import com.entities.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class State {

    private final ArrayList<Player> players = new ArrayList<>();
    private final ArrayList<GameObject> boxes = new ArrayList<>();

    // Player droppable entities
    private final ArrayList<Bomb> bombs = new ArrayList<>();
    private final ArrayList<Shield> shields = new ArrayList<>();
    private final ArrayList<Pit> pits = new ArrayList<>();

    private State() {}
    private static State state;

    public synchronized static State getInstance() {
        if(state == null) {
            state = new State();
        }
        return state;
    }

    public static void setNewInstance(State newState) {
        state = newState;
    }

    public void addPlayer(Player player) {
        players.add(player);
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }


    public ArrayList<GameObject> getBoxes() {
        return boxes;
    }

    public void addBox(GameObject boxObject) {
        boxes.add(boxObject);
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

    public synchronized void addBomb(Bomb bomb){
        bombs.add(bomb);
    }

    public ArrayList<Bomb> getBombs() {
        ArrayList<Bomb> newBombs = new ArrayList<>();
        bombs.forEach(it->{
            if(it != null)
                newBombs.add(it);
        });
        return newBombs;
    }

    public void addShield(Shield shield) {
        shields.add(shield);
    }

    public ArrayList<Shield> getShields() {
        return shields;
    }

    public void addPit(Pit pit){
        pits.add(pit);
    }

    public ArrayList<Pit> getPits() {
        return pits;
    }

    public void removeBox(GameObject box){
        ((Box) box).explode();
        getBoxes().removeIf(it->it.ID.equals(box.ID));
    }
    public synchronized void removeBomb(GameObject bomb){
//        bombs.removeIf(it->it.ID.equals(bomb.ID));
//        bombs.remove(bomb);
        bombs.set(bombs.indexOf(bomb), null);
    }

}
