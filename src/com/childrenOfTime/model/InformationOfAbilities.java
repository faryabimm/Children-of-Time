package com.childrenOfTime.model;

import com.childrenOfTime.InProgress;

/**
 * Created by SaeedHD on 05/13/2016.
 */
public enum InformationOfAbilities {
    Fight("Fight training", -1, 2, 3, 4),
    Work("Work out", -1, 2, 3, 4),
    Quick("Quick as a bunny", -1, 2, 3, 4),
    Magic("Magic lessons", -1, 2, 3, 4),
    Overpowered("Overpowered attack", -1, 2, 4, 6),
    Sacrifice("Sacrifice", 1, 2, 3, 4),
    Swirling("Swirling attack", -1, 2, 3, 4),
    Critical("Critical Strike", -1, 2, 3, 4),
    Caretaker("Caretaker", 1, 2, 3, 5),
    Elixir("Elixir", 1, 2, 3, 5),
    Boost("Boost", 1, 2, 3, 5),
    Mana("Mana Beam", 1, 2, 3, 5);


    String name;
    int coolDownTime;

    int xp1;
    int xp2;
    int xp3;

    boolean upgradeRequirement1 = true;
    boolean upgradeRequirement2 = true;
    boolean upgradeRequirement3 = true;


    InformationOfAbilities(String name, int coolDownTime, int xp1, int xp2, int xp3) {
        this.name = name;
        this.coolDownTime = coolDownTime;
        //this.maxLevel = maxLevel;
        this.xp1 = xp1;
        this.xp2 = xp2;
        this.xp3 = xp3;

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


}
