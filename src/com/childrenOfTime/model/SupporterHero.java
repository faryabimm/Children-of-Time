package com.childrenOfTime.model;

import com.childrenOfTime.InProgress;

import java.util.ArrayList;

/**
 * Created by mohammadmahdi on 5/8/16.
 */
public abstract class SupporterHero extends Hero {
    @InProgress
    public SupporterHero(int inventorySize, int maxMagic, int magicRefillRate, int currentMagic
            , int currentEnergyPoints, Inventory inventory, ArrayList<Ability> abilities){
            super(inventorySize,maxMagic,magicRefillRate,currentMagic,currentEnergyPoints, inventory,abilities);
        abilities.add(new Ability());//TODO Completing ....
        abilities.add(new Ability());//TODO Completing ....

    }}



