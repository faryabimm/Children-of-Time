package com.childrenOfTime.model;

import java.util.ArrayList;

/**
 * Created by mohammadmahdi on 5/8/16.
 */
public enum FighterHero{
    Eley(200,10,2,120,120,5,6,"Overpowered attack", "Swirling attack"),
    Chrome(200,10,2,120,120,5,6,"Sacrifice", "Critical strike");
    int maxHealth;
    int healthRefillRate;
    int inventorySize;
    int attackPower;
    int maxMagic;
    int magicRefillRate;
    int initialEP;
    ArrayList<String> abilities=new ArrayList<>();

    FighterHero(int maxHealth, int healthRefillRate, int inventorySize, int attackPower, int maxMagic, int magicRefillRate, int initialEP,String ability3,String ability4) {
        this.maxHealth = maxHealth;
        this.healthRefillRate = healthRefillRate;
        this.inventorySize = inventorySize;
        this.attackPower = attackPower;
        this.maxMagic = maxMagic;
        this.magicRefillRate = magicRefillRate;
        this.initialEP = initialEP;
        abilities.add("ability1");//TODO complete....
        abilities.add("ability2");//TODO complete....
        abilities.add("ability3");//TODO complete....
        abilities.add("ability4");//TODO complete....

    }


}
