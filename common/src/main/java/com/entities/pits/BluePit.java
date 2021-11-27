package com.entities.pits;

import com.core.State;
import com.entities.Position;
import com.entities.players.Player;

import java.awt.*;

import static com.utils.Scheduler.scheduleTask;

public class BluePit extends Pit{
    public BluePit(Position position) {
        super(position);
    }

    @Override
    public void triggerPit(Player player) {
        if (player.getSpeed() == 0 || this.initiatorId == player.ID)
            return;
        var playerSpeed = player.getSpeed();
        player.setSpeed(0);
        var trueSpeed = playerSpeed - player.getSpeed();
        if(player.getSpeed() > 0)
        {
            player.setSpeed(-player.getSpeed());
        }

        State.getInstance().removePit(this);
        scheduleTask(() -> {
            System.out.println("Freeze finished. Speed = " + playerSpeed);
            if (player.getSpeed() == 0)
                player.setSpeed(trueSpeed);
            return null;
        }, "PlayerFreeze_Timer", this.getLifespan());
    }

    @Override
    public Color getColor(){
        return new Color(0f,0.5f,1f);
    }

    @Override
    public String getTextureFile() {
        return "src/main/resources/trap_blue.png";
    }
}
