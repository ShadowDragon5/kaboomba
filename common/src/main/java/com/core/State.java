package com.core;

import com.entities.*;
import java.util.ArrayList;

public class State {

    private final ArrayList<Player> players = new ArrayList<>();
    private final ArrayList<GameObject> boxes = new ArrayList<>();
    private final ArrayList<GameObject> extras = new ArrayList<>();

    // Player droppable entities
    private final ArrayList<Bomb> bombs = new ArrayList<>();
    private final ArrayList<Shield> shields = new ArrayList<>();
    private final ArrayList<Pit> pits = new ArrayList<>();

    // Explosion
    private final ArrayList<BombExplosion> explosions = new ArrayList<>();

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

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void addPlayer(Player player) {
        players.add(player);
    }

    public ArrayList<GameObject> getBoxes() {
        return boxes;
    }

    public void addBox(GameObject boxObject) {
        boxes.add(boxObject);
    }

    public ArrayList<GameObject> getExtras() {
        return extras;
    }

    public void addExtras(GameObject gameObject) {
        extras.add(gameObject);
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

    public void addBomb(Bomb bomb){
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
        ArrayList<Shield> newShields = new ArrayList<>();
        shields.forEach(it->{
            if(it != null)
                newShields.add(it);
        });
        return newShields;
    }

    public void addPit(Pit pit){
        pits.add(pit);
    }

    public ArrayList<Pit> getPits() {
        ArrayList<Pit> newPits = new ArrayList<>();
        pits.forEach(it->{
            if(it != null)
                newPits.add(it);
        });
        return newPits;
    }

    public void removeBox(GameObject box) {
        ((Box) box).explode();
        getBoxes().removeIf(it->it.ID.equals(box.ID));
    }

    public void removeBomb(GameObject bomb) {
        bombs.set(bombs.indexOf(bomb), null);
    }

    public void removeShield(GameObject shield) {
        shields.set(shields.indexOf(shield), null);
    }

    public void removePit(GameObject pit) {
        pits.set(pits.indexOf(pit), null);
    }

    public void addBombExplosion(ArrayList<BombExplosion> explosion) {
        explosions.addAll(explosion);
    }

    public void removeExplosion(BombExplosion explosion) {
        getExplosions().removeIf(it->it.ID.equals(explosion.ID));
    }

    public ArrayList<BombExplosion> getExplosions() {
        return explosions;
    }
}
