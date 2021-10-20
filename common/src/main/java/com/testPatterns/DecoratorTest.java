package com.testPatterns;

import com.entities.*;

public class DecoratorTest {
    public static void main(String[] args){
        testDecorator();
    }

    public static void testDecorator(){
        var Player = new BluePlayer();

        var PlayerWithHigherSpeed = new SpeedPowerUpDecorator(new BluePlayer());
        var PlayerWithEvenHigherSpeed = new SpeedPowerUpDecorator(new SpeedPowerUpDecorator(new BluePlayer()));
        System.out.println("Default speed: " + Player.getSpeed() + ",Player higher Speed: " + PlayerWithHigherSpeed.getSpeed() + ",Player even higher Speed: " + PlayerWithEvenHigherSpeed.getSpeed() +"\n");

        var PlayerWithHigherBombPower = new BombPowerPowerUpDecorator(new BluePlayer());
        var PlayerWithEvenHigherBombPower = new BombPowerPowerUpDecorator(new BombPowerPowerUpDecorator(new BluePlayer()));
        System.out.println("Default bomb power: " + Player.getBombPower() + ",Player higher bomb power: " + PlayerWithHigherBombPower.getBombPower() + ",Player even higher bomb power: " + PlayerWithEvenHigherBombPower.getBombPower() +"\n");

        var PlayerWithHigherBombAmmo = new BombAmmoPowerUpDecorator(new BluePlayer());
        var PlayerWithEvenHigherBombAmmo = new BombAmmoPowerUpDecorator(new BombAmmoPowerUpDecorator(new BluePlayer()));
        System.out.println("Default bomb ammo: " + Player.getBombAmmo() + ",Player higher bomb ammo: " + PlayerWithHigherBombAmmo.getBombAmmo() + ",Player even higher bomb ammo: " + PlayerWithEvenHigherBombAmmo.getBombAmmo() +"\n");

        var PlayerWithHigherHealth = new HealthyPowerUpDecorator(new BluePlayer());
        var PlayerWithEvenHigherHealth = new HealthyPowerUpDecorator(new HealthyPowerUpDecorator(new BluePlayer()));
        System.out.println("Default health: " + Player.getHealth() + ",Player higher bomb power: " + PlayerWithHigherHealth.getHealth() + ",Player even higher bomb power: " + PlayerWithEvenHigherHealth.getHealth() +"\n");

    }
}
