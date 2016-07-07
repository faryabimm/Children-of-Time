package com.childrenOfTime.model.Warriors;

import com.childrenOfTime.model.Equip.AbilComps.Ability;

import java.util.ArrayList;

/**
 * Created by SaeedHD on 05/11/2016.
 */
public class HeroClass {
    int maxHealth_TEMP;
    int healthRefillRate;
    int maxMagic;
    int magicRefillRate;
    int attackPoewr_Temp;
    int initialEP;
    int inventorySize;
    String classDescription;
    String heroDescription;
    String BackStory;
    ArrayList<Ability> classAbilities;

    public HeroClass(int maxHealth_TEMP, int healthRefillRate, int maxMagic, int magicRefillRate, int attackPoewr_Temp
            , int initialEP, int inventorySize, String classDescription,
                     String heroDescription, String backStory, ArrayList<Ability> classAbilities) {
        this.maxHealth_TEMP = maxHealth_TEMP;
        this.healthRefillRate = healthRefillRate;
        this.maxMagic = maxMagic;
        this.magicRefillRate = magicRefillRate;
        this.attackPoewr_Temp = attackPoewr_Temp;
        this.initialEP = initialEP;
        this.inventorySize = inventorySize;
        this.classDescription = classDescription;
        this.heroDescription = heroDescription;
        BackStory = backStory;
        this.classAbilities = classAbilities;
    }


}
