package com.childrenOfTime.model.Equip;

import com.childrenOfTime.model.Interfaces.Performable;
import com.childrenOfTime.model.Warrior;
import com.sun.istack.internal.NotNull;
import com.sun.istack.internal.Nullable;

import java.util.Random;

/**
 * Created by SaeedHD on 07/07/2016.
 */
public class Effects implements Performable {
    public static final Double YEK_DOUBLE = 1d;

    final EffectType effectType;
    final AlterPackage alterPackage;
    final AlterPackage performerCost;
    final Target targetType;
    final Integer ProbabilyPercent;
    final Integer impermanentDurability;
    final Integer autoRepitionDuration;

    @Override     //TODO add performed Alters to Warriors
    public void perform(@NotNull Warrior performer, Warrior[] targets, @NotNull Warrior... implicitTarget_s) {
        doAutoRepeat(implicitTarget_s);
        attentionToPermanents();
        performInEachTurn(performer, implicitTarget_s);
    }

    public Effects(@NotNull EffectType effectType, @NotNull AlterPackage alterPackage, @Nullable AlterPackage performerCost, @NotNull Target targetType, @Nullable Integer probabilyPercent, @Nullable Integer impermanentDurability, @Nullable Integer autoRepitionDuration) {
        if (autoRepitionDuration == null) autoRepitionDuration = 0;
        if (probabilyPercent == null) probabilyPercent = 100;
        if (impermanentDurability == null) impermanentDurability = 0;

        this.effectType = effectType;
        this.alterPackage = alterPackage;
        this.performerCost = performerCost;
        this.targetType = targetType;
        ProbabilyPercent = probabilyPercent;
        this.impermanentDurability = impermanentDurability;
        this.autoRepitionDuration = autoRepitionDuration;
    }

    private boolean hasPermissionToContinue(Warrior performer, Warrior... targets) {
        return doesPassiveAllowsToContinue(performer) && isProbabilityOccured();

    }


    private void doAutoRepeat(Warrior... targets) {
        if (effectType.isAutoRepeatable()) {
            for (Warrior target : targets) {
                target.addToAutoRepeatEffList(this, autoRepitionDuration);
            }

        }
    }


    public void attentionToPermanents(Warrior... target_s) {
        if (!effectType.isPermanent()) {
            for (Warrior affectedTarget : target_s) {
                affectedTarget.addToImPermanentEffectsList(this, impermanentDurability);
            }
        }
    }

    public void wearOff(Warrior... targets) {
        alterPackage.wearOff(targets);
    }

    public boolean doesPassiveAllowsToContinue(Warrior performer) {
        if (effectType.isPassive()) {
            if (performer.containsPassiveEffect(this)) return true;
            performer.addPassiveEffect(this);
            return false;
        }
        return true;
    }


    public boolean isProbabilityOccured() {
        if (effectType.isProbablePerform()) {
            Random rand = new Random();
            int n = rand.nextInt(100) + 1;
            if (n > this.ProbabilyPercent) {
                return false;
            }
        }
        return true;
    }

    public void payCost(Warrior performer) {
        if (performerCost != null) performerCost.perform(performer);

    }

    public void performInEachTurn(Warrior performer, Warrior... target_s) {
        if (!hasPermissionToContinue(performer, target_s)) return;
        alterPackage.perform(target_s);
        payCost(performer);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Effects effects = (Effects) o;

        if (effectType != null ? !effectType.equals(effects.effectType) : effects.effectType != null) return false;
        if (alterPackage != null ? !alterPackage.equals(effects.alterPackage) : effects.alterPackage != null)
            return false;
        if (performerCost != null ? !performerCost.equals(effects.performerCost) : effects.performerCost != null)
            return false;
        if (targetType != effects.targetType) return false;
        if (ProbabilyPercent != null ? !ProbabilyPercent.equals(effects.ProbabilyPercent) : effects.ProbabilyPercent != null)
            return false;
        if (impermanentDurability != null ? !impermanentDurability.equals(effects.impermanentDurability) : effects.impermanentDurability != null)
            return false;
        return !(autoRepitionDuration != null ? !autoRepitionDuration.equals(effects.autoRepitionDuration) : effects.autoRepitionDuration != null);

    }

    @Override
    public int hashCode() {
        int result = effectType != null ? effectType.hashCode() : 0;
        result = 31 * result + (alterPackage != null ? alterPackage.hashCode() : 0);
        result = 31 * result + (performerCost != null ? performerCost.hashCode() : 0);
        result = 31 * result + (targetType != null ? targetType.hashCode() : 0);
        result = 31 * result + (ProbabilyPercent != null ? ProbabilyPercent.hashCode() : 0);
        result = 31 * result + (impermanentDurability != null ? impermanentDurability.hashCode() : 0);
        result = 31 * result + (autoRepitionDuration != null ? autoRepitionDuration.hashCode() : 0);
        return result;
    }

    public static Double getYekDouble() {
        return YEK_DOUBLE;
    }

    public EffectType getEffectType() {
        return effectType;
    }

    public AlterPackage getAlterPackage() {
        return alterPackage;
    }

    public AlterPackage getPerformerCost() {
        return performerCost;
    }

    public Target getTargetType() {
        return targetType;
    }

    public Integer getProbabilyPercent() {
        return ProbabilyPercent;
    }

    public Integer getImpermanentDurability() {
        return impermanentDurability;
    }

    public Integer getAutoRepitionDuration() {
        return autoRepitionDuration;
    }
}

