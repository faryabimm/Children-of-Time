package com.childrenOfTime.model;

import com.childrenOfTime.Completed;

/**
 * Created by mohammadmahdi on 5/8/16.
 */
public class Foe extends Warrior {

    protected int healingAmount;
    protected int lowHealthHealingCoef;
    StrengthOfFoes strength;


    //Just For Final Boss
    int attackPowerInHighHealth;
    int heroAttackingNumberPerTurn;
    int[] heroBurningEnergy;


    @Completed
    public Foe(String name,String strength) {
        super(name);
        TypesOfFoes ty = TypesOfFoes.valueOf(name);
        this.strength = StrengthOfFoes.valueOf(strength);
        ty.setStrength(this.strength);

        this.healingAmount = ty.healingAmount;
        super.maxHealth = ty.maximumHealth;
        super.attackPower = ty.attackPower;
        this.heroBurningEnergy = ty.heroBurningEnergy;
        this.heroAttackingNumberPerTurn = ty.heroAttackingNumberPerTurn;


        super.currentHealth = maxHealth;

    }


    public Warrior findTarget() {
        return null;
    }


    @Override
    public String toString() {
        return super.name;
    }
}
