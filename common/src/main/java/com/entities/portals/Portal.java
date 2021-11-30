package com.entities.portals;

import com.entities.GameObject;
import com.entities.Rectangle;
import com.entities.players.Player;
import com.entities.portals.effects.PortalEffect;
import com.entities.tiles.Tile;
import com.entities.portals.effects.*;

public abstract class Portal extends Tile {

    protected PortalEffect portalEffect;

    public Portal(Rectangle position) {
        super(position);
    }

    public PortalEffect getPortalEffect() {
        return portalEffect;
    }

    public void setPortalEffect(PortalEffect portalEffect) {
        this.portalEffect = portalEffect;
    }

    public void setPortalEffect(int effectID) {
        switch (effectID) {
            case 0:
                this.portalEffect = new ReducePointsPortalEffect();
                break;
            case 1:
                this.portalEffect = new SurpriseBombPortalEffect();
                break;
        }
    }

    abstract void teleport(GameObject object);

    @Override
    public void onCollision(GameObject object) {
        if (!(object instanceof Player)) {
            return;
        }

        var player = (Player) object;

        if(!player.canTeleport()){
            return;
        }

        player.setLastTimeTeleported(System.currentTimeMillis());
        teleport(object);
    }
}
