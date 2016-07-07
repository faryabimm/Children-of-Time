package com.childrenOfTime.model.Equip.AbilComps;

import com.childrenOfTime.exceptions.AbilityNotAquiredException;
import com.childrenOfTime.exceptions.RequirementsNotMetException;
import com.childrenOfTime.exceptions.UpgradeException;
import com.childrenOfTime.model.BST;
import com.childrenOfTime.model.Equip.Target;
import com.childrenOfTime.model.Interfaces.Castable;
import com.childrenOfTime.model.Interfaces.Durable;
import com.childrenOfTime.model.Warrior;
import com.childrenOfTime.model.Warriors.Hero;

import static com.childrenOfTime.view.IOHandler.printOutput;

/*
 * Created by SaeedHD on 07/05/2016.
 */
public class Ability implements Castable, Durable {
    String name;
    String description;
    Upgrade baseState;
    BST<Upgrade> Upgrades;
    Upgrade currentLevel;
    String SuccessMessage;
    Target targetType;

    public Ability(String name, Target targetType, String successMessage, String description) {
        SuccessMessage = successMessage;
        this.targetType = targetType;
        this.name = name;
        this.description = description;
    }

    public Ability(String name, String description, String successMessage, BST<Upgrade> upgrades, Target targetType) {
        this.name = name;
        this.description = description;
        this.SuccessMessage = successMessage;
        this.targetType = targetType;
        this.Upgrades = upgrades;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BST<Upgrade> getUpgrades() {
        return Upgrades;
    }

    public void setSuccessMessage(String successMessage) {
        SuccessMessage = successMessage;
    }

    @Override
    public void cast(Warrior caster, Warrior... targets) {
        if (currentLevel == null) throw new AbilityNotAquiredException("You didn't acqiure this");
        if (caster != null && caster instanceof Hero) {
            Hero casterHero = (Hero) caster;
            Warrior[] filteredTargets = targets;
            Hero performer = (Hero) caster;
            switch (targetType) {
                case HimSelf:
                    filteredTargets = new Warrior[1];
                    filteredTargets[0] = performer;
            }
            currentLevel.perform(casterHero, filteredTargets);
        }


    }

    private Integer acquire(Warrior warrior) {
        this.baseState = Upgrades.getMinElement();
        if (!baseState.upgradeBoolean) throw new RequirementsNotMetException();
        this.currentLevel = Upgrades.getMinElement();
        return currentLevel.getXPCost();
    }

    public Integer upgrade(Warrior performer, Integer i) throws UpgradeException {
        if (currentLevel == null) {
            return acquire(performer);

        }
        Upgrade fake = new Upgrade(i);
        Upgrade result = (Upgrade) Upgrades.getVar(fake);
        if (result != null) {
            if (!result.upgradeBoolean) throw new RequirementsNotMetException();
            currentLevel = result;
        }
        return result.getXPCost();
    }

    public Upgrade getUpgradeByNumber(Integer i) {
        return (Upgrade) Upgrades.getVar(new Upgrade(i));

    }

    @Override
    public void aTurnHasPassed() {
        currentLevel.aTurnHasPassed();
    }

    public void showDescription() {
        printOutput(description);
    }
}



