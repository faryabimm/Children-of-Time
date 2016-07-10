package com.childrenOfTime.model.Warriors;

import com.childrenOfTime.model.Equip.AbilComps.Ability;

import java.util.ArrayList;

/**
 * Created by SaeedHD on 05/11/2016.
 */
public class HeroClass {


    Boolean CanHaveFBFeatures;
    //Just For Final Boss
    int attackPowerInHighHealth;
    int attackPowerInLowHealth;
    int healthBound;
    int[] heroBurningEnergy;
    private String mutationMessage;
    private String epBurningMessage;


    Boolean CanChangeMP;
    Boolean CanChangeEP;
    Boolean CanBuyItems;
    Boolean CanUseImmortalityPotions;
    Boolean CanUseRefillFeature;

    private String specificActionMessage;
    private String DyingActionMessage;


    String ClassName;

    Integer damageEfficiencyIntelligenceOutOfTen = 0;
    private int heroAttackingNumberPerTurn;
    int maxHealth;
    int healthRefillRate;
    int maxMagic;
    int magicRefillRate;
    int attackPower;
    int EPPerTurn;
    int inventorySize;
    String classDescription;
    String BackStory;
    ArrayList<Ability> classAbilities;

    public HeroClass(int maxHealth, int healthRefillRate, int maxMagic, int magicRefillRate, int attackPower
            , int EPPerTurn, int inventorySize, String classDescription, String backStory, ArrayList<Ability> classAbilities) {
        this.maxHealth = maxHealth;
        this.healthRefillRate = healthRefillRate;
        this.maxMagic = maxMagic;
        this.magicRefillRate = magicRefillRate;
        this.attackPower = attackPower;
        this.EPPerTurn = EPPerTurn;
        this.inventorySize = inventorySize;
        this.classDescription = classDescription;
        BackStory = backStory;
        this.classAbilities = classAbilities;
    }


    public Boolean getCanHaveFBFeatures() {
        return CanHaveFBFeatures;
    }

    public int getAttackPowerInHighHealth() {
        return attackPowerInHighHealth;
    }

    public int getAttackPowerInLowHealth() {
        return attackPowerInLowHealth;
    }

    public int[] getHeroBurningEnergy() {
        return heroBurningEnergy;
    }

    public String getMutationMessage() {
        return mutationMessage;
    }

    public String getEpBurningMessage() {
        return epBurningMessage;
    }

    public Boolean getCanChangeMP() {
        return CanChangeMP;
    }

    public Boolean getCanChangeEP() {
        return CanChangeEP;
    }

    public Boolean getCanBuyItems() {
        return CanBuyItems;
    }

    public Boolean getCanUseImmortalityPotions() {
        return CanUseImmortalityPotions;
    }

    public Boolean getCanUseRefillFeature() {
        return CanUseRefillFeature;
    }

    public String getSpecificActionMessage() {
        return specificActionMessage;
    }

    public String getDyingActionMessage() {
        return DyingActionMessage;
    }

    public String getClassName() {
        return ClassName;
    }

    public Integer getDamageEfficiencyIntelligenceOutOfTen() {
        return damageEfficiencyIntelligenceOutOfTen;
    }

    public int getHeroAttackingNumberPerTurn() {
        return heroAttackingNumberPerTurn;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public int getHealthRefillRate() {
        return healthRefillRate;
    }

    public int getMaxMagic() {
        return maxMagic;
    }

    public int getMagicRefillRate() {
        return magicRefillRate;
    }

    public int getAttackPower() {
        return attackPower;
    }

    public int getEPPerTurn() {
        return EPPerTurn;
    }

    public int getInventorySize() {
        return inventorySize;
    }

    public String getClassDescription() {
        return classDescription;
    }

    public String getBackStory() {
        return BackStory;
    }

    public ArrayList<Ability> getClassAbilities() {
        return classAbilities;
    }
}
