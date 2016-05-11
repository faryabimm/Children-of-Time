package com.childrenOfTime.model;

import java.util.ArrayList;

/**
 * Created by SaeedHD on 05/11/2016.
 */
public class InformationOfHeroes {
    int maxHealth;
    int healthRefillRate;
    int inventorySize;
    int attackPower;
    int maxMagic;
    int magicRefillRate;
    int initialEP;
    String ability2,ability1,ability3,ability4;
    InformationOfHeroes(int maxHealth, int healthRefillRate, int inventorySize, int attackPower, int maxMagic, int magicRefillRate, int initialEP,String ability1,String ability2) {
        this.maxHealth = maxHealth;
        this.healthRefillRate = healthRefillRate;
        this.inventorySize = inventorySize;
        this.attackPower = attackPower;
        this.maxMagic = maxMagic;
        this.magicRefillRate = magicRefillRate;
        this.initialEP = initialEP;
        this.ability1 = ability1;
        this.ability2 = ability2;
    }
}
