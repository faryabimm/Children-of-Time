package com.childrenOfTime.model.Interfaces;

import com.childrenOfTime.model.Warrior;

/**
 * Created by SaeedHD on 07/05/2016.
 */
public interface HasImpactHealth {
    void attack(Warrior warrior, Integer attackPower, Integer EPCost, Warrior... nonTargetedEnemies);

    void heal(Warrior warrior, Integer healingAmount, Integer EPCost, Warrior... nonTargetedEnemies);

}
