package com.childrenOfTime.model.Equip;

import com.sun.istack.internal.NotNull;

/**
 * Created by SaeedHD on 07/07/2016.
 */
public class EffectType {
    private boolean targetUnChoosable;
    private boolean passive;
    private boolean AutoRepeatable;
    private boolean permanent;
    private boolean probablePerform;

    public EffectType(@NotNull boolean targetUnChoosable, boolean passive, boolean autoRepeatable, boolean permanent, boolean probablePerform) {
        this.setTargetUnChoosable(targetUnChoosable);
        this.setPassive(passive);
        setAutoRepeatable(autoRepeatable);
        this.setPermanent(permanent);
        this.setProbablePerform(probablePerform);
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
}
