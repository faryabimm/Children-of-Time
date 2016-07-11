package com.childrenOfTime.model.Equip;

import java.io.Serializable;

/**
 * Created by SaeedHD on 07/07/2016.
 */
public class EffectType implements Serializable {
    private boolean targetUnChoosable;
    private boolean passive;
    private boolean AutoRepeatable;
    private boolean permanent;
    private boolean probablePerform;
    boolean ifPassiveInstantEffectJustForAttack;

    public EffectType(boolean targetUnChoosable, boolean passive, boolean autoRepeatable, boolean permanent, boolean probablePerform, boolean ifPassiveInstantEffectJustForAttack) {
        this.targetUnChoosable = targetUnChoosable;
        this.passive = passive;
        AutoRepeatable = autoRepeatable;
        this.permanent = permanent;
        this.probablePerform = probablePerform;
        this.ifPassiveInstantEffectJustForAttack = ifPassiveInstantEffectJustForAttack;
    }


    public boolean isTargetUnChoosable() {
        return targetUnChoosable;
    }

    public void setTargetUnChoosable(boolean targetUnChoosable) {
        this.targetUnChoosable = targetUnChoosable;
    }

    public boolean isPassive() {
        return passive;
    }

    public void setPassive(boolean passive) {
        this.passive = passive;
    }


    public boolean isAutoRepeatable() {
        return AutoRepeatable;
    }

    public void setAutoRepeatable(boolean autoRepeatable) {
        AutoRepeatable = autoRepeatable;
    }

    public boolean isPermanent() {
        return permanent;
    }

    public void setPermanent(boolean permanent) {
        this.permanent = permanent;
    }

    public boolean isProbablePerform() {
        return probablePerform;
    }

    public void setProbablePerform(boolean probablePerform) {
        this.probablePerform = probablePerform;
    }

    public boolean isIfPassiveInstantEffectJustForAttack() {
        return ifPassiveInstantEffectJustForAttack;
    }

    public void setIfPassiveInstantEffectJustForAttack(boolean ifPassiveInstantEffectJustForAttack) {
        this.ifPassiveInstantEffectJustForAttack = ifPassiveInstantEffectJustForAttack;
    }
}
