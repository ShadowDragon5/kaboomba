package com.controllers;

import com.core.Direction;
import com.core.Globals;
import com.core.State;
import com.entities.*;

import java.util.ArrayList;

public class BombExplosionController {
    private final GameMap gameMap = GameMap.getInstance();

    int explosionSize = Globals.getDefaultExplosionSize();
    float dim = Globals.getDefaultDimension();

    public ArrayList<BombExplosion> createExplosion(Bomb bomb) {

        var explosions = new ArrayList<BombExplosion>();
        explosions.add(bomb.createExplosion(bomb.getPosition()));

        explosions.addAll(generateExplosions(Direction.UP, bomb));
        explosions.addAll(generateExplosions(Direction.LEFT, bomb));
        explosions.addAll(generateExplosions(Direction.RIGHT, bomb));
        explosions.addAll(generateExplosions(Direction.DOWN, bomb));

        return explosions;
    }

    private ArrayList<BombExplosion> generateExplosions(Direction direction, Bomb bomb) {
        var explosions = new ArrayList<BombExplosion>();
        Position initialPosition = bomb.getPosition();

        for(int i = 1; i<explosionSize + 1; i++) {
            Position newPos = new Position(initialPosition.getX(), initialPosition.getY());

            switch (direction) {
                case UP:
                    newPos.addY(dim * i);
                    break;
                case DOWN:
                    newPos.addY(-dim * i);
                    break;
                case LEFT:
                    newPos.addX(-dim * i);
                    break;
                case RIGHT:
                    newPos.addX(dim * i);
                    break;
            }

            GameObject object = atPosition(newPos);

            if (object instanceof Box) {
                BombExplosion explosion = bomb.createExplosion(newPos);
                object.onCollision(explosion);
                explosions.add(explosion);
                break;
            } else if (object instanceof Wall || object instanceof Shield) {
                break;
            } else {
                explosions.add(bomb.createExplosion(newPos));
            }
        }

        return explosions;
    }


    private GameObject atPosition(Position position) {
        var state = State.getInstance();

        // Check if at given position exists GameObject from state
        var gameObjects = new ArrayList<GameObject>();
        gameObjects.addAll(state.getBoxes());
        gameObjects.addAll(state.getShields());

        GameObject gameObject = gameObjects.stream()
                .filter(it -> it.getPosition().equals(position))
                .findFirst().orElse(null);

        if (gameObject != null) {
            return gameObject;
        }

        //Tile from GameMap
        return gameMap.getGameObjects().stream()
                .filter(it -> it.getPosition().equals(position))
                .findFirst().orElse(null);
    }
}
