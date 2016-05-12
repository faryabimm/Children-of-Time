package com.childrenOfTime.model;

/**
 * Created by SaeedHD on 05/12/2016.
 */
public enum TypesOfFoes {
    Thug,
    Angel,
    Tank,
    FinalBoss;
    StrengthOfFoes strength;
    int attackPower;
    int healingAmount;
    int maximumHealth;


    //Just For Final Boss
    int attackPowerInLowHealth;
    int attackPowerInHighHealth;
    int heroAttackingNumberPerTurn;
    int[] heroBurningEnergy;

    public void setStrength(StrengthOfFoes strength){
        this.strength=strength;
        switch (this) {
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
            case FinalBoss:
                attackPowerInLowHealth = 150;
                attackPowerInHighHealth = 250;
                attackPower = attackPowerInHighHealth;
                maximumHealth=1000;
                heroAttackingNumberPerTurn=2;
                int[] h={2,4};
                heroBurningEnergy=h;
        }

    }

}
