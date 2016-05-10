package com.childrenOfTime.model;

import java.util.ArrayList;

/**
 * Created by mohammadmahdi on 5/8/16.
 */
public enum HeroType{
    Fighter(200,10,2,120,120,5,6,"Overpowered attack", "Swirling attack"),
    Supporter(200,10,2,120,120,5,6,"Sacrifice", "Critical strike");
    int maxHealth;
    int healthRefillRate;
    int inventorySize;
    int attackPower;
    int maxMagic;
    int magicRefillRate;
    int initialEP;
    String ability2,ability1;
    HeroType(int maxHealth, int healthRefillRate, int inventorySize, int attackPower, int maxMagic, int magicRefillRate, int initialEP,String ability1,String ability2) {
        this.maxHealth = maxHealth;
        this.healthRefillRate = healthRefillRate;
        this.inventorySize = inventorySize;
        this.attackPower = attackPower;
        this.maxMagic = maxMagic;
        this.magicRefillRate = magicRefillRate;
        this.initialEP = initialEP;
        this.ability1=ability1;
        this.ability2=ability2;
    }


}
