package com.childrenOfTime.model;

import com.childrenOfTime.Completed;
import com.childrenOfTime.exceptions.*;

import java.util.ArrayList;

import static com.childrenOfTime.view.IOHandler.printOutput;

/**
 * Created by mohammadmahdi on 5/8/16.
 */
public class Player {
    //TODO When You wanna add a Hero for a player You have to check if the player has another hero with the same name or not,assigning id for a hero by method setID is your duty
    private int currentWealth;
    private int currentExperience;
    private int immprtalityPotions = 3;
    private ArrayList<Hero> heros = new ArrayList<>();

    @Completed
    public void upgradeAbility(Ability ability, Hero targetHero) throws UpgradeException {
        ability.upgrade(targetHero, this);
    }
    @Completed
    public void buy(Item item, Hero target) throws TradeException {
        if (item.getInfo().isHasVolume() && target.inventory.getAvailableCapacity() == 0) {
            throw new NotEnoughInventorySpaceException();
        } else {
            if (item.getInfo().getInitialPrice() > currentWealth) {
                throw new NotEnoughMoneyException();
            } else {
                int cost = item.getInfo().getInitialPrice() +
                        item.getInfo().getPriceIncreament() * item.timesBought;
                item.timesBought++;
                currentWealth -= cost;
                target.inventory.getItems().add(item);
            }
        }
    }
    @Completed
    public void sell(Item item, Hero target) throws TradeException {

        ArrayList<Item> heroItems = target.inventory.getItems();
        currentWealth += heroItems.get(heroItems.indexOf(item)).getInfo().getInitialPrice() / 2;
        target.inventory.getItems().remove(item);
    }
    @Completed
    public void useImmortalityPotion(Hero target) throws NoImmortalityPotionLeftException {
        target.revivedWithImmortalityPotion();
    }
    @Completed
    public void showCurrentHeroStats() {
        for (Hero hero : heros) {
            printOutput(hero.toString());
        }
    }
    @Completed
    public boolean isDefeated() {
        boolean isDefeated = false;
        for (int i = 0; i < heros.size(); i++) {
            if (heros.get(i).isDead) isDefeated = true;
            break;
        }
        return isDefeated;
    }
    @Completed
    public int getImmprtalityPotions() {
        return immprtalityPotions;
    }
    @Completed
    public void changeCurrentExperience(int num) throws NotEnoughXPException {
        if (this.currentExperience + num < 0) {
            throw new NotEnoughXPException();
        } else {
            this.currentExperience += num;
        }
    }
    @Completed
    public int getCurrentExperience() {
        return currentExperience;
    }
    @Completed
    public ArrayList<Hero> getHeros() {
        return heros;
    }
    @Completed
    public int getCurrentWealth() {
        return currentWealth;
    }
    @Completed
    public void castAbility(Hero castingHero, Ability castedAbility, Warrior targetFoe) {

        castingHero.useAbility(castedAbility, targetFoe);

    }
    @Completed
    public void useItem(Hero usingHero, Item usedItem, Warrior targetWarrior) {
        if (usingHero.inventory.getItems().contains(usedItem)) {
            usedItem.use(usingHero, targetWarrior);
        } else {
            throw new ItemNotAquiredException("Hero '" + usingHero.getName() + " "
                    + usingHero.getId() + "' doesnt" + "have this Item!");
        }
    }
    @Completed
    public Ability findAbilityByNameAndOwner(String name, Hero owner) {             //COOL!!!!!!!!!!!!!!
        Hero currentHero = null;

        for (int i = 0; i < heros.size(); i++) {
            if (heros.get(i).equals(owner)) {
                currentHero = heros.get(i);
                break;
            } else {
                return null;
            }
        }
        for (String keyAbilityName : currentHero.abilities.keySet()) {
            if (keyAbilityName.equals(name)) {
                return currentHero.abilities.get(keyAbilityName);
            }
        }
        return null;
    }
    @Completed
    public Ability findAbilityByName(String name) {
        Hero currentHero = null;

        for (int i = 0; i < heros.size(); i++) {
            for (String keyAbilityName : currentHero.abilities.keySet()) {
                if (keyAbilityName.equals(name)) {
                    return currentHero.abilities.get(keyAbilityName);
                }
            }
        }

        return null;
    }
    @Completed
    public void giveAttack(Hero attackingHero, Foe targetFoe) {
        attackingHero.attackManual(targetFoe);
    }
    @Completed
    public Hero findHeroByNameAndId(String name, int id) {
        Hero currentHero;
        for (int i = 0; i < heros.size(); i++) {
            currentHero = heros.get(i);
            if (currentHero.equals(new Hero(name, "Fighter", 0)) && currentHero.getId() == id) return currentHero;
        }
        return null;
    }
    @Completed
    public Hero findHeroByName(String name) {
        Hero currentHero;
        for (int i = 0; i < heros.size(); i++) {
            currentHero = heros.get(i);
            if (currentHero.equals(new Hero(name, "Fighter", 0))) return currentHero;
        }
        return null;
    }
    @Completed
    public Item getItembyNameAndOwner(String name, Hero usingHero) {
        Item item = new Item(name);
        if (usingHero.inventory.getItems().contains(item)) {
            Inventory temp = usingHero.inventory;
            return temp.getItems().get(temp.getItems().indexOf(item));
        }
        return null;
    }
    @Completed
    public Item getItembyName(String name) {
        Item toReturn = null;
        for (int i = 0; i < heros.size(); i++) {
            Hero usingHero = heros.get(i);
            toReturn = getItembyNameAndOwner(name, usingHero);
            if (toReturn != null) {
                return toReturn;
            }
        }
        return null;
    }
}

