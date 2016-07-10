package com.childrenOfTime.model.Equip.AbilComps;

import com.childrenOfTime.exceptions.AbilityNotAquiredException;
import com.childrenOfTime.exceptions.RequirementsNotMetException;
import com.childrenOfTime.exceptions.UpgradeException;
import com.childrenOfTime.model.BST;
import com.childrenOfTime.model.Equip.Target;
import com.childrenOfTime.model.Interfaces.Castable;
import com.childrenOfTime.model.Interfaces.TurnBase;
import com.childrenOfTime.model.Warriors.Warrior;
import com.sun.istack.internal.NotNull;
import com.sun.istack.internal.Nullable;

import javax.swing.*;

import static com.childrenOfTime.view.IOHandler.printOutput;

/*
 * Created by SaeedHD on 07/05/2016.
 */
public class Ability implements Castable, TurnBase {
    public static ImageIcon DEFAUL_AbilityImage;
    String name;
    String description;
    Upgrade baseState;
    BST<Upgrade> Upgrades;
    Upgrade currentLevel;
    String SuccessMessage;
    Target targetType;
    ImageIcon image;

    public Ability(@NotNull String name, @NotNull Target targetType, @Nullable String successMessage, @Nullable String description, ImageIcon image) {
        SuccessMessage = successMessage;
        this.name = name;
        this.description = description;
        this.targetType = targetType;
        this.image = image;

    }

    public Ability(@NotNull String name, @Nullable String description, @Nullable String successMessage, @NotNull BST<Upgrade> upgrades, @NotNull Target targetType, ImageIcon image) {
        if (image == null) image = DEFAUL_AbilityImage;
        this.name = name;
        this.description = description;
        this.SuccessMessage = successMessage;
        this.Upgrades = upgrades;
        this.targetType = targetType;
        this.image = image;

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

    public void setBaseState(Upgrade baseState) {
        this.baseState = baseState;
    }

    @Override
    public void cast(Warrior caster, Warrior[] selectedTargets, Warrior[] allEnemies, Warrior[] allTeammates) {
        if (currentLevel == null) throw new AbilityNotAquiredException("You didn't acquire this");
        if (currentLevel.recastable) {
            if (currentLevel.castedOnce) {
                return;
            }
            currentLevel.castedOnce = true;
        }
        currentLevel.cast(caster, selectedTargets, allEnemies, allTeammates);
    }


    public Integer acquire(Warrior warrior, Warrior[] allEnemies, Warrior[] allTeammates) {
        this.baseState = Upgrades.getGodFatherElement();
        if (!baseState.getUpgradeBoolean()) throw new RequirementsNotMetException();
        this.currentLevel = Upgrades.getMinElement();
        if (currentLevel.castJustAfterAcquire) cast(warrior, null, allEnemies, allTeammates);
        return currentLevel.getXPCost();
    }

    public Integer upgrade(Warrior performer, Integer i, Warrior[] allEnemies, Warrior[] allTeammates) throws UpgradeException {
        if (currentLevel == null) {
            return acquire(performer, allEnemies, allTeammates);
        }

        Upgrade fake = new Upgrade(i);
        Upgrade result = (Upgrade) Upgrades.getVar(fake);
        if (result != null) {
            if (!result.getUpgradeBoolean()) throw new RequirementsNotMetException();
            currentLevel = result;
        }
        if (currentLevel.castJustAfterAcquire) cast(performer, null, allEnemies, allTeammates);
        return result.getXPCost();
    }


    public void forceUpgrade(Warrior performer, Integer i, Warrior[] allEnemies, Warrior[] allTeammates) {
        if (i == null) return;
        Upgrade fake = new Upgrade(i);
        Upgrade result = (Upgrade) Upgrades.getVar(fake);
        if (result != null) {
            currentLevel = result;
        }
        if (currentLevel.castJustAfterAcquire) cast(performer, null, allEnemies, allTeammates);
    }


    public Upgrade getUpgradeByNumber(Integer i) {
        Object returnedUp = Upgrades.getVar(new Upgrade(i));
        ;
        if (returnedUp == null) throw new RuntimeException(" Such an Upgrade doesn't exist ! ");
        return (Upgrade) returnedUp;

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

}



