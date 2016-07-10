package com.childrenOfTime.model.Equip;

import com.childrenOfTime.model.Warriors.Warrior;
import com.sun.istack.internal.Nullable;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Random;

/**
 * Created by SaeedHD on 07/07/2016.
 */
public class AlterPackage implements Serializable {
    public static final int DEFAULT_COUNT_OF_ALTERING_ATTRIBUTES = 8;
    public static final Double DEFAULT_FACTOR = 1d;
    public static final Integer DEFAULT_DELTA = 0;

    //  AP , H , MH ,  HRF  , MP , MMP , MPRF  , EP ;
    public final Integer[] DELTA;
    public final Double[] FACTORS;
    private final Integer ProbabilyPercent;

    boolean woreOff = false;


    public boolean isProbabilityOccured() {
        Random rand = new Random();
        int n = rand.nextInt(100) + 1;
        if (n > this.ProbabilyPercent) {
            return false;
        }

        return true;
    }

    public AlterPackage(@Nullable Integer[] DELTA, @Nullable Double[] FACTORS) {

        if (FACTORS == null && DELTA == null) {
            this.DELTA = null;
            this.FACTORS = null;
            this.ProbabilyPercent = 100;
            return;
        }
        if (DELTA == null) Arrays.fill(FACTORS, DEFAULT_FACTOR);
        if (DELTA == null) Arrays.fill(DELTA, DEFAULT_DELTA);
        this.DELTA = DELTA;
        this.FACTORS = FACTORS;
        this.ProbabilyPercent = 100;
    }

    public AlterPackage(Integer[] DELTA, Double[] FACTORS, Integer probabilyPercent) {
        this.DELTA = DELTA;
        this.FACTORS = FACTORS;
        this.ProbabilyPercent = probabilyPercent;
    }

    public void perform(Warrior... target_s) {

        for (Warrior w : target_s) {
            w.receiveAlterPack(this);
        }
    }

    public void asCost(Warrior... ta) {
        wearOff(ta);
    }

    public void wearOff(Warrior... target_s) {
        AlterPackage newPackage = CreateReversedAlteringPackage();
        newPackage.perform(target_s);
        woreOff = true;
    }

    public AlterPackage CreateReversedAlteringPackage() {
        Integer[] newDelta = new Integer[DEFAULT_COUNT_OF_ALTERING_ATTRIBUTES];
        Double[] newFactors = new Double[DEFAULT_COUNT_OF_ALTERING_ATTRIBUTES];
        for (int i = 0; i < this.DELTA.length; i++) {
            newDelta[i] = -this.DELTA[i];

            newFactors[i] = this.FACTORS[i] == 0 ? 0 : 1.0 / this.FACTORS[i]; //TODO ask for it
        }
        return new AlterPackage(newDelta, newFactors);
    }

}
