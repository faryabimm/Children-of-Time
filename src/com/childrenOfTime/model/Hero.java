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
    protected int maxHealth;
    protected int healthRefillRate;
    protected int inventorySize;
    protected int attackPower;
    protected int maxMagic;
    protected int magicRefillRate;
    protected int currentHealth;
    protected int id;
    protected String name;
    protected boolean isAlive=true;
    protected int currentMagic;
    protected int currentEnergyPoints;
    protected Inventory inventory;
    protected ArrayList<Ability> abilities = new ArrayList<>();

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

    public Hero(String name,String className){
        switch (className){
            case "Supporter":
                switch (name) {
                    case "Meryl":
                        break;
                    case "Bolti":
                        break;
                }
                break;
            case "Fighter":
                case "Eley":
                    break;
                case "Chrome":
                    break;
        }
    }
    /*
    (int inventorySize, int maxMagic, int magicRefillRate, int currentMagic, int currentEnergyPoints, Inventory inventory, ArrayList<Ability> abilities) {
        this.inventorySize = inventorySize;
        this.maxMagic = maxMagic;
        this.magicRefillRate = magicRefillRate;
        this.currentMagic = currentMagic;
        this.currentEnergyPoints = currentEnergyPoints;
        this.inventory = inventory;
        this.abilities = abilities;
    }
*/
    public int getInventorySize() {
        return inventorySize;
    }

    public void setInventorySize(int inventorySize) {
        this.inventorySize = inventorySize;
    }

    public int getMaxMagic() {
        return maxMagic;
    }

    public void setMaxMagic(int maxMagic) {
        this.maxMagic = maxMagic;
    }

    public int getCurrentMagic() {
        return currentMagic;
    }

    public void setCurrentMagic(int currentMagic) {
        this.currentMagic = currentMagic;
    }

    public int getMagicRefillRate() {
        return magicRefillRate;
    }

    public void setMagicRefillRate(int magicRefillRate) {
        this.magicRefillRate = magicRefillRate;
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
