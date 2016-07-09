package com.childrenOfTime.model.Equip;

/**
 * Created by SaeedHD on 07/06/2016.
 */
public class DuarbleEffects {
}
/*
    private Double factorAttackPower_WithAttack_Amount = 1d;
    private int factorAttackPower_WithAttack_Duration = 1;
    private int factorAttackPower_WithAttack_Duration_Left = 1;
    private Double factorAttackPower_WithoutAttack_Amount = 1d;
    private int factorAttackPower_WithoutAttack_Duration = 1;
    private int factorAttackPower_WithoutAttack_Duration_Left = 1;
    private int giveMagicPoints_Amount = 0;
    private int giveMagicPoints_Duration = 1;
    private int giveMagicPoints_Duration_Left = 1;
    private int giveHealth_amount = 0;
    private int giveHealth_Duration = 1;
    private int giveHealth_Duration_Left = 1;
    private int giveEP_amount = 0;
    private int giveEP_Duration = 1;
    private int giveEP_Duration_Left = 1;

    private ArrayList performingQueue;

    public DuarbleEffects() {

    }


    public DuarbleEffects(Double factorAttackPower_WithAttack_Amount, int factorAttackPower_WithAttack_Duration, Double factorAttackPower_WithoutAttack_Amount, int factorAttackPower_WithoutAttack_Duration, int giveMagicPoints_Amount, int giveMagicPoints_Duration, int giveHealth_amount, int giveHealth_Duration, int giveEP_amount, int giveEP_Duration) {

        this.factorAttackPower_WithAttack_Amount = factorAttackPower_WithAttack_Amount;
        this.factorAttackPower_WithAttack_Duration = factorAttackPower_WithAttack_Duration;
        this.factorAttackPower_WithoutAttack_Amount = factorAttackPower_WithoutAttack_Amount;
        this.factorAttackPower_WithoutAttack_Duration = factorAttackPower_WithoutAttack_Duration;
        this.giveMagicPoints_Amount = giveMagicPoints_Amount;
        this.giveMagicPoints_Duration = giveMagicPoints_Duration;
        this.giveHealth_amount = giveHealth_amount;
        this.giveHealth_Duration = giveHealth_Duration;
        this.giveEP_amount = giveEP_amount;
        this.giveEP_Duration = giveEP_Duration;
        setLefts();
    }

    @Override
    public void perform(Warrior performer, Warrior[] targets, Warrior... implicitTarget_s) {
        setLefts();
        saveData(performer, implicitTarget_s);
        performInEachTurn(performer, implicitTarget_s);


    }

    private Warrior savedPerformer;
    private Warrior[] savedTargets;

    private void saveData(Warrior performer, Warrior[] target_s) {
        savedPerformer = performer;
        savedTargets = target_s;
    }


    private void performInEachTurn(Warrior performer, Warrior... target_s) {
        if (getGiveEP_amount() != 0 && getGiveEP_Duration_Left() > 0) {
            giveEP(target_s);
        }
        if (getGiveHealth_amount() != 0 && getGiveHealth_Duration_Left() > 0) {
            heal(performer, target_s);
        }
        if (getGiveMagicPoints_Amount() != 0 && getGiveMagicPoints_Duration_Left() > 0) {
            giveMagic(target_s);
        }
        if (getFactorAttackPower_WithAttack_Amount() != YEK_DOUBLE && getFactorAttackPower_WithAttack_Duration_Left() > 0) {
            attack_factorAP(performer, target_s);
        }

        if (getFactorAttackPower_WithoutAttack_Amount() != YEK_DOUBLE && getFactorAttackPower_WithoutAttack_Duration_Left() == getFactorAttackPower_WithoutAttack_Duration()) {
            multipleAP(target_s);
        }
        if (getFactorAttackPower_WithoutAttack_Amount() != YEK_DOUBLE && getFactorAttackPower_WithoutAttack_Duration_Left() == 0) {
            wearOff(performer, target_s);
        }

    }

    private void giveEP(Warrior... targets) {
        for (Warrior target : targets) {
            if (target instanceof Hero)
                ((Hero) target).changeEP(getGiveEP_amount());
        }
    }

    private void attack_factorAP(Warrior attacker, Warrior... targets) {
        for (Warrior target : targets) {
            int newAttackPower = (int) (getFactorAttackPower_WithAttack_Amount() * attacker.getAttackPower());
            ((Hero) attacker).attackAuto(target, newAttackPower);
        }
    }

    private void multipleAP(Warrior... targets) {
        for (Warrior target : targets) {
            int newAP = (int) (target.getAttackPower() * getFactorAttackPower_WithoutAttack_Amount());
            target.setAttackPower(newAP);
        }
    }

    private void divideAP(Warrior... targets) {
        for (Warrior target : targets) {
            int newAP = (int) (target.getAttackPower() / getFactorAttackPower_WithoutAttack_Amount());
            target.setAttackPower(newAP);
        }
    }

    private void heal(Warrior healer, Warrior... targets) {
        for (Warrior target : targets) {
            if (target instanceof Hero)
                ((Hero) healer).heal(target, getGiveHealth_amount(), 0);
        }
    }

    private void giveMagic(Warrior... targets) {
        for (Warrior target : targets) {
            if (target instanceof Hero)
                ((Hero) target).changeMagic(getGiveMagicPoints_Amount());
        }
    }

    public void wearOff(Warrior performer, Warrior... target_s) {
        divideAP(target_s);
    }  //TODO JamKardane Rideman !

    @Override
    public void aTurnHasPassed() {
        factorAttackPower_WithoutAttack_Duration_Left = getFactorAttackPower_WithoutAttack_Duration_Left() - 1;
        factorAttackPower_WithAttack_Duration_Left = getFactorAttackPower_WithAttack_Duration_Left() - 1;
        giveEP_Duration_Left = getGiveEP_Duration_Left() - 1;
        giveHealth_Duration_Left = getGiveHealth_Duration_Left() - 1;
        giveMagicPoints_Duration_Left = getGiveMagicPoints_Duration_Left() - 1;
        if (savedPerformer != null && savedTargets != null) {
            performInEachTurn(savedPerformer, savedTargets);
        }
    }

    private void setLefts() {
        this.factorAttackPower_WithAttack_Duration_Left = this.getFactorAttackPower_WithAttack_Duration();
        this.factorAttackPower_WithoutAttack_Duration_Left = this.getFactorAttackPower_WithoutAttack_Duration();
        this.giveEP_Duration_Left = getGiveEP_Duration();
        this.giveHealth_Duration_Left = this.getGiveEP_Duration();
        this.giveMagicPoints_Duration_Left = this.getGiveMagicPoints_Duration();

    }

    public void setGiveEP_Duration(int giveEP_Duration) {
        this.giveEP_Duration = giveEP_Duration;
    }

    public void setGiveEP_amount(int giveEP_amount) {
        this.giveEP_amount = giveEP_amount;
    }

    public void setGiveHealth_Duration(int giveHealth_Duration) {
        this.giveHealth_Duration = giveHealth_Duration;
    }

    public void setGiveHealth_amount(int giveHealth_amount) {
        this.giveHealth_amount = giveHealth_amount;
    }

    public void setGiveMagicPoints_Duration(int giveMagicPoints_Duration) {
        this.giveMagicPoints_Duration = giveMagicPoints_Duration;
    }

    public void setGiveMagicPoints_Amount(int giveMagicPoints_Amount) {
        this.giveMagicPoints_Amount = giveMagicPoints_Amount;
    }

    public void setFactorAttackPower_WithoutAttack_Duration(int factorAttackPower_WithoutAttack_Duration) {
        this.factorAttackPower_WithoutAttack_Duration = factorAttackPower_WithoutAttack_Duration;
    }


    public void setFactorAttackPower_WithAttack_Duration(int factorAttackPower_WithAttack_Duration) {
        this.factorAttackPower_WithAttack_Duration = factorAttackPower_WithAttack_Duration;
    }


    public int getFactorAttackPower_WithAttack_Duration() {
        return factorAttackPower_WithAttack_Duration;
    }

    public int getFactorAttackPower_WithAttack_Duration_Left() {
        return factorAttackPower_WithAttack_Duration_Left;
    }

    public Double getFactorAttackPower_WithAttack_Amount() {
        return factorAttackPower_WithAttack_Amount;
    }

    public void setFactorAttackPower_WithAttack_Amount(Double factorAttackPower_WithAttack_Amount) {
        this.factorAttackPower_WithAttack_Amount = factorAttackPower_WithAttack_Amount;
    }

    public Double getFactorAttackPower_WithoutAttack_Amount() {
        return factorAttackPower_WithoutAttack_Amount;
    }

    public void setFactorAttackPower_WithoutAttack_Amount(Double factorAttackPower_WithoutAttack_Amount) {
        this.factorAttackPower_WithoutAttack_Amount = factorAttackPower_WithoutAttack_Amount;
    }

    public int getFactorAttackPower_WithoutAttack_Duration() {
        return factorAttackPower_WithoutAttack_Duration;
    }

    public int getFactorAttackPower_WithoutAttack_Duration_Left() {
        return factorAttackPower_WithoutAttack_Duration_Left;
    }

    public int getGiveMagicPoints_Amount() {
        return giveMagicPoints_Amount;
    }

    public int getGiveMagicPoints_Duration() {
        return giveMagicPoints_Duration;
    }

    public int getGiveMagicPoints_Duration_Left() {
        return giveMagicPoints_Duration_Left;
    }

    public int getGiveHealth_amount() {
        return giveHealth_amount;
    }

    public int getGiveHealth_Duration() {
        return giveHealth_Duration;
    }

    public int getGiveHealth_Duration_Left() {
        return giveHealth_Duration_Left;
    }

    public int getGiveEP_amount() {
        return giveEP_amount;
    }

    public int getGiveEP_Duration() {
        return giveEP_Duration;
    }

    public int getGiveEP_Duration_Left() {
        return giveEP_Duration_Left;
    }

    public ArrayList getPerformingQueue() {
        return performingQueue;
    }
}
*/
