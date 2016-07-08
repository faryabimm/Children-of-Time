package com.childrenOfTime.model.Equip;

import com.childrenOfTime.model.Warrior;

import java.util.Arrays;

/**
 * Created by SaeedHD on 07/07/2016.
 */
public class AlterPackage {
    public static final int DEFAULT_COUNT_OF_ALTERING_ATTRIBUTES = 8;
    public static final Double DEFAULT_FACTOR = 1d;
    public static final Integer DEFAULT_DELTA = 0;

    //  AP , H , MH ,  HRF  , MP , MMP , MPRF  , EP ;
    public final Integer[] DELTA;
    public final Double[] FACTORS;

    public AlterPackage(Integer[] DELTA, Double[] FACTORS) {
        if (FACTORS == null) Arrays.fill(FACTORS, DEFAULT_FACTOR);
        if (DELTA == null) Arrays.fill(DELTA, DEFAULT_DELTA);
        this.DELTA = DELTA;
        this.FACTORS = FACTORS;
    }

    public void perform(Warrior... target_s) {
        for (Warrior w : target_s) {
            w.receiveAlterPack(this);
        }
    }

    public void wearOff(Warrior performer, Warrior... target_s) {
    }

}
