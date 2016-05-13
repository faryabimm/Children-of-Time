package com.childrenOfTime.model;

import com.childrenOfTime.Completed;
import com.childrenOfTime.NotImplementedYet;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by mohammadmahdi on 5/8/16.
 */
public class Foe extends Warrior {
    protected int healingAmount;
    TypesOfFoes type;
    StrengthOfFoes strength;


    //Just For Final Boss
    int attackPowerInHighHealth;
    int attackPowerInLowHealth;
    int heroAttackingNumberPerTurn;
    int[] heroBurningEnergy;
    String description;

    @Completed
    public Foe(String name, StrengthOfFoes strength, int id) {
        super(name, id);
        type = TypesOfFoes.valueOf(name.split(" ")[0]);
        this.strength = strength;
        type.setStrength(this.strength);

        this.healingAmount = type.healingAmount;
        super.maxHealth = type.maximumHealth;
        super.attackPower = type.attackPower;
        this.heroBurningEnergy = type.heroBurningEnergy;
        this.heroAttackingNumberPerTurn = type.heroAttackingNumberPerTurn;
        this.attackPowerInHighHealth = type.attackPowerInHighHealth;
        this.attackPowerInLowHealth = type.attackPowerInLowHealth;
        this.description = type.description;

        super.currentHealth = maxHealth;

    }


    @Completed
    private void updateFinalBoss() {
        if (this.name.equals("Final Boss")) {
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
    public void Act1(ChildrenOfTime game) {
        ArrayList<Object> targets = new ArrayList();
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
                for (Warrior w : ChildrenOfTime.getInstance().getPlayers().get(0).getHeros()) {
                    ((Hero) w).changeHealth(-attackPower);
                }
                break;
            case "Final Boss":
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
            case "Final Boss":
                singleTarget = this.findTarget(game);
                Hero h = (Hero) singleTarget;
                h.setCurrentEnergyPoints(h.getCurrentEnergyPoints() + this.heroBurningEnergy[1]);
                break;

        }

    }

    @NotImplementedYet
    private Warrior findTarget(ChildrenOfTime game) {
        Random rand = new Random();
        ArrayList<Hero> allHeroes = ChildrenOfTime.getInstance().getPlayers().get(0).getHeros();
        int n = rand.nextInt(allHeroes.size()) + 1;
        return allHeroes.get(n);
    }


    @Override
    public String toString() {

        return showCurrentTraits();
    }


    @Override
    public boolean equals(Object obj) {
        if (obj == null || obj.getClass() != this.getClass()) return false;
        Foe other = (Foe) obj;
        if (!this.name.equals(other.name)) return false;
        return true;
    }

    @Override
    public String showCurrentTraits() {
        String toPrint = "";
        toPrint += "You’ve encountered";

        for (TypesOfFoes type : TypesOfFoes.values()) {
            for (StrengthOfFoes strength : StrengthOfFoes.values()) {
                int num = 0;
                for (Foe foes : Battle.getFoes()) {
                    if (type.equals(foes.type)) {
                        num++;
                    }
                }
                if (strength != null) {
                    toPrint += num + " " + strength.name + " " + type.name() + " ";
                } else {
                    toPrint += num + " " + type.name() + " ";
                }
            }
        }
        //  printOutput(toPrint);
        return toPrint;
    }

}

