package com.childrenOfTime.model.Equip;

import com.childrenOfTime.model.Rules;

import java.io.Serializable;

/**
 * Created by SaeedHD on 07/07/2016.
 */
public enum Target implements Serializable {
    HimSelf(true, true), SingleEnemy(false, 1, false), SingleTeamMate(false, 1, true), SeveralEnemies(false, false), SeveralTeamMates(false, true), AllTeammates(true, true), AllEnemies(true, false), theAttackedOne(true, false);

    Target(boolean implicit, boolean isTeammate) {
        Implicit = implicit;
        if (implicit) this.numberOftargetsNeededToChoose = 0;
        else this.numberOftargetsNeededToChoose = null;

    }
    Target(boolean implicit, Integer numberOftargetsNeededToChoose, boolean isTeammate) {
        Implicit = implicit;
        this.numberOftargetsNeededToChoose = numberOftargetsNeededToChoose;
        this.isTeammate = isTeammate;
    }
    public boolean isImplicit() {
        return Implicit;
    }
    public Integer getNumberOftargetsNeededToChoose() {


        return numberOftargetsNeededToChoose == null ? Rules.Quantitiy_Of_Targets_For_Manual_Multiple_Target_Choosing : numberOftargetsNeededToChoose;
    }
    public boolean isTeammate() {
        return isTeammate;
    }

    private boolean Implicit;
    private Integer numberOftargetsNeededToChoose;
    private boolean isTeammate;
}
