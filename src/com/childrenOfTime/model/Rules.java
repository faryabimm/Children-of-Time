package com.childrenOfTime.model;

import java.io.Serializable;

/**
 * Created by SaeedHD on 07/09/2016.
 */
public class Rules implements Serializable {
    public static int INITIAL_XP = 15;
    public static int INITIAL_MONEY = 40;
    public static int INITIAL_IMMORTALITY_POTION = 3;
    public static final int INITIAL_XP_DEFAULT = 15;
    public static final int INITIAL_MONEY_DEFAULT = 40;
    public static final int INITIAL_IMMORTALITY_POTION_DEFAULT = 3;
    public static DIFFICUALTY DIFFICUALTY = com.childrenOfTime.model.DIFFICUALTY.Medium;
    public static Integer Quantitiy_Of_Targets_For_Manual_Multiple_Target_Choosing = 3;
    public static final Integer Quantitiy_Of_Targets_For_Manual_Multiple_Target_Choosing_DEFAULT = 3;
    //TODO if it's 1000 It means All Heroes have to die to Defeat
    public static int NUMBER_OF_HEROS_DYE_FROM_HUMAN_PLAYER_TO_DEFEAT = 1;
    public static final int NUMBER_OF_HEROS_DYE_FROM_HUMAN_PLAYER_TO_DEFEAT_DEFAULT = 1;


    public static Boolean AttackPowerCanBeNegative = false;
    public static Boolean RefillRatesCanBeNegative = false;
    public static Boolean INTELLIJENT_DAMGES = true;
    public static boolean giveRandomRewardByDifferentiationBetweenWinnerAndLoser = true;
}
