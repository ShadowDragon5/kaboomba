package com.entities.portals.effects;

import com.entities.players.Player;
import com.core.Defaults;

public class ReducePointsPortalEffect extends PortalEffect{
    @Override
    public void portalEffect(Player player) {
        player.addScore(Defaults.scorePortalCost);
    }
}
