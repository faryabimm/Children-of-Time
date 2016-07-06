package com.childrenOfTime.model;

/**
 * Created by SaeedHD on 07/05/2016.
 */
public interface HasImpactHealth {
    void attack(Warrior warrior, Integer attackPower, Integer EPCost);

    void heal(Warrior warrior, Integer healingAmount);

}
