package com.childrenOfTime.model.Equip.AbilComps;

import com.childrenOfTime.exceptions.AbilityInCooldownException;
import com.childrenOfTime.exceptions.NotEnoughEnergyPointsException;
import com.childrenOfTime.exceptions.NotEnoughMagicPointsException;
import com.childrenOfTime.model.Equip.Effect;
import com.childrenOfTime.model.Equip.EffectPerformer;
import com.childrenOfTime.model.Equip.ItemComps.Messages;
import com.childrenOfTime.model.Equip.Target;
import com.childrenOfTime.model.Interfaces.Castable;
import com.childrenOfTime.model.Warriors.Warrior;
import com.sun.istack.internal.NotNull;
import com.sun.istack.internal.Nullable;

import java.io.Serializable;
import java.util.ArrayList;

import static com.childrenOfTime.view.IOHandler.printOutput;

/**
 * Created by SaeedHD on 07/06/2016.
 */
public class Upgrade implements Castable, Comparable<Upgrade>, Serializable {
    //Upgrade father;
    //ArrayList<Upgrade> children;

    int numberOfUpgrade;
    final Integer COOLDOWN_TIME;
    int leftTurnsToCoolDown;
    boolean isInCoolDown = false;
    boolean isBaseUpgrade;
    int XPCost;
    int masrafEP;
    int masrafMP;
    Messages messages;
    String[] upgradeRequirements;
    private Boolean upgradeBoolean = true;
    ArrayList<Effect> effects;
    Boolean castJustAfterAcquire = false;
    final Boolean recastable;
    boolean castedOnce = false;
    public boolean acquired;
    public Upgrade(@NotNull int numberOfUpgrade) {
        this.numberOfUpgrade = numberOfUpgrade;
        this.COOLDOWN_TIME = 0;
        this.recastable = true;
    }
//
//    @Deprecated
//    public Upgrade(@NotNull Integer numberOfUpgrade, @Nullable Integer COOLDOWN_TIME, @Nullable Integer XPCost, @Nullable Integer masrafEP, @Nullable Integer masrafMP, @Nullable String... upgradeRequirements) {
//        if (COOLDOWN_TIME == null) COOLDOWN_TIME = 0;
//        if (XPCost == null) XPCost = 0;
//        if (masrafEP == null) masrafEP = 0;
//        if (masrafMP == null) masrafMP = 0;
//        if (upgradeRequirements == null) {
//            upgradeRequirements = new String[1];
//            upgradeRequirements[0] = "true";
//        }
//        this.numberOfUpgrade = numberOfUpgrade;
//        this.leftTurnsToCoolDown = COOLDOWN_TIME;
//        this.COOLDOWN_TIME = COOLDOWN_TIME;
//        this.XPCost = XPCost;
//        this.masrafEP = masrafEP;
//        this.masrafMP = masrafMP;
//        this.upgradeRequirements = upgradeRequirements;
//        this.recastable = true;
//
//    }

    public Upgrade(@NotNull Integer numberOfUpgrade, @Nullable Integer COOLDOWN_TIME, @Nullable Integer XPCost, @Nullable Integer masrafEP, @Nullable Integer masrafMP, @Nullable Boolean castJustAfterAcquire, @Nullable Boolean recastable, ArrayList<Effect> effects, boolean isBaseUpgrade, @Nullable String... upgradeRequirements) {
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
        this.isBaseUpgrade = isBaseUpgrade;
        this.leftTurnsToCoolDown = COOLDOWN_TIME;
        this.COOLDOWN_TIME = COOLDOWN_TIME;
        this.XPCost = XPCost;
        this.masrafEP = masrafEP;
        this.masrafMP = masrafMP;
        this.upgradeRequirements = upgradeRequirements;
        this.recastable = recastable;
        this.castJustAfterAcquire = castJustAfterAcquire;
        this.effects = effects;
    }
    public void setDescription(String description) {
        this.messages.description = description;
    }
    //TODO dorost she ;
    public boolean needsTarget() {
        return true;
    }
    public Target getneededTargetType() {
        return Target.HimSelf;
    }
    @Override
    public void cast(Warrior performer, Warrior[] selectedTargets, Warrior[] allEnemies, Warrior[] allTeammates) {
        if (isInCoolDown) throw new AbilityInCooldownException(messages.getCoolDownFailureMessage());
        try {
            PayCosts(performer);
            EffectPerformer.performEffects(false, this.effects, performer, selectedTargets, allEnemies, allTeammates);
            if (this.COOLDOWN_TIME != 0) isInCoolDown = true;
            printOutput(messages.getSuccessMessage());
        } catch (NotEnoughEnergyPointsException e) {
            throw new NotEnoughEnergyPointsException(messages.getEpFailureMessage());
        } catch (NotEnoughMagicPointsException e) {
            throw new NotEnoughEnergyPointsException(messages.getMpSuccessMessage());
        }
    }
    private void PayCosts(Warrior performer) {

        performer.changeEP(-masrafEP);
        try {
            performer.changeCurrentMagic(-masrafMP);
        } catch (RuntimeException e) {
            performer.changeEP(+masrafEP);
            throw new RuntimeException(e.getMessage());
            //performer.setCurrentMagic(initMP);
            // }

        }
    }
    public void aTurnHasPassed() {
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
    public void addEffect(Effect effect) {
        effects.add(effect);
    }

    public ArrayList<Effect> getEffects() {
        return effects;
    }

    public String getDescription() {
        return messages.description;
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
    public void setEffects(ArrayList<Effect> effects) {
        this.effects = effects;
    }
    public Boolean getUpgradeBoolean() {
        return upgradeBoolean;
    }
    public void setMessages(Messages messages) {
        this.messages = messages;
    }
}
