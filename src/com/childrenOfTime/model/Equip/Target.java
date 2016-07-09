package com.childrenOfTime.model.Equip;

/**
 * Created by SaeedHD on 07/07/2016.
 */
public enum Target {
    HimSelf(true), SingleTarget(false), SeveralEnemies(false), SeveralTeamMates(false), AllTeammates(true), AllEnemies(true), theAttackedOne(true);

    Target(boolean implicit) {
        Implicit = implicit;
    }

    boolean Implicit;
}
