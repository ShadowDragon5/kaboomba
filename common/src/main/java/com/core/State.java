package com.core;

import com.entities.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class State {

    private final List<Player> players = Collections.synchronizedList(new ArrayList<>());
    private final List<GameObject> boxes = Collections.synchronizedList(new ArrayList<>());
    private final List<GameObject> extras = Collections.synchronizedList(new ArrayList<>());

    // Player droppable entities
    private final List<GameObject> bombs = Collections.synchronizedList(new ArrayList<>());
    private final List<GameObject> shields = Collections.synchronizedList(new ArrayList<>());
    private final List<GameObject> pits = Collections.synchronizedList(new ArrayList<>());

    // Explosion
    private final List<GameObject> explosions = Collections.synchronizedList(new ArrayList<>());

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

    public List<Player> getPlayers() {
        return players;
    }

    public void addPlayer(Player player) {
        players.add(player);
    }

    public List<GameObject> getBoxes() {
        return boxes;
    }

    public void addBox(GameObject boxObject) {
        boxes.add(boxObject);
    }

    public List<GameObject> getExtras() {
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

    public void removePlayer(String id) {
        getPlayers().removeIf(it->it.ID.equals(id));
    }

    public void addBomb(Bomb bomb){
        getBombs().add(bomb);
    }

    public List<GameObject> getBombs() {
        return bombs;
    }

    public void addShield(Shield shield) {
        getShields().add(shield);
    }

    public List<GameObject> getShields() {
        return shields;
    }

    public void addPit(Pit pit){
        getPits().add((Pit) pit);
    }

    public List<GameObject> getPits() {
        return pits;
    }

    public void removeBox(GameObject box) {
        removeFromList(getBoxes(), box.ID);
    }

    public void removeBomb(GameObject bomb) {
        removeFromList(getBombs(), bomb.ID);
    }

    public void removeShield(GameObject shield) {
        removeFromList(getShields(), shield.ID);
    }

    public void removePit(GameObject pit) {
        removeFromList(getPits(), pit.ID);
    }

    public void addBombExplosion(ArrayList<BombExplosion> explosion) {
        explosion.forEach(it->{
            getExplosions().add(it);
        });
    }

    public void removeExplosion(BombExplosion explosion) {
        removeFromList(getExplosions(), explosion.ID);
    }

    public List<GameObject> getExplosions() {
        return explosions;
    }

    public void removeFromList(List<GameObject> objects, String id) {
        objects.removeIf(it->it.ID.equals(id));
    }
}
