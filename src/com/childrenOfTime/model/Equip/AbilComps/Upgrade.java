package com.childrenOfTime.model.Equip.AbilComps;

import com.childrenOfTime.exceptions.AttackException;
import com.childrenOfTime.model.Equip.Effects;
import com.childrenOfTime.model.Interfaces.Durable;
import com.childrenOfTime.model.Interfaces.Performable;
import com.childrenOfTime.model.Warrior;
import com.childrenOfTime.model.Warriors.Hero;

import java.util.ArrayList;

/**
 * Created by SaeedHD on 07/06/2016.
 */
public class Upgrade implements Performable, Comparable<Upgrade> {
    //TODO make Constructors nullable
    //Upgrade father;
    //ArrayList<Upgrade> children;

    int numberOfUpgrade;
    final Integer COOLDOWN_TIME;
    int leftTurnsToCoolDown;
    boolean isInCoolDown = false;
    int XPCost;
    int masrafEP;
    int masrafMP;
    String[] upgradeRequirements;
    Boolean upgradeBoolean;
    ArrayList<Effects> effects;
    String description;

    public Upgrade(int numberOfUpgrade) {
        this.numberOfUpgrade = numberOfUpgrade;
        this.COOLDOWN_TIME = 0;
    }

    public Upgrade(Integer numberOfUpgrade, String description, Integer COOLDOWN_TIME, Integer XPCost, Integer masrafEP, Integer masrafMP, String... upgradeRequirements) {
        this.numberOfUpgrade = numberOfUpgrade;
        this.leftTurnsToCoolDown = COOLDOWN_TIME;
        this.COOLDOWN_TIME = COOLDOWN_TIME;
        this.XPCost = XPCost;
        this.masrafEP = masrafEP;
        this.masrafMP = masrafMP;
        this.description = description;
        this.upgradeRequirements = upgradeRequirements;

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
            initEP = performer.getCurrentEnergyPoints();
            initMP = performer.getCurrentMagic();
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


    public void setEffects(ArrayList<Effects> effects) {
        this.effects = effects;
    }
}
