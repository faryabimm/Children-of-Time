package com.childrenOfTime.model;

import com.childrenOfTime.Completed;
import com.childrenOfTime.InProgress;

/**
 * Created by SaeedHD on 05/13/2016.
 */
public enum InformationOfAbilities {
    Fight("Fight training", -1, 2, 3, 4, 0, 0),
    Work("Work out", -1, 2, 3, 4, 0, 0),
    Quick("Quick as a bunny", -1, 2, 3, 4, 0, 0),
    Magic("Magic lessons", -1, 2, 3, 4, 0, 0),
    Overpowered("Overpowered attack", -1, 2, 4, 6, 2, 50),
    Sacrifice("Sacrifice", 1, 2, 3, 4, 3, 60),
    Swirling("Swirling attack", -1, 2, 3, 4, 0, 0),
    Critical("Critical Strike", -1, 2, 3, 4, 0, 0),
    Caretaker("Caretaker", 1, 2, 3, 5, 1, 30),
    Elixir("Elixir", 1, 2, 3, 5, 2, 60),
    Boost("Boost", 1, 2, 3, 5, 2, 50),
    Mana("Mana Beam", 1, 2, 3, 5, 1, 50);


    String name;
    int coolDownTime;

    int xp1;
    int xp2;
    int xp3;

    int masrafEP;
    int masrafMP;

    boolean upgradeRequirement1 = true;
    boolean upgradeRequirement2 = true;
    boolean upgradeRequirement3 = true;

    String description;

    InformationOfAbilities(String name, int coolDownTime, int xp1, int xp2, int xp3, int Ep, int Mp) {
        this.name = name;
        this.coolDownTime = coolDownTime;
        //this.maxLevel = maxLevel;
        this.xp1 = xp1;
        this.xp2 = xp2;
        this.xp3 = xp3;

        this.masrafEP = Ep;
        this.masrafMP = Mp;

    }


    @InProgress
    public void setUpgradeRequirements(Hero hero) {
        switch (this) {
            case Boost:
            case Fight:
            case Work:
            case Quick:
            case Magic:
                break;
            case Overpowered:
                upgradeRequirement1 = hero.abilities.get(Fight.name).currentLevel >= 1;
                upgradeRequirement2 = hero.abilities.get(Fight.name).currentLevel >= 2;
                upgradeRequirement3 = hero.abilities.get(Fight.name).currentLevel >= 3;
                break;
            case Swirling:
                upgradeRequirement1 = hero.abilities.get(Work.name).currentLevel >= 1;
                break;
            case Sacrifice:
                upgradeRequirement1 = hero.abilities.get(Work.name).currentLevel >= 1;
                upgradeRequirement2 = hero.abilities.get(Work.name).currentLevel >= 2;
                upgradeRequirement3 = hero.abilities.get(Work.name).currentLevel >= 3;
                break;
            case Critical:
                upgradeRequirement1 = hero.abilities.get(Fight.name).currentLevel >= 1;
                break;
            case Elixir:
                upgradeRequirement2 = hero.abilities.get(Magic.name).currentLevel >= 1;
                upgradeRequirement3 = hero.abilities.get(Magic.name).currentLevel >= 2;
                break;
            case Caretaker:
                upgradeRequirement1 = hero.abilities.get(Quick.name).currentLevel >= 1;
                upgradeRequirement2 = hero.abilities.get(Quick.name).currentLevel >= 2;
                upgradeRequirement3 = hero.abilities.get(Quick.name).currentLevel >= 3;
                break;
            case Mana:
                upgradeRequirement1 = hero.abilities.get(Magic.name).currentLevel >= 1;
                upgradeRequirement2 = hero.abilities.get(Magic.name).currentLevel >= 2;
                upgradeRequirement3 = hero.abilities.get(Magic.name).currentLevel >= 3;
                break;
        }
    }

    @Completed
    void setDescription() {
        switch (this) {
            case Fight:
                this.description = "Fight training" + "\n" + "Permanently increases attack power" + "\n" + "Upgrade1: +30 attack power for 2 xp points" + "\n" + "Upgrade2: +30 attack power for 3 xp points" + "\n" + "Upgrade3: +30 attack power for 4 xp points";
                break;
            case Work:
                this.description = "Work out" + "\n" + "Permanently increases maximum health" + "\n" + "Upgrade 1: +50 maximum health for 2 xp points" + "\n" + "Upgrade 2: +50 maximum health for 3 xp points" + "\n" + "Upgrade 3: +50 maximum health for 4 xp points";
                break;
            case Quick:
                this.description = "Quick as a bunny\n" +
                        "Permanently increases energy points\n" +
                        "Upgrade1: +1 energy point for 2 xp points\n" +
                        "Upgrade2: +1 energy point for 3 xp points\n" +
                        "Upgrade3: +1 energy point for 4 xp points";
                break;
            case Magic:
                this.description = "Magic lessons\n" +
                        "Permanently increases maximum magic\n" +
                        "Upgrade 1: +50 maximum magic for 2 xp points\n" +
                        "Upgrade 2: +50 maximum magic for 3 xp points\n" +
                        "Upgrade 3: +50 maximum magic for 4 xp points";
                break;
            case Overpowered:
                this.description = "";
                this.description = " Overpowered attack\n" +
                        "Attacks an enemy with N times power for 2 energy points and 50 magic points\n" +
                        "Upgrade 1: N=1.2 for 2 xp points, needs Fight training upgrade1\n" +
                        "Upgrade 2: N=1.4 for 4 xp points, needs Fight training upgrade2\n" +
                        "Upgrade 3: N=1.6 for 6 xp points, needs Fight training upgrade3\n" +
                        "Success message: “Eley just did an overpowered attack on “ +\n" +
                        "(target) + “ with “ + (damage done) + “ damage";
                break;
            case Boost:
                this.description = "";
            case Swirling:
                this.description = "Swirling attack\n" +
                        "While attacking, non-targeted enemies also take P percent of\n" +
                        "its damage\n" +
                        "Upgrade 1: P=10 for 2 xp points, needs Work out upgrade 1\n" +
                        "Upgrade 2: P=20 for 3 xp points\n" +
                        "Upgrade 3: P=30 for 4 xp points";
                break;
            case Sacrifice:
                this.description = " Sacrifice\n" +
                        " Damages all the enemies with 3H power at the cost of\n" +
                        "H of his own health, needs 3 energy points, 60 magic points and\n" +
                        "has a 1 turn cooldown\n" +
                        " Upgrade 1: H=40 for 2 xp points, needs Work out\n" +
                        "upgrade 1\n" +
                        "  Upgrade 2: H=50 for 3 xp points, needs Work out\n" +
                        "upgrade 2\n" +
                        " Upgrade 3: H=60 for 4 xp points, needs Work out\n" +
                        "upgrade 3\n" +
                        " Success message: “Chrome just sacrificed himself to\n" +
                        "damage all his enemies with “ + (damage done) + “ power“";
                break;
            case Critical:
                this.description = "Critical strike\n" +
                        "Has a permanent P percent chance of doing an attack\n" +
                        "with double power (does not affect other abilities)\n" +
                        "Upgrade 1: P=20 for 2 xp points, needs Fight training\n" +
                        "upgrade 1\n" +
                        "Upgrade 2: P=30 for 3 xp points\n" +
                        "Upgrade 3: P=40 for 4 xp points";
                break;
            case Elixir:
                this.description = "Elixir\n" +
                        "Refills H points of her own health or an ally’s, for 2 energy points and 60 magic points\n" +
                        "Upgrade 1: H=100 for 2 xp points and takes 1 turn to cool down\n" +
                        "Upgrade 2: H=150 for 3 xp points, takes 1 turn to cool down and needs Magic lessons upgrade 1\n" +
                        "Upgrade 3: H=150 for 5 xp points, cools down instantly and needs Magic lessons upgrade 2\n" +
                        "Success message: “Meryl just healed “ + (target) + “ with “ + (healing amount) + “ health points”";
                break;
            case Caretaker:
                this.description = "Caretaker\n" +
                        "Gives 1 energy point to an ally for 30 magic points (this ep does not last until the end of the battle and is only\n" +
                        "usable during the current turn)\n" +
                        "Upgrade 1: takes 2 energy points and has a 1 turn cooldown for 2 xp points, needs Quick as a bunny upgrade 1\n" +
                        "Upgrade 2: takes 2 energy points and cools down instantly for 3 xp points, needs Quick as a bunny upgrade 2\n" +
                        "Upgrade 3 takes 1 energy point and cools down instantly for 5 xp points, needs Quick as a bunny upgrade 3\n" +
                        "Success message: “Meryl just gave “ + (target) + “ 1 energy point”";

                break;
            case Mana:
                this.description = "Mana beam\n" +
                        "Gives M magic points to himself or an ally for 1 energy point and 50 magic points\n" +
                        "Upgrade 1: M=50 for 2 xp points and takes 1 turn to cool down, needs magic lessons upgrade 1\n" +
                        "Upgrade 2: M=80 for 3 xp points and takes 1 turn to cool down, needs magic lessons upgrade 2\n" +
                        "Upgrade 3: M=80 for 4 xp points and cools down instantly, needs magic lessons upgrade 3\n" +
                        "Success message: “Bolti just helped “ + (target) + “ with “ + (M) + “ magic points”";
                break;
        }
    }
}
