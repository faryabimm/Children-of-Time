package com.childrenOfTime.model.Equip;

import com.childrenOfTime.model.Rules;

/**
 * Created by SaeedHD on 07/07/2016.
 */
public enum Target {
    HimSelf(true, true), SingleEnemy(false, 1, false), SingleTeamMate(false, 1, true), SeveralEnemies(false, 0, false), SeveralTeamMates(false, true), AllTeammates(true, true), AllEnemies(true, false), theAttackedOne(true, false);

    Target(boolean implicit, boolean isTeammate) {
        Implicit = implicit;
        if (implicit) this.numberOftargetsNeededToChoose = 0;
        else this.numberOftargetsNeededToChoose = Rules.Quantitiy_Of_Targets_For_Manual_Multiple_Target_Choosing;

    }


    Target(boolean implicit, Integer numberOftargetsNeededToChoose, boolean isTeammate) {
        Implicit = implicit;
        this.numberOftargetsNeededToChoose = numberOftargetsNeededToChoose;
        this.isTeammate = isTeammate;
    }


    private boolean Implicit;
    private Integer numberOftargetsNeededToChoose;
    private boolean isTeammate;

    public boolean isImplicit() {
        return Implicit;
    }

    public Integer getNumberOftargetsNeededToChoose() {
        return numberOftargetsNeededToChoose;
    }

    public boolean isTeammate() {
        return isTeammate;
    }
}
