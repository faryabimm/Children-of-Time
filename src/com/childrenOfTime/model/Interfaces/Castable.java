package com.childrenOfTime.model.Interfaces;

import com.childrenOfTime.model.Warriors.Warrior;

import java.io.Serializable;

/**
 * Created by SaeedHD on 07/06/2016.
 */
public interface Castable extends Serializable {
    void cast(Warrior performer, Warrior[] selectedTargets, Warrior[] allEnemies, Warrior[] allTeammates);
}


