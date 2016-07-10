package com.childrenOfTime.model.Equip;

import com.childrenOfTime.model.Interfaces.Performable;
import com.childrenOfTime.model.Warriors.Warrior;
import com.sun.istack.internal.NotNull;
import com.sun.istack.internal.Nullable;

/**
 * Created by SaeedHD on 07/07/2016.
 */
public class Effect implements Performable {
    public static final Double YEK_DOUBLE = 1d;
    public static final int DEFAULT_GHARARDADI_FOR_WEAROF_PASSIVE_EFFECT_INSTANTLY_AFTER_ATTACK = -3000;

    final EffectType effectType;
    final AlterPackage alterPackage;
    //final AlterPackage performerCost;
    final Target targetType;
    final Integer impermanentDurability;
    final Integer autoRepitionDuration;


    @Override
    public void perform(@NotNull Warrior... target_s) {
        doAutoRepeat(target_s);
        attentionToPermanents(target_s);
        alterPackage.perform(target_s);
        //payCost(performer);
    }


    private void doAutoRepeat(Warrior... targets) {
        if (this.effectType.isAutoRepeatable()) {
            for (Warrior target : targets) {

                //TODO rideman Jam shavad
                target.addToAutoRepeatEffList(this, this.autoRepitionDuration);
            }

        }
    }


    public void attentionToPermanents(Warrior... target_s) {
        if (!this.effectType.isPermanent()) {
            for (Warrior affectedTarget : target_s) {
                //TODO rideman Jam shavad
                affectedTarget.addToImPermanentTurnBasedEffectsList(this, this.impermanentDurability);
            }
        }
    }


    public Effect(@NotNull EffectType effectType, @NotNull AlterPackage alterPackage, @NotNull Target targetType, @Nullable Integer probabilyPercent, @Nullable Integer impermanentDurability, @Nullable Integer autoRepitionDuration) {
        if (autoRepitionDuration == null) autoRepitionDuration = 0;
        if (impermanentDurability == null) impermanentDurability = 0;

        this.effectType = effectType;
        this.alterPackage = alterPackage;
        //this.performerCost = performerCost;
        this.targetType = targetType;
        this.impermanentDurability = impermanentDurability;
        this.autoRepitionDuration = autoRepitionDuration;
    }


    public void wearOff(Warrior... targets) {

        alterPackage.wearOff(targets);
    }



/*
    public void payCost(Warrior performer) {
        if (performerCost != null) performerCost.asCost(performer);

    }

    public void performInEachTurn( Warrior... target_s) {
        if (!hasPermissionToContinue( target_s ) return;
        alterPackage.perform(target_s);
        //payCost(performer);
    }
*/


    public static Double getYekDouble() {
        return YEK_DOUBLE;
    }

    public EffectType getEffectType() {
        return effectType;
    }

    public AlterPackage getAlterPackage() {
        return alterPackage;
    }

    public Target getTargetType() {
        return targetType;
    }


    public Integer getImpermanentDurability() {
        return impermanentDurability;
    }

    public Integer getAutoRepitionDuration() {
        return autoRepitionDuration;
    }
}

