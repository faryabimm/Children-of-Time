package com.childrenOfTime.model;

import com.childrenOfTime.NotImplementedYet;
import com.childrenOfTime.ShouldBeImplementedInChildren;

import java.awt.image.AreaAveragingScaleFilter;
import java.util.ArrayList;

/**
 * Created by mohammadmahdi on 5/8/16.
 */
public abstract class Hero extends Warrior {

    protected int inventorySize;
    protected int maxMagic;
    protected int magicRefillRate;
    protected int currentMagic;
    protected int currentEnergyPoints;
    protected Inventory inventory;
    protected ArrayList<Ability> abilities = new ArrayList<>();


    @ShouldBeImplementedInChildren
    public abstract void castAbility1();

    @ShouldBeImplementedInChildren
    public abstract void castAbility2();

    @ShouldBeImplementedInChildren
    public abstract void castAbility3();

    @ShouldBeImplementedInChildren
    public abstract void castAbility4();

    @NotImplementedYet
    public void attack() {
    }

    @NotImplementedYet
    public ArrayList<Item> getInventoryItems() {

        //TODO implement
        return null;
    }

    @NotImplementedYet
    public void addToInventory(Item item) {
    }

    @NotImplementedYet
    public void removeFromInventory(Item item) {
    }

    @NotImplementedYet
    public void applyItem(Item item) {
    }

    @ShouldBeImplementedInChildren
    public abstract void die();

    @NotImplementedYet
    public String toString() {
        return null;
    }









}
