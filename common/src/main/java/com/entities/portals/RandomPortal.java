package com.entities.portals;

import com.core.State;
import com.entities.GameMap;
import com.entities.GameObject;
import com.entities.Position;
import com.entities.portals.effects.PortalEffect;

public class RandomPortal extends Portal{

    public RandomPortal(Position position, PortalEffect portalEffect) {
        super(position, portalEffect);
    }

    @Override
    void teleport(GameObject object) {
        //todo: implement random position by checking GameMap
        object.setPosition(new Position(0, 0));
    }

    @Override
    public String getTextureFile() {
        return "src/main/resources/portal_random.png";
    }
}
