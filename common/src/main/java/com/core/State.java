package com.core;

import com.entities.*;
import com.entities.bomb.BombExplosion;
import com.entities.pits.Pit;
import com.entities.players.NullPlayer;
import com.entities.players.Player;
import com.entities.shields.Shield;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class State {

    private final List<Player> players = Collections.synchronizedList(new ArrayList<>());
    private final List<GameObject> boxes = Collections.synchronizedList(new ArrayList<>());
    private final List<GameObject> portals = Collections.synchronizedList(new ArrayList<>());
    private final List<GameObject> powerups = Collections.synchronizedList(new ArrayList<>());

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

    public void loadState(State newState) {
        this.state = newState;
    }

    public State clone() {
        var newState = new State();
        this.players.forEach(it->newState.addPlayer(it.clone()));
        this.boxes.forEach(it->newState.addBox(it));
        this.powerups.forEach(it->newState.addPowerup(it));
        this.portals.forEach(it->newState.addPortal(it));

        return newState;
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

    public void addPortal(GameObject portalObject) {
        portals.add(portalObject);
    }

    public List<GameObject> getPowerups() {
        return powerups;
    }

    public void addPowerup(GameObject gameObject) {
        powerups.add(gameObject);
    }

    public Player getPlayer(String id) {
        return getPlayers().stream()
                .filter(it -> it.ID.equals(id))
                .findFirst().orElse(new NullPlayer(id));
    }

    public void replacePlayer(Player oldPlayer, Player newPlayer) {
        removePlayer(oldPlayer.ID);
        addPlayer(newPlayer);
    }

    public void removePlayer(String id) {
        getPlayers().removeIf(it->it.ID.equals(id));
    }

    public void removeDeadPlayers() {
        getPlayers().removeIf(Player::isDead);
    }

    public void addBomb(GameObject bomb){
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

    public void addPit(GameObject pit){
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

    public void removePowerup(GameObject powerup) {
        removeFromList(getPowerups(), powerup.ID);
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

    public List<GameObject> getPortals() {
        return portals;
    }

    public void removeFromList(List<GameObject> objects, String id) {
        objects.removeIf(it->it.ID.equals(id));
    }
}
