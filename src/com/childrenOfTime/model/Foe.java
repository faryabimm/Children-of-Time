package com.childrenOfTime.model;

/**
 * Created by mohammadmahdi on 5/8/16.
 */
public class Foe extends Warrior {

    protected int healingAmount;
    protected int lowHealthLevel;
    protected int lowHealthDamageCoef;
    protected int lowHealthHealingCoef;


    public Foe(FoeStrength foeStrength, FoeClass foeClass) {
    }

    public String getActionMessage() {
        String toReturn = "";

        //TODO implement this

        return toReturn;
    }

}
