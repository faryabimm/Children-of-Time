package com.childrenOfTime.model;

import com.childrenOfTime.Completed;
import com.childrenOfTime.NotImplementedYet;

import java.util.ArrayList;

/**
 * Created by mohammadmahdi on 5/8/16.
 */
public class Foe extends Warrior {

    protected int healingAmount;
    protected int lowHealthHealingCoef;
    StrengthOfFoes strength;


    //Just For Final Boss
    int attackPowerInHighHealth;
    int attackPowerInLowHealth;
    int heroAttackingNumberPerTurn;
    int[] heroBurningEnergy;


    @Completed
    private void updateFinalBoss() {
        if (this.name.equals("FinalBoss")) {
            if (currentHealth <= 400) {
                strength = StrengthOfFoes.Mutated;
                attackPower = attackPowerInLowHealth;
            } else {
                strength = null;
                attackPower = attackPowerInHighHealth;
            }
        }
    }


    @Completed
    public Foe(String name,String strength) {
        super(name);
        TypesOfFoes ty = TypesOfFoes.valueOf(name);
        try {
            this.strength = StrengthOfFoes.valueOf(strength);
        } catch (Exception e) {
            this.strength = null;
        }

        ty.setStrength(this.strength);

        this.healingAmount = ty.healingAmount;
        super.maxHealth = ty.maximumHealth;
        super.attackPower = ty.attackPower;
        this.heroBurningEnergy = ty.heroBurningEnergy;
        this.heroAttackingNumberPerTurn = ty.heroAttackingNumberPerTurn;
        this.attackPowerInHighHealth = ty.attackPowerInHighHealth;
        this.attackPowerInLowHealth = ty.attackPowerInLowHealth;


        super.currentHealth = maxHealth;

    }

    @Completed
    public void Act1(ChildrenOfTime game) {
        ArrayList<Warrior> targets = new ArrayList();
        Warrior singleTarget;
        switch (name) {
            case "Thug":
                singleTarget = this.findTarget(game);
                singleTarget.changeHealth(-attackPower);
                break;
            case "Angel":
                singleTarget = this.findTarget(game);
                singleTarget.changeHealth(+this.healingAmount);
                break;
            case "Tank":
                //TODO waiting for MMD
                break;
            case "FinalBoss":
                singleTarget = this.findTarget(game);
                updateFinalBoss();
                singleTarget.changeHealth(-attackPower);
                break;
        }
    }

    @Completed
    public void Act2(ChildrenOfTime game) {
        Warrior singleTarget;
        switch (name) {
            case "FinalBoss":
                singleTarget = this.findTarget(game);
                Hero h = (Hero) singleTarget;
                h.setCurrentEnergyPoints(h.getCurrentEnergyPoints() + this.heroBurningEnergy[1]);
                break;

        }

    }

    @NotImplementedYet
    private Warrior findTarget(ChildrenOfTime game) {
        //TODO Dont know How to implement
        return null;
    }


    @Override
    public String toString() {
        return super.name;
    }
}
