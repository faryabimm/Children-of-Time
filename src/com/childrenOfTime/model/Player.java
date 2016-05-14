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
    public void buy(InformationOfItems item, Hero target) throws TradeException {
        if (item.isHasVolume() && target.inventory.getAvailableCapacity() == 0) {
            throw new NotEnoughInventorySpaceException("Your inventory has no empty space!");
        } else {
            if (item.getInitialPrice() > currentWealth) {
                throw new NotEnoughMoneyException("You don't have Enough Money to apply this upgrade\n" +
                        "your current Wealth : " + currentWealth + "$\nrequired Money : " +
                        item.getInitialPrice() + "$\nYou need " +
                        (item.getInitialPrice() - currentExperience) + "$ additional Money.");
            } else {
                Item newItem = new Item(item.getName());        // TODO HAS BUG!
                int cost = newItem.getCurrentPrice();
                newItem.timesBought++;
                currentWealth -= cost;
                target.inventory.getItems().add(newItem);
            }
        }
    }

    @Completed
    public Player(ArrayList<Hero> heros) {

        this.currentWealth = 40;
        this.currentExperience = 15;
        this.immprtalityPotions = 3;
        this.heros = heros;
    }
    @Completed
    public void sell(Item item, Hero target) throws TradeException {

        ArrayList<Item> heroItems = target.inventory.getItems();
        currentWealth += heroItems.get(heroItems.indexOf(item)).getInfo().getInitialPrice() / 2;
        target.inventory.getItems().remove(item);
    }
    @Completed
    public void useImmortalityPotion() throws NoImmortalityPotionLeftException {
        immprtalityPotions--;
    }
    @Completed
    public void showCurrentHeroStats() {
        String toReturn = "";
        for (Hero hero : heros) {
            toReturn += hero.toString();
        }

        printOutput(toReturn.substring(0, toReturn.length() - 2));
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
            throw new NotEnoughXPException("You don't have Enough XP points to apply this upgrade\n" +
                    "your current XP : " + currentExperience + " \nrequired XP : " + -num + "\nYou need " +
                    (-num - currentExperience) + " additional XP points.");
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
        Hero currentHero = findHeroByName(owner.name);


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
            try {
                if (currentHero.equals(new Hero(name, "Fighter", 0)))
                    return currentHero;
            } catch (Exception e) {
                try {
                    if (currentHero.equals(new Hero(name, "Supporter", 0)))
                        return currentHero;
                } catch (Exception e2) {
                    return null;
                }
                ;
            }
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

    @Completed
    public void aTurnHasPassed() {
        for (int i = 0; i < heros.size(); i++) {
            heros.get(i).aTurnHasPassed();
        }
    }

    @Completed
    public boolean isAnyImmortalityPotionLeft() {
        return immprtalityPotions != 0;
    }
}


