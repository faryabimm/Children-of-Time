package com.childrenOfTime.model.Equip;

import com.childrenOfTime.model.Warrior;
import com.childrenOfTime.model.Warriors.Hero;

/**
 * Created by SaeedHD on 07/07/2016.
 */
public class PermanentExtraFeatures extends PermanentEffects {
    private Boolean swirlingHeal;
    private Integer swirlingHealPercents;

    private Boolean swirlingAttack1;
    private Integer swirlingAttackPercents;

    private Boolean CriticalStrike;
    private Double CriticalStrikeFactor;
    private Integer CriticalStrikeProbability;

    public PermanentExtraFeatures(Boolean swirlingHeal, Integer swirlingHealPercents, Boolean swirlingAttack1, Integer swirlingAttackPercents, Boolean criticalStrike, Double criticalStrikeFactor, Integer ProbabilityPercent) {
        this.swirlingHeal = swirlingHeal;
        this.swirlingHealPercents = swirlingHealPercents;
        this.swirlingAttack1 = swirlingAttack1;
        this.swirlingAttackPercents = swirlingAttackPercents;
        this.CriticalStrike = criticalStrike;
        this.CriticalStrikeFactor = criticalStrikeFactor;
        this.CriticalStrikeProbability = ProbabilityPercent;
    }

    @Override
    public void perform(Hero performer, Warrior... target_s) {
        performer.setSwirlingAttackisActivated(swirlingAttack1);
        performer.setSwirlingHealisActivated(swirlingHeal);
        performer.setDamagePercentAttack(swirlingAttackPercents);
        performer.setDamagePercentH(swirlingHealPercents);
        performer.setCriticalIsActivated(CriticalStrike);
        performer.setCriticalFactor(CriticalStrikeFactor);
        performer.setProbability(this.CriticalStrikeProbability);
    }
}
