package com.testPatterns;

import com.entities.*;

public class DecoratorTest {
    public static void main(String[] args){
        testDecorator();
    }

    public static void testDecorator(){
        var Player = new BluePlayer();

        var PlayerWithHigherSpeed = new SpeedPowerUp(new BluePlayer());
        var PlayerWithEvenHigherSpeed = new SpeedPowerUp(new SpeedPowerUp(new BluePlayer()));
        System.out.println("Default speed: " + Player.getSpeed() + ",Player higher Speed: " + PlayerWithHigherSpeed.getSpeed() + ",Player even higher Speed: " + PlayerWithEvenHigherSpeed.getSpeed() +"\n");

        var PlayerWithHigherBombPower = new BombPowerPowerUp(new BluePlayer());
        var PlayerWithEvenHigherBombPower = new BombPowerPowerUp(new BombPowerPowerUp(new BluePlayer()));
        System.out.println("Default bomb power: " + Player.getBombPower() + ",Player higher bomb power: " + PlayerWithHigherBombPower.getBombPower() + ",Player even higher bomb power: " + PlayerWithEvenHigherBombPower.getBombPower() +"\n");

        var PlayerWithHigherBombAmmo = new BombAmmoPowerUp(new BluePlayer());
        var PlayerWithEvenHigherBombAmmo = new BombAmmoPowerUp(new BombAmmoPowerUp(new BluePlayer()));
        System.out.println("Default bomb ammo: " + Player.getBombAmmo() + ",Player higher bomb ammo: " + PlayerWithHigherBombAmmo.getBombAmmo() + ",Player even higher bomb ammo: " + PlayerWithEvenHigherBombAmmo.getBombAmmo() +"\n");

        var PlayerWithHigherHealth = new HealthyPowerUp(new BluePlayer());
        var PlayerWithEvenHigherHealth = new HealthyPowerUp(new HealthyPowerUp(new BluePlayer()));
        System.out.println("Default health: " + Player.getHealth() + ",Player higher bomb power: " + PlayerWithHigherHealth.getHealth() + ",Player even higher bomb power: " + PlayerWithEvenHigherHealth.getHealth() +"\n");

    }
}
