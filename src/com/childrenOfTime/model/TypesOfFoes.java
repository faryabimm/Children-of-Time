package com.childrenOfTime.model;

import com.childrenOfTime.Completed;

/**
 * Created by SaeedHD on 05/12/2016.
 */
public enum TypesOfFoes {
    Thug("Thug", "Thug:\n" +
            "Attacks one of your heroes in each turn\n" +
            "Weak version: Attack Power=50, Maximum health=200\n" +
            "Able version: Attack Power=90, Maximum health=300\n" +
            "Mighty version: Attack Power=150, Maximum health=400\n" +
            "Action message: “Thug just attacked “ + (target) + “ with “ + (attack power) + “ power”"),
    Angel("Angel", "Angel:\n" +
            "Heals one of her allies in each turn\n" +
            "Weak version: Healing Amount=100, Maximum health=150\n" +
            "Able version: Healing Amount =150, Maximum health=250\n" +
            "Action message: “Angel just healed “ + (target) + “ with “ + (healing amount) + “ health points”"),
    Tank("Tank", "Tank:\n" +
            "Attacks every one of your heroes in each turn\n" +
            "Weak version: Attack Power=30, Maximum health=400\n" +
            "Able version: Attack Power=90, Maximum health=500\n" +
            "Action message: “Tank just damaged all of your heroes with “ + (attack power) + “ power”"),
    Final("Final Boss", "Final Boss:\n" +
            "Burns 2 to 4 energy points of each hero and attacks 2 of them in each turn\n" +
            "Maximum health: 1000\n" +
            "Attack power when his current health is higher than 400: 150\n" +
            "Attack power when his current health is below 400: 250\n" +
            "Action messages:\n" +
            "“Collector just attacked “ + (target) + “ with ” + (attack power) + “ power”\n" +
            "“Collector just burned “ + (energy point count) + “ energy points from ” + (target)\n" +
            "“Collector has mutated”");
    StrengthOfFoes strength;
    int attackPower;
    int healingAmount;
    int maximumHealth;
    String description;
    String name;

    @Completed
    TypesOfFoes(String name, String description) {
        this.description = description;
        this.name = name;
    }
    //Just For Final Boss
    int attackPowerInLowHealth;
    int attackPowerInHighHealth;
    int heroAttackingNumberPerTurn;
    int[] heroBurningEnergy;


    public void setStrength(TypesOfFoes type, StrengthOfFoes strength) {
        this.strength=strength;
        switch (type) {
            case Thug:
                switch (strength) {
                    case Weak:
                        attackPower = 50;
                        maximumHealth = 200;
                        break;
                    case Able:
                        attackPower = 90;
                        maximumHealth = 300;
                        break;
                    case Mighty:
                        attackPower = 150;
                        maximumHealth = 400;
                        break;

                }
                break;

            case Tank:
                switch (strength) {
                    case Weak:
                        attackPower = 30;
                        maximumHealth = 200;
                        break;
                    case Able:
                        attackPower = 90;
                        maximumHealth = 300;
                        break;
                }
                break;
            case Angel:
                switch (strength) {
                    case Weak:
                        healingAmount = 100;
                        maximumHealth = 150;
                        break;
                    case Able:
                        attackPower = 150;
                        maximumHealth = 250;
                        break;
                }
                break;
            case Final:
                attackPowerInLowHealth = 150;
                attackPowerInHighHealth = 250;
                attackPower = attackPowerInHighHealth;
                maximumHealth = 1000;
                heroAttackingNumberPerTurn = 2;
                int[] h = {2, 4};
                heroBurningEnergy = h;
                break;
        }

    }

}
