package com.entities.portals.effects;

import com.core.State;
import com.entities.bomb.BaseBomb;
import com.entities.players.Player;

import static com.utils.Scheduler.scheduleTask;

public class SurpriseBombPortalEffect extends PortalEffect {
    @Override
    public void portalEffect(Player player) {
        BaseBomb baseBomb = new BaseBomb(player.getRectangle().clone().snap());
        State.getInstance().addBomb(baseBomb);
        scheduleTask(() -> {
            baseBomb.explode();
            return null;
        }, "DropBombExplode_timer", baseBomb.getLifespan());
    }
}
