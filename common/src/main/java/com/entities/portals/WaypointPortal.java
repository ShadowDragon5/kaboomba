package com.entities.portals;

import com.entities.GameObject;
import com.entities.Position;
import com.entities.players.Player;
import com.entities.portals.effects.PortalEffect;

public class WaypointPortal extends Portal {

    private Position linkedPortalPosition;

    public WaypointPortal(Position position, PortalEffect portalEffect) {
        super(position, portalEffect);
    }

    @Override
    void teleport(GameObject object) {
        if(linkedPortalPosition == null) return;
        object.setPosition(linkedPortalPosition.clone().snap());
        portalEffect.portalEffect((Player) object);
    }

    @Override
    public String getTextureFile() {
        return "src/main/resources/portal_waypoint.png";
    }

    public Position getLinkedPortalPosition() {
        return linkedPortalPosition;
    }

    public void setLinkedPortalPosition(Position linkedPortalPosition) {
        this.linkedPortalPosition = linkedPortalPosition;
    }
}
