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
import com.sun.istack.internal.NotNull;
import com.sun.istack.internal.Nullable;

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

    public Ability(@NotNull String name, @NotNull Target targetType, @Nullable String successMessage, @Nullable String description) {
        SuccessMessage = successMessage;
        this.targetType = targetType;
        this.name = name;
        this.description = description;
    }

    public Ability(@NotNull String name, @Nullable String description, @Nullable String successMessage, @NotNull BST<Upgrade> upgrades, @NotNull Target targetType) {
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

    public boolean isAcquired() {
        return currentLevel == null;
    }


    @Override
    public void cast(Warrior caster, Warrior[] targets, Warrior... implicitTargets) {
        if (currentLevel == null) throw new AbilityNotAquiredException("You didn't acqiure this");
        if (currentLevel.recastable) {
            if (currentLevel.castedOnce) {
                return;
            }
            currentLevel.castedOnce = true;
        }
        if (caster != null && caster instanceof Hero) {
            Hero casterHero = (Hero) caster;
            Warrior[] filteredTargets = targets;
            Hero performer = (Hero) caster;
            switch (targetType) {
                case HimSelf:
                    filteredTargets = new Warrior[1];
                    filteredTargets[0] = performer;
            }
            currentLevel.perform(casterHero, null, filteredTargets);
        }


    }

    private Integer acquire(Warrior warrior, Warrior[] targets, Warrior... implicitTargets) {
        this.baseState = Upgrades.getGodFatherElement();
        if (!baseState.getUpgradeBoolean()) throw new RequirementsNotMetException();
        this.currentLevel = Upgrades.getMinElement();
        if (currentLevel.castJustAfterAcquire) cast(warrior, targets, implicitTargets);
        return currentLevel.getXPCost();
    }

    public Integer upgrade(Warrior performer, Integer i, Warrior[] targets, Warrior... implicitTargets) throws UpgradeException {
        if (currentLevel == null) {
            return acquire(performer, targets, implicitTargets);
        }

        Upgrade fake = new Upgrade(i);
        Upgrade result = (Upgrade) Upgrades.getVar(fake);
        if (result != null) {
            if (!result.getUpgradeBoolean()) throw new RequirementsNotMetException();
            currentLevel = result;
        }
        if (currentLevel.castJustAfterAcquire) cast(performer, targets, implicitTargets);
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

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Upgrade getBaseState() {
        return baseState;
    }

    public Upgrade getCurrentLevel() {
        return currentLevel;
    }

    public String getSuccessMessage() {
        return SuccessMessage;
    }

    public Target getTargetType() {
        return targetType;
    }
}



