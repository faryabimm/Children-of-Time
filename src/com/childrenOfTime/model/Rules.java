package com.childrenOfTime.model;

import java.io.Serializable;

/**
 * Created by SaeedHD on 07/09/2016.
 */
public class Rules implements Serializable {
    public static int INITIAL_XP = 40;
    public static int INITIAL_MONEY = 15;

    //TODO if it's 1000 It means All Heroes have to die to Defeat
    public static int NUMBER_OF_HEROS_DYE_FROM_HUMAN_PLAYER_TO_DEFEAT = 1;
    public static Boolean AttackPowerCanBeNegative;
    public static Boolean RefillRatesCanBeNegative;
    public static Boolean INTELLIJENT_DAMGES;
    public static DIFFICUALTY DIFFICUALTY;
    public static Integer Quantitiy_Of_Targets_For_Manual_Multiple_Target_Choosing = 3;


    public static boolean giveRandomRewardByDifferentiationBetweenWinnerAndLoser = true;
}
