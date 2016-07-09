package com.childrenOfTime.model.Interfaces;

import com.childrenOfTime.model.Warriors.Warrior;

/**
 * Created by SaeedHD on 07/06/2016.
 */
public interface Castable {
    void cast(Warrior performer, Warrior[] selectedTargets, Warrior[] allEnemies, Warrior[] allTeammates);
}


