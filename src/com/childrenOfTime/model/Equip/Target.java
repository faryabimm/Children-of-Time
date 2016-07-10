package com.childrenOfTime.model.Equip;

import com.childrenOfTime.model.Rules;

/**
 * Created by SaeedHD on 07/07/2016.
 */
public enum Target {
    HimSelf(true), SingleEnemy(false, 1), SingleTeamMate(false, 1), SeveralEnemies(false), SeveralTeamMates(false), AllTeammates(true), AllEnemies(true), theAttackedOne(true);

    Target(boolean implicit) {
        Implicit = implicit;
        if (implicit) this.targetsNeededToChoose = 0;
        else this.targetsNeededToChoose = Rules.Quantitiy_Of_Targets_While_Manual_Multiple_Target_Choosing;

    }


    Target(Integer targetsNeededToChoose, boolean implicit) {
        this.targetsNeededToChoose = targetsNeededToChoose;
        Implicit = implicit;
    }

    boolean Implicit;
    Integer targetsNeededToChoose;
}
