package com.entities.portals;

import com.entities.GameObject;
import com.entities.Rectangle;
import com.entities.players.Player;

public class WaypointPortal extends Portal {

    private Rectangle linkedPortalRectangle;

    public WaypointPortal(Rectangle position) {
        super(position);
    }

    @Override
    void teleport(GameObject object) {
        if(linkedPortalRectangle == null) return;
        object.setRectangle(linkedPortalRectangle.clone().snap());
        portalEffect.portalEffect((Player) object);
    }

    @Override
    public String getTextureFile() {
        return "src/main/resources/portal_waypoint.png";
    }

    public Rectangle getLinkedPortalRectangle() {
        return linkedPortalRectangle;
    }

    public void setLinkedPortalRectangle(Rectangle linkedPortalRectangle) {
        this.linkedPortalRectangle = linkedPortalRectangle;
    }
}
