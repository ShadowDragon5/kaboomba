package com.entities.portals;

import com.core.State;
import com.entities.GameMap;
import com.entities.GameObject;
import com.entities.Position;
import com.entities.players.Player;
import com.entities.portals.effects.PortalEffect;
import com.entities.tiles.Box;
import com.entities.tiles.Wall;

import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class RandomPortal extends Portal{

    public RandomPortal(Position position) {
        super(position);
    }

    @Override
    void teleport(GameObject object) {
        //todo: implement random position by checking GameMap
        boolean positionMatched = true;
        var mergedObjects = Stream.concat(
                GameMap.getInstance().getGameObjects().stream().filter(it->it instanceof Wall),
                State.getInstance().getBoxes().stream()).collect(Collectors.toList());
        while (positionMatched) {
            float randomX = (float) (Math.random() * 2 - 1);
            float randomY = (float) (Math.random() * 2 - 1);
            Position randomPosition = new Position(randomX, randomY).snap();
            positionMatched = mergedObjects.stream().anyMatch(it->it.getPosition().equals(randomPosition));
            if(!positionMatched)
            {
                object.setPosition(randomPosition);
                portalEffect.portalEffect((Player) object);
            }
        }

    }

    @Override
    public String getTextureFile() {
        return "src/main/resources/portal_random.png";
    }
}
