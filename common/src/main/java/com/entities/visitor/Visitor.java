package com.entities.visitor;

import com.entities.players.Player;
import com.entities.powerups.BombAmmoPowerUp;
import com.entities.powerups.BombPowerPowerUp;
import com.entities.powerups.HealthyPowerUp;
import com.entities.powerups.SpeedPowerUp;

public interface Visitor {
    Player visitHealthPowerUp(HealthyPowerUp hp);

    Player visitSpeedPowerUp(SpeedPowerUp sp);

    Player visitPowerPowerUp(BombPowerPowerUp pp);

    Player visitAmmoPowerUp(BombAmmoPowerUp ap);
}
