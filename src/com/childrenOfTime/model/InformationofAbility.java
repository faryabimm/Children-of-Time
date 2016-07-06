package com.childrenOfTime.model;

import com.childrenOfTime.Completed;
import com.childrenOfTime.exceptions.AbilityNotAquiredException;
import com.childrenOfTime.exceptions.AttackException;
import com.childrenOfTime.exceptions.RequirementsNotMetException;
import com.childrenOfTime.exceptions.UpgradeException;

import java.util.ArrayList;

import static com.childrenOfTime.view.IOHandler.printOutput;

/*
 * Created by SaeedHD on 07/05/2016.
 */
public class InformationofAbility {

    String name;
    String description;
    Upgrade baseState;
    BST<Upgrade> Upgrades;
    Upgrade currentLevel;
    String SuccessMessage;
    Target targetType;

    public void acquire() {
        this.baseState = this.currentLevel = Upgrades.getMinElement();
    }


    public InformationofAbility(String name, BST<Upgrade> upgrades, Target targetType) {
        this.name = name;
        this.baseState = baseState;
        Upgrades = upgrades;
        this.targetType = targetType;
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

    @Completed
    public void showDescription() {
        printOutput(this.description);
    }

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


    public void upgrade(int i) throws UpgradeException {
        if (currentLevel == null) {
            throw new RequirementsNotMetException("You Don't have this ability !");
        }
        Upgrade fake = new Upgrade(i);
        Upgrade result = (Upgrade) Upgrades.getVar(fake);
        if (result != null) {
            if (!result.upgradeRequirement) throw new RequirementsNotMetException();
            currentLevel = result;
        }

    }

    public Upgrade getUpgradeByNumber(Integer i) {
        return (Upgrade) Upgrades.getVar(new Upgrade(i));

    }
}

enum Target {HimSelf, SingleEnemy, SeveralEnemies, SeveralTeamMates, SingleTeamMate;}


interface Performable {
    void perform(Hero performer, Warrior... target_s);
}

class Upgrade implements Performable, Comparable<Upgrade> {

    //Upgrade father;
    //ArrayList<Upgrade> children;

    int numberOfUpgrade;
    final Integer COOLDOWN_TIME;
    int leftTurnsToCoolDown;
    boolean isInCoolDown = false;
    int XPCost;
    int masrafEP;
    int masrafMP;
    boolean upgradeRequirement = true;
    ArrayList<Effects> effects;
    String description;

    public Upgrade(int numberOfUpgrade) {
        this.numberOfUpgrade = numberOfUpgrade;
        this.COOLDOWN_TIME = 0;
    }

    public Upgrade(int numberOfUpgrade, Integer COOLDOWN_TIME, int XPCost, int masrafEP, int masrafMP, Boolean upgradeRequirement) {
        this.numberOfUpgrade = numberOfUpgrade;
        this.leftTurnsToCoolDown = COOLDOWN_TIME;
        this.COOLDOWN_TIME = COOLDOWN_TIME;
        this.XPCost = XPCost;
        this.masrafEP = masrafEP;
        this.masrafMP = masrafMP;
        this.upgradeRequirement = upgradeRequirement;
    }


    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public void perform(Hero performer, Warrior... target_s) {
        PayCosts(performer);

        for (Effects eff : effects) {
            eff.perform(performer, target_s);
        }
        isInCoolDown = true;
    }

    private void PayCosts(Hero performer) {
        int initEP = 0;
        int initMP = 0;

        try {
            initEP = performer.currentEnergyPoints;
            initMP = performer.currentMagic;
            performer.changeEP(-masrafEP);
            performer.changeMagic(-masrafMP);
        } catch (AttackException e) {
            performer.setCurrentMagic(initEP);
            performer.setCurrentMagic(initMP);
        }
    }


    public void aTurnHasPassed() {
        for (Effects eff : effects) {
            if (eff instanceof Durable) {
                ((Durable) eff).aTurnHasPassed();
            }
        }
        if (!isInCoolDown) return;
        if (leftTurnsToCoolDown == 1) {
            this.isInCoolDown = false;
            leftTurnsToCoolDown = this.COOLDOWN_TIME;
        } else {
            leftTurnsToCoolDown--;
        }
    }

    @Override
    public int compareTo(Upgrade o) {
        if (((Upgrade) o).numberOfUpgrade > this.numberOfUpgrade) return -1;
        if (((Upgrade) o).numberOfUpgrade == this.numberOfUpgrade) return 0;
        else return 1;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Upgrade upgrade = (Upgrade) o;

        return numberOfUpgrade == upgrade.numberOfUpgrade;

    }


    public int getNumberOfUpgrade() {
        return numberOfUpgrade;
    }

    public Integer getCOOLDOWN_TIME() {
        return COOLDOWN_TIME;
    }

    public int getLeftTurnsToCoolDown() {
        return leftTurnsToCoolDown;
    }

    public boolean isInCoolDown() {
        return isInCoolDown;
    }

    public int getXPCost() {
        return XPCost;
    }

    public int getMasrafEP() {
        return masrafEP;
    }

    public int getMasrafMP() {
        return masrafMP;
    }

    public boolean isUpgradeRequirement() {
        return upgradeRequirement;
    }

    public void addEffect(Effects effect) {
        effects.add(effect);
    }

    public String getDescription() {
        return description;
    }


    public void setNumberOfUpgrade(int numberOfUpgrade) {
        this.numberOfUpgrade = numberOfUpgrade;
    }

    public void setLeftTurnsToCoolDown(int leftTurnsToCoolDown) {
        this.leftTurnsToCoolDown = leftTurnsToCoolDown;
    }

    public void setInCoolDown(boolean inCoolDown) {
        isInCoolDown = inCoolDown;
    }

    public void setXPCost(int XPCost) {
        this.XPCost = XPCost;
    }

    public void setMasrafEP(int masrafEP) {
        this.masrafEP = masrafEP;
    }

    public void setMasrafMP(int masrafMP) {
        this.masrafMP = masrafMP;
    }

    public void setUpgradeRequirement(boolean upgradeRequirement) {
        this.upgradeRequirement = upgradeRequirement;
    }

    public void setEffects(ArrayList<Effects> effects) {
        this.effects = effects;
    }
}


abstract class Effects implements Performable {
    public static final Double YEK_DOUBLE = 1d;

    @Override
    public abstract void perform(Hero performer, Warrior... target_s);


}


