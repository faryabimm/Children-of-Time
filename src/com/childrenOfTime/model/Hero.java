package com.childrenOfTime.model;

import com.childrenOfTime.Completed;
import com.childrenOfTime.InProgress;
import com.childrenOfTime.NotImplementedYet;
import com.childrenOfTime.ShouldBeImplementedInChildren;
import com.childrenOfTime.exceptions.NotEnoughEnergyPointsException;
import com.childrenOfTime.exceptions.NotEnoughInventorySpaceException;

import javax.sound.midi.MidiDevice;
import java.awt.image.AreaAveragingScaleFilter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by mohammadmahdi on 5/8/16.
 */
public abstract class Hero extends Warrior {
    protected int currentMagic;
    protected int currentEnergyPoints;
    protected Inventory inventory;
    private HeroType heroType;
    InformationOfHeroes info;
    Map<String,Ability> abilities=new HashMap<String,Ability>();

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
    public Hero(String name,String className){
        super(name);
        heroType=HeroType.valueOf(className);
        this.info=new InformationOfHeroes(heroType.maxHealth,heroType.healthRefillRate,heroType.inventorySize,
                heroType.attackPower,heroType.maxMagic,heroType.magicRefillRate,heroType.initialEP,heroType.ability1,heroType.ability2);

        switch (heroType){
            case Supporter:
                SupporterHero heroName=SupporterHero.valueOf(name);
                info.ability3=heroName.ability3;
                info.ability4=heroName.ability4;
                break;
            case Fighter:
                FighterHero heroName2=FighterHero.valueOf(name);
                info.ability3=heroName2.ability3;
                info.ability4=heroName2.ability4;
                break;
        }
        setAbilities(info);
        setCurrentMagic(info.maxMagic);
        setCurrentEnergyPoints(info.initialEP);
        setInventory(new Inventory(info.inventorySize));
        super.currentHealth=info.maxHealth;
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

    @Completed
    private void setAbilities(InformationOfHeroes info){
        abilities.put(info.ability1,new Ability(info.ability1));
        abilities.put(info.ability2,new Ability(info.ability2));
        abilities.put(info.ability3,new Ability(info.ability3));
        abilities.put(info.ability4,new Ability(info.ability4));
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
