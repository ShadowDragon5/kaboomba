package com.entities.portals.effects;

import com.entities.players.Player;

public class ReducePointsPortalEffect extends PortalEffect{
    @Override
    public void portalEffect(Player player) {
        player.addScore(-100);
        System.out.println("Points reduced");
    }
}
