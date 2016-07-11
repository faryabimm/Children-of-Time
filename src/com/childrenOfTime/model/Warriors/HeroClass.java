package com.childrenOfTime.model.Warriors;

import com.childrenOfTime.model.Equip.AbilComps.Ability;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by SaeedHD on 05/11/2016.
 */
public class HeroClass implements Serializable {

    Boolean CanAttack;

    Boolean CanHaveFBFeatures;
    //Just For Final Boss
    int attackPowerInHighHealth;
    int attackPowerInLowHealth;
    int healthBound;


    Boolean CanBurnEP;
    int[] heroBurningEnergy;
    int EPBurningCost;
    private String mutationMessage;
    private String epBurningMessage;


    Boolean CanChangeMP;
    Boolean CanChangeEP;
    Boolean CanUseImmortalityPotions;
    Boolean CanUseRefillFeature;

    private String specificActionMessage;
    private String DyingActionMessage;


    String ClassName;

    Integer damageEfficiencyIntelligenceOutOfTen = 0;
    int maxHealth;
    int healthRefillRate;
    int maxMagic;
    int magicRefillRate;
    int initialEP;
    int inventorySize;
    String classDescription;
    String BackStory;
    ArrayList<Ability> classAbilities;

    public HeroClass(Boolean canAttack, Boolean canHaveFBFeatures, int attackPowerInHighHealth, int attackPowerInLowHealth, int healthBound, Boolean canBurnEP, int[] heroBurningEnergy, int EPBurningCost, String mutationMessage, String epBurningMessage, Boolean canChangeMP, Boolean canChangeEP, Boolean canBuyItems, Boolean canUseImmortalityPotions, Boolean canUseRefillFeature, String specificActionMessage, String dyingActionMessage, String className, Integer damageEfficiencyIntelligenceOutOfTen, int heroAttackingNumberPerTurn, int maxHealth, int healthRefillRate, int maxMagic, int magicRefillRate, int initialEP, int inventorySize, String classDescription, String backStory, ArrayList<Ability> classAbilities) {
        CanAttack = canAttack;
        CanHaveFBFeatures = canHaveFBFeatures;
        this.attackPowerInHighHealth = attackPowerInHighHealth;
        this.attackPowerInLowHealth = attackPowerInLowHealth;
        this.healthBound = healthBound;
        CanBurnEP = canBurnEP;
        this.heroBurningEnergy = heroBurningEnergy;
        this.EPBurningCost = EPBurningCost;
        this.mutationMessage = mutationMessage;
        this.epBurningMessage = epBurningMessage;
        CanChangeMP = canChangeMP;
        CanChangeEP = canChangeEP;
        CanUseImmortalityPotions = canUseImmortalityPotions;
        CanUseRefillFeature = canUseRefillFeature;
        this.specificActionMessage = specificActionMessage;
        DyingActionMessage = dyingActionMessage;
        ClassName = className;
        this.damageEfficiencyIntelligenceOutOfTen = damageEfficiencyIntelligenceOutOfTen;
        this.maxHealth = maxHealth;
        this.healthRefillRate = healthRefillRate;
        this.maxMagic = maxMagic;
        this.magicRefillRate = magicRefillRate;
        this.inventorySize = inventorySize;
        this.classDescription = classDescription;
        BackStory = backStory;
        this.classAbilities = classAbilities;

    }

    public HeroClass(HeroClass heroClass) {
        CanAttack = heroClass.CanAttack;
        CanHaveFBFeatures = heroClass.CanHaveFBFeatures;
        this.attackPowerInHighHealth = heroClass.attackPowerInHighHealth;
        this.attackPowerInLowHealth = heroClass.attackPowerInLowHealth;
        this.healthBound = heroClass.healthBound;
        CanBurnEP = heroClass.CanBurnEP;
        this.heroBurningEnergy = heroClass.heroBurningEnergy;
        this.EPBurningCost = heroClass.EPBurningCost;
        this.mutationMessage = heroClass.mutationMessage;
        this.epBurningMessage = heroClass.epBurningMessage;
        CanChangeMP = heroClass.CanChangeMP;
        CanChangeEP = heroClass.CanChangeEP;
        CanUseImmortalityPotions = heroClass.CanUseImmortalityPotions;
        CanUseRefillFeature = heroClass.CanUseRefillFeature;
        this.specificActionMessage = heroClass.specificActionMessage;
        DyingActionMessage = heroClass.DyingActionMessage;
        ClassName = heroClass.ClassName;
        this.damageEfficiencyIntelligenceOutOfTen = heroClass.damageEfficiencyIntelligenceOutOfTen;
        this.maxHealth = heroClass.maxHealth;
        this.healthRefillRate = heroClass.healthRefillRate;
        this.maxMagic = heroClass.maxMagic;
        this.magicRefillRate = heroClass.magicRefillRate;
        this.initialEP = heroClass.initialEP;
        this.inventorySize = heroClass.inventorySize;
        this.classDescription = heroClass.classDescription;
        BackStory = heroClass.BackStory;
        this.classAbilities = heroClass.classAbilities;
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


    public int getInitialEP() {
        return initialEP;
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

    public Boolean getCanAttack() {
        return CanAttack;
    }
}
