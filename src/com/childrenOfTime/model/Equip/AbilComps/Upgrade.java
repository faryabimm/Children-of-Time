package com.childrenOfTime.model.Equip.AbilComps;

import com.childrenOfTime.exceptions.AttackException;
import com.childrenOfTime.model.Equip.Effects;
import com.childrenOfTime.model.Equip.Target;
import com.childrenOfTime.model.Interfaces.Performable;
import com.childrenOfTime.model.Warrior;
import com.childrenOfTime.model.Warriors.Hero;
import com.sun.istack.internal.NotNull;
import com.sun.istack.internal.Nullable;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by SaeedHD on 07/06/2016.
 */
public class Upgrade implements Performable, Comparable<Upgrade> {
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
    private Boolean upgradeBoolean;
    ArrayList<Effects> effects;
    String description;
    Boolean castJustAfterAcquire = false;
    final Boolean recastable;
    Boolean castedOnce;

    public Upgrade(@NotNull int numberOfUpgrade) {
        this.numberOfUpgrade = numberOfUpgrade;
        this.COOLDOWN_TIME = 0;
        this.recastable = true;
    }

    @Deprecated
    public Upgrade(@NotNull Integer numberOfUpgrade, @Nullable String description, @Nullable Integer COOLDOWN_TIME, @Nullable Integer XPCost, @Nullable Integer masrafEP, @Nullable Integer masrafMP, @Nullable String... upgradeRequirements) {
        if (description == null) description = "No Description Added";
        if (COOLDOWN_TIME == null) COOLDOWN_TIME = 0;
        if (XPCost == null) XPCost = 0;
        if (masrafEP == null) masrafEP = 0;
        if (masrafMP == null) masrafMP = 0;
        if (upgradeRequirements == null) {
            upgradeRequirements = new String[1];
            upgradeRequirements[0] = "true";
        }
        this.numberOfUpgrade = numberOfUpgrade;
        this.leftTurnsToCoolDown = COOLDOWN_TIME;
        this.COOLDOWN_TIME = COOLDOWN_TIME;
        this.XPCost = XPCost;
        this.masrafEP = masrafEP;
        this.masrafMP = masrafMP;
        this.description = description;
        this.upgradeRequirements = upgradeRequirements;
        this.recastable = true;

    }

    public Upgrade(@NotNull Integer numberOfUpgrade, @Nullable String description, @Nullable Integer COOLDOWN_TIME, @Nullable Integer XPCost, @Nullable Integer masrafEP, @Nullable Integer masrafMP, @Nullable Boolean castJustAfterAcquire, @Nullable Boolean recastable, @Nullable String... upgradeRequirements) {
        if (description == null) description = "No Description Added";
        if (COOLDOWN_TIME == null) COOLDOWN_TIME = 0;
        if (XPCost == null) XPCost = 0;
        if (masrafEP == null) masrafEP = 0;
        if (masrafMP == null) masrafMP = 0;
        if (upgradeRequirements == null) {
            upgradeRequirements = new String[1];
            upgradeRequirements[0] = "true";
        }
        if (castJustAfterAcquire == null) {
            castJustAfterAcquire = false;
        }
        if (recastable == null) {
            recastable = false;
        }
        this.numberOfUpgrade = numberOfUpgrade;
        this.leftTurnsToCoolDown = COOLDOWN_TIME;
        this.COOLDOWN_TIME = COOLDOWN_TIME;
        this.XPCost = XPCost;
        this.masrafEP = masrafEP;
        this.masrafMP = masrafMP;
        this.description = description;
        this.upgradeRequirements = upgradeRequirements;
        this.recastable = recastable;

    }


    public void setDescription(String description) {
        this.description = description;
    }


    //TODO dorost she ;
    public boolean needsTarget() {
        return true;
    }

    public Target getneededTargetType() {
        return Target.HimSelf;
    }

    @Override
    public void perform(@NotNull Warrior performer, @Nullable Warrior[] target_s, @Nullable Warrior... implicitTargets) {
        if (performer instanceof Hero) PayCosts((Hero) performer);
        Iterator<Effects> itr = this.effects.iterator();
        Warrior[] finalTargets = target_s;
        while (itr.hasNext()) {
            //TODO anotherCheck required
            Effects nextEffect = itr.next();
            if (nextEffect.getEffectType().isTargetUnChoosable() && implicitTargets != null) {
                finalTargets = implicitTargets;
            }
            if (finalTargets != null) {
                nextEffect.perform(performer, null, finalTargets);
            } else throw new RuntimeException("no Targets Selected");
            if (nextEffect.getEffectType().isPassive()) {
                itr.remove();
            }
        }
        if (this.COOLDOWN_TIME != 0) isInCoolDown = true;
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
        /*
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
        */

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

    public Boolean getUpgradeBoolean() {
        return upgradeBoolean;
    }
}
