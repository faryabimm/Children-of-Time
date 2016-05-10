package com.childrenOfTime.model;

import com.childrenOfTime.Completed;
import com.childrenOfTime.InProgress;
import com.childrenOfTime.NotImplementedYet;
import com.childrenOfTime.ShouldBeImplementedInChildren;
import com.childrenOfTime.exceptions.NotEnoughEnergyPointsException;
import com.childrenOfTime.exceptions.NotEnoughInventorySpaceException;

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

    @Completed
    public void attack(Foe enemy) throws NotEnoughEnergyPointsException{
        if (currentEnergyPoints<2){
            throw new NotEnoughEnergyPointsException();
        }
        else {
            currentEnergyPoints -= 2;
            enemy.changeHealth(this.attackPower);
        }
    }

    @NotImplementedYet
    public ArrayList<Item> getInventoryItems() {
        return inventory.getItems();
    }

    @Completed
    public void addToInventory(Item item){
        inventory.getItems().add(item);
    }

    @Completed
    public void removeFromInventory(Item item) {
        this.inventory.getItems().remove(item);
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
