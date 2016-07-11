package com.childrenOfTime.model.Equip;

import com.childrenOfTime.model.Interfaces.Performable;
import com.childrenOfTime.model.Warriors.Warrior;
import com.sun.istack.internal.NotNull;
import com.sun.istack.internal.Nullable;

import java.io.Serializable;

/**
 * Created by SaeedHD on 07/07/2016.
 */
public class Effect implements Performable, Serializable {

    static final long serialVersionUID = -4746324265253139119L;

    public static final Double YEK_DOUBLE = 1d;
    public static final int DEFAULT_GHARARDADI_FOR_WEAROF_PASSIVE_EFFECT_INSTANTLY_AFTER_ATTACK = -3000;
    private String name;

    public String getName() {
        return name;
    }

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
                target.addToAutoRepeatEffList(this.alterPackage, this.autoRepitionDuration);
            }

        }
    }


    public void attentionToPermanents(Warrior... target_s) {
        if (!this.effectType.isPermanent()) {
            for (Warrior affectedTarget : target_s) {
                //TODO rideman Jam shavad
                affectedTarget.addToImPermanentTurnBasedEffectsList(this.alterPackage, this.impermanentDurability);
            }
        }
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Effect effect = (Effect) o;

        if (!getName().equals(effect.getName())) return false;
        if (getEffectType() != null ? !getEffectType().equals(effect.getEffectType()) : effect.getEffectType() != null)
            return false;
        if (getAlterPackage() != null ? !getAlterPackage().equals(effect.getAlterPackage()) : effect.getAlterPackage() != null)
            return false;
        if (getTargetType() != effect.getTargetType()) return false;
        if (getImpermanentDurability() != null ? !getImpermanentDurability().equals(effect.getImpermanentDurability()) : effect.getImpermanentDurability() != null)
            return false;
        return getAutoRepitionDuration() != null ? getAutoRepitionDuration().equals(effect.getAutoRepitionDuration()) : effect.getAutoRepitionDuration() == null;

    }

    @Override
    public int hashCode() {
        return 0;
    }

    public Effect(@NotNull String name, @NotNull EffectType effectType, @NotNull AlterPackage alterPackage, @NotNull Target targetType, @Nullable Integer probabilyPercent, @Nullable Integer impermanentDurability, @Nullable Integer autoRepitionDuration) {

        this.name = name;
        if (autoRepitionDuration == null) autoRepitionDuration = 0;
        if (impermanentDurability == null) impermanentDurability = 0;

        this.effectType = effectType;
        this.alterPackage = new AlterPackage(alterPackage, probabilyPercent);
        this.alterPackage.name = name;
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

