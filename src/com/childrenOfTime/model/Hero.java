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
    protected int currentMagic;
    protected int currentEnergyPoints;
    protected Inventory inventory;
    protected ArrayList<Ability> abilities = new ArrayList<>();
    private HeroType heroType;

    @Completed
    public void attack(Foe enemy) throws NotEnoughEnergyPointsException{
        if (currentEnergyPoints<2){
            throw new NotEnoughEnergyPointsException();
        }
        else {
            currentEnergyPoints -= 2;
            enemy.changeHealth(this.heroType.attackPower);
        }
    }

    @Completed
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

    @InProgress
    public void applyItem(Item item) {
        item.use(this);
    }

    @NotImplementedYet
    public abstract void die();

    @Completed
    public String toString() {
        return this.getName();
    }
    public Hero(){}

    @InProgress
    public Hero(String name,String className){
        heroType=HeroType.valueOf(className);
        switch (heroType){
            case Supporter:
                break;

            case Fighter:
                break;
        }
    }

    public int getInventorySize() {
        return heroType.inventorySize;
    }

    public void setInventorySize(int inventorySize) {
        this.heroType.inventorySize = inventorySize;
    }

    public int getMaxMagic() {
        return heroType.maxMagic;
    }

    public void setMaxMagic(int maxMagic) {
        this.heroType.maxMagic = maxMagic;
    }

    public int getCurrentMagic() {
        return currentMagic;
    }

    public void setCurrentMagic(int currentMagic) {
        this.currentMagic = currentMagic;
    }

    public int getMagicRefillRate() {
        return heroType.magicRefillRate;
    }

    public void setMagicRefillRate(int magicRefillRate) {
        this.heroType.magicRefillRate = magicRefillRate;
    }

    public int getCurrentEnergyPoints() {
        return currentEnergyPoints;
    }

    public void setCurrentEnergyPoints(int currentEnergyPoints) {
        this.currentEnergyPoints = currentEnergyPoints;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }
}
