package com.childrenOfTime.gui.fillForms.dataHolders;

import com.childrenOfTime.model.Equip.Target;

/**
 * Created by mohammadmahdi on 7/10/16.
 */
public class EffectDataHolder {
    public String name;
    public boolean applyUponAttack;
    public boolean temporaryEffect;
    public int temporaryEffectTurnCount;
    public boolean autoRepeatable;
    public int autoRepeatableTurnCount;
    public boolean indefiniteExcecution;
    public int indefiniteExcecutionPercent;
    public boolean wearOffEffectsAfterExcecution;
    public boolean automaticTargetSelection;
    public Target automaticTargetType;
    public double APCoefficient;
    public int APIncrement;
    public double HCoefficient;
    public int HIncrement;
    public double MPCoefficient;
    public int MPIncrement;
    public double MMPCoefficient;
    public int MMPIncrement;
    public double EPCoefficient;
    public int EPIncrement;
    public double HRRCoefficient;
    public int HRRIncrement;
    public double MMRRCoefficient;
    public int MMRRIncrement;



    public String toString() {
        System.out.println(applyUponAttack);
        System.out.println(temporaryEffect);
        System.out.println(temporaryEffectTurnCount);
        System.out.println(autoRepeatable);
        System.out.println(autoRepeatableTurnCount);
        System.out.println(indefiniteExcecution);
        System.out.println(indefiniteExcecutionPercent);
        System.out.println(automaticTargetSelection);




        return null;
    }


}
