package com.childrenOfTime.model;

import java.util.ArrayList;

/**
 * Created by SaeedHD on 05/11/2016.
 */
public enum SupporterHero {
    Meryl(220,5,3,80,200,10,5,"Elixir","Caretaker"),
    Bolti(220,5,3,80,200,10,5,"Boost","Mana beam");
    int maxHealth;
    int healthRefillRate;
    int inventorySize;
    int attackPower;
    int maxMagic;
    int magicRefillRate;
    int initialEP;
    ArrayList<String> abilities=new ArrayList<>();

    SupporterHero(int maxHealth, int healthRefillRate, int inventorySize, int attackPower, int maxMagic, int magicRefillRate, int initialEP,String ability3,String ability4) {
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

