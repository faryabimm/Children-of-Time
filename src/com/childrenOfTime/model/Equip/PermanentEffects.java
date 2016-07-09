package com.childrenOfTime.model.Equip;

import com.childrenOfTime.model.Warriors.Hero;
import com.childrenOfTime.model.Warriors.Warrior;

/**
 * Created by SaeedHD on 07/06/2016.
 */
public class PermanentEffects extends Effects {

    Integer giveAttackPowerPermanently = 0;
    Double factorAttackPowerPermanently = YEK_DOUBLE;
    Integer giveMaxMagicPermanently = 0;
    Integer giveMaxHealthPermanently;


    public PermanentEffects() {
    }

    public PermanentEffects(Integer giveAttackPowerPermanently, Double factorAttackPowerPermanently, Integer giveMaxMagicPermanently) {
        this.giveAttackPowerPermanently = giveAttackPowerPermanently;
        this.factorAttackPowerPermanently = factorAttackPowerPermanently;
        this.giveMaxMagicPermanently = giveMaxMagicPermanently;
    }

    public PermanentEffects(Integer giveAttackPowerPermanently, Double factorAttackPowerPermanently, Integer giveMaxMagicPermanently, Integer giveMaxHealthPermanently) {
        this.giveAttackPowerPermanently = giveAttackPowerPermanently;
        this.factorAttackPowerPermanently = factorAttackPowerPermanently;
        this.giveMaxMagicPermanently = giveMaxMagicPermanently;
        this.giveMaxHealthPermanently = giveMaxHealthPermanently;
    }

    @Override
    public void perform(Warrior... Target_s) {
        if (giveAttackPowerPermanently != null && giveAttackPowerPermanently != 0) {
            for (Warrior target : Target_s) {
                target.changeAttackPower(this.giveAttackPowerPermanently);
            }
        }
        if (factorAttackPowerPermanently != null && factorAttackPowerPermanently != YEK_DOUBLE) {
            for (Warrior target : Target_s) {
                int newAttackpower = (int) (target.getAttackPower() * this.factorAttackPowerPermanently);
                target.setAttackPower(newAttackpower);
            }
        }

        if (giveMaxMagicPermanently != null && giveMaxMagicPermanently != 0)
            for (Warrior target : Target_s) {
                if (target instanceof Hero)
                    ((Hero) target).changeMaxMagic(this.giveMaxMagicPermanently);
            }
        if (giveMaxHealthPermanently != null && giveMaxHealthPermanently != 0)
            for (Warrior target : Target_s) {
                target.setMaxHealth(target.getMaxHealth() + this.giveMaxMagicPermanently);
            }

    }

    public void setGiveAttackPowerPermanently(Integer giveAttackPowerPermanently) {
        this.giveAttackPowerPermanently = giveAttackPowerPermanently;
    }

    public void setFactorAttackPowerPermanently(Double factorAttackPowerPermanently) {
        this.factorAttackPowerPermanently = factorAttackPowerPermanently;
    }

    public void setGiveMaxMagicPermanently(Integer giveMaxMagicPermanently) {
        this.giveMaxMagicPermanently = giveMaxMagicPermanently;
    }
}
