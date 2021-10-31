package com.entities.portals;

import com.entities.GameObject;
import com.entities.Position;
import com.entities.players.Player;
import com.entities.portals.effects.PortalEffect;
import com.entities.tiles.Tile;
import com.utils.Scheduler;

public abstract class Portal extends Tile {

    private PortalEffect portalEffect;

    public Portal(Position position, PortalEffect portalEffect) {
        super(position);
        this.portalEffect = portalEffect;
    }

    public PortalEffect getPortalEffect() {
        return portalEffect;
    }

    public void setPortalEffect(PortalEffect portalEffect) {
        this.portalEffect = portalEffect;
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
        Scheduler.scheduleTask(() -> {
            portalEffect.portalEffect(player);
            return null;
        }, "Portal_effect", 1000L);
    }
}
