package com.entities.portals;

import com.core.State;
import com.core.TextureFile;
import com.entities.GameMap;
import com.entities.GameObject;
import com.entities.Rectangle;
import com.entities.players.Player;
import com.entities.tiles.Wall;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class RandomPortal extends Portal {

    public RandomPortal(Rectangle position) {
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
            Rectangle randomRectangle = new Rectangle(randomX, randomY).snap();
            positionMatched = mergedObjects.stream().anyMatch(it->it.getRectangle().equals(randomRectangle));
            if(!positionMatched)
            {
                object.setRectangle(randomRectangle);
                portalEffect.portalEffect((Player) object);
            }
        }

    }

    @Override
    public String getTextureFile() {
        return TextureFile.PORTAL_RANDOM;
    }
}
