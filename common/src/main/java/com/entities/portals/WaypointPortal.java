package com.entities.portals;

import com.entities.GameObject;
import com.entities.Position;
import com.entities.portals.effects.PortalEffect;

public class WaypointPortal extends Portal {

    private Portal linkedPortal;

    public WaypointPortal(Position position, PortalEffect portalEffect) {
        super(position, portalEffect);
    }

    @Override
    void teleport(GameObject object) {
        if(linkedPortal == null) return;
        object.setPosition(linkedPortal.getPosition().clone().snap());
    }

    @Override
    public String getTextureFile() {
        return null;
    }

    public Portal getLinkedPortal() {
        return linkedPortal;
    }

    public void setLinkedPortal(Portal linkedPortal) {
        this.linkedPortal = linkedPortal;
    }
}
