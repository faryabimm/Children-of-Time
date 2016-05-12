package com.childrenOfTime.model;

import com.childrenOfTime.InProgress;

/**
 * Created by mohammadmahdi on 5/8/16.
 */
public class Foe extends Warrior {

    protected int healingAmount;
    protected int lowHealthLevel;
    protected int lowHealthAttackpower;
    protected int lowHealthHealingCoef;
    protected int attackPower;
    protected int maxHealth;
    StrengthOfFoes strength;

    //Just For Final Boss
    int attackPowerInHighHealth;
    int heroAttackingNumberPerTurn;
    int[] heroBurningEnergy;

    @InProgress
    public Foe(String name,String strength) {
        super(name);
        TypesOfFoes.valueOf(name);
        StrengthOfFoes.valueOf(strength);
    }

    public String getActionMessage() {
        String toReturn = "";

        //TODO implement this

        return toReturn;
    }

    @Override
    public Warrior findTarget() {
        return null;
    }
}
