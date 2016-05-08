package com.childrenOfTime.model;

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

    public abstract void castAbility1();

    public abstract void castAbility2();

    public abstract void castAbility3();

    public abstract void castAbility4();

    public void attack() {
    }

    public ArrayList<Item> getInventoryItems() {

        //TODO implement
        return null;
    }

    public void addToInventory(Item item) {
    }

    public void removeFromInventory(Item item) {
    }

    public void applyItem(Item item) {
    }

    public abstract void die();









}
