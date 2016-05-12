package com.childrenOfTime.model;

import com.childrenOfTime.Completed;
import com.childrenOfTime.InProgress;
import com.childrenOfTime.exceptions.AttackException;
import com.childrenOfTime.exceptions.NotEnoughEnergyPointsException;
import com.childrenOfTime.exceptions.NotEnoughMagicPointsException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by mohammadmahdi on 5/8/16.
 */
public class Hero extends Warrior {
    protected int currentMagic;
    protected int currentEnergyPoints;
    protected Inventory inventory;
    private HeroType heroType;

    //for Swirling ability
    boolean swirlingisActivated = false;
    public double damagePercent;



    InformationOfHeroes info;
    Map<String,Ability> abilities=new HashMap<String,Ability>();


    @Completed
    public void attackAuto(Foe enemy, int attackPower) {
        enemy.changeHealth(-attackPower);
        if (this.swirlingisActivated) {
            for (Foe f : foes) {   //TODO how to get access All Foes ?
                if (f.equals(enemy)) continue;
                f.changeAttackPower((int) (this.damagePercent * attackPower));
            }
        }
    }

    @Completed
    public void attackManual(Foe enemy, int attackPower) throws NotEnoughEnergyPointsException {
        changeEP(-2);
        enemy.changeHealth(-attackPower);
        if (this.swirlingisActivated) {
            for (Foe f : foes) {   //TODO how to get access All Foes ?
                if (f.equals(enemy)) continue;
                f.changeAttackPower((int) (Ability.damagePercent * attackPower));
            }
        }
    }

    @Completed
    public Hero(String name,String className){
        super(name);
        heroType=HeroType.valueOf(className);
        this.info = new InformationOfHeroes(heroType.healthRefillRate, heroType.inventorySize,
                heroType.maxMagic, heroType.magicRefillRate, heroType.initialEP, heroType.ability1, heroType.ability2);

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

        super.currentHealth = heroType.maxHealth;
        super.attackPower = heroType.attackPower;
        super.maxHealth = heroType.maxHealth;
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

    @InProgress
    public void castAbility(String abilityName, Warrior warrior) throws AttackException {
        abilities.get(abilityName).cast(this, warrior);
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

    public void changeEP(int num) throws NotEnoughEnergyPointsException {
        if (this.currentEnergyPoints + num < 0) {
            throw new NotEnoughEnergyPointsException();
        }
        this.currentEnergyPoints += num;
    }


    public void changeMagic(int i) throws NotEnoughMagicPointsException {
        if (this.currentMagic + i < 0) {
            throw new NotEnoughMagicPointsException();
        }
        this.currentMagic += i;
    }

    public void changeMaxMagic(int i) throws NotEnoughMagicPointsException {
        this.info.maxMagic += i;
    }
}
