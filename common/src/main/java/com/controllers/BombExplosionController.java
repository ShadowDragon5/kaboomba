package com.controllers;

import com.core.enums.Direction;
import com.core.enums.ExplosionDirection;
import com.core.Defaults;
import com.core.State;
import com.entities.*;
import com.entities.bomb.Bomb;
import com.entities.bomb.BombExplosion;
import com.entities.shields.Shield;
import com.entities.tiles.Box;
import com.entities.tiles.Wall;

import java.util.ArrayList;

public class BombExplosionController {
    float dim = Defaults.getDimension();

    public ArrayList<BombExplosion> createExplosion(Bomb bomb) {

        var explosions = new ArrayList<BombExplosion>();
        explosions.add(bomb.createExplosion(bomb.getRectangle().clonePosition().snap(), ExplosionDirection.CENTER));

        explosions.addAll(generateExplosions(Direction.UP, bomb));
        explosions.addAll(generateExplosions(Direction.LEFT, bomb));
        explosions.addAll(generateExplosions(Direction.RIGHT, bomb));
        explosions.addAll(generateExplosions(Direction.DOWN, bomb));

        return explosions;
    }

    private ArrayList<BombExplosion> generateExplosions(Direction direction, Bomb bomb) {
        var explosions = new ArrayList<BombExplosion>();

        for (int i = 1; i < bomb.getBombPower() + 1; i++) {
            Rectangle newRec = bomb.getRectangle().clonePosition();

            switch (direction) {
                case UP:
                    newRec.addY(dim * i);
                    break;
                case DOWN:
                    newRec.addY(-dim * i);
                    break;
                case LEFT:
                    newRec.addX(-dim * i);
                    break;
                case RIGHT:
                    newRec.addX(dim * i);
                    break;
            }

            GameObject object = atRectangle(newRec);

            var explosionDirection = direction == Direction.DOWN || direction == Direction.UP ?
                ExplosionDirection.VERTICAL : ExplosionDirection.HORIZONTAL;
            if (object instanceof Box) {
                BombExplosion explosion = bomb.createExplosion(newRec, explosionDirection);
                object.onCollision(explosion);
                explosions.add(explosion);
                break;
            } else if (object instanceof Wall || object instanceof Shield) {
                break;
            } else {
                explosions.add(bomb.createExplosion(newRec, explosionDirection));
            }
        }

        return explosions;
    }


    private GameObject atRectangle(Rectangle rectangle) {
        var state = State.getInstance();

        // Check if at given position exists GameObject from state
        var gameObjects = new ArrayList<GameObject>();
        gameObjects.addAll(state.getBoxes());
        gameObjects.addAll(state.getShields());

        GameObject gameObject = gameObjects.stream()
                .filter(it -> it.getRectangle().equals(rectangle))
                .findFirst().orElse(null);

        if (gameObject != null) {
            return gameObject;
        }

        //Tile from GameMap
        return GameMap.getInstance().getGameObjects().stream()
                .filter(it -> it.getRectangle().equals(rectangle))
                .findFirst().orElse(null);
    }
}
