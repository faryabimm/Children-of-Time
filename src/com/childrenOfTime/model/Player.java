package com.childrenOfTime.model;

import com.childrenOfTime.Completed;
import com.childrenOfTime.InProgress;
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


    public void upgradeAbility(Ability ability, Hero target) throws UpgradeException {
    }

    public void buy(Item item, Hero target) throws TradeException {
        //TODO You should check if we have enough inventory space or not , I defined a method that named "getAvailableSpace" in inventory
    }

    public void sell(Item item, Hero target) throws TradeException {
    }

    public void useImmortalityPotion(Hero target) throws NoImmortalityPotionLeftException {
    }

    @Completed
    public void showCurrentHeroStats() {
        for (Hero hero : heros) {
            printOutput(hero.toString());
        }
    }
    @InProgress
    public void Alaki(){
        WarriorMessages.getDiedMessageForHero(new Hero("saeed","salam"));
    }

    @InProgress
    public boolean isDefeated() {

        return false;
    }

    public int getImmprtalityPotions() {
        return immprtalityPotions;
    }

    public void changeCurrentExperience(int num) throws NotEnoughXPException {
        if (this.currentExperience + num < 0) {
            throw new NotEnoughXPException();
        } else {
            this.currentExperience += num;
        }
    }

    public int getCurrentExperience() {
        return currentExperience;
    }

    public ArrayList<Hero> getHeros() {
        return heros;
    }

    public int getCurrentWealth() {
        return currentWealth;
    }

    public Item getItembyName(String temp) {
    }

    public void castAbility(Hero castingHero, Ability castedAbility, Warrior targetFoe) {

        castingHero.useAbility(castedAbility, targetFoe);

    }

    public void useItem(Hero usingHero, Item usedItem, Warrior targetWarrior) {
        if (usingHero.inventory.getItems().contains(usedItem)) {
            usedItem.use(usingHero, targetWarrior);
        } else {
            throw new ItemNotAquiredException("Hero '" + usingHero.getName() + " "
                    + usingHero.getId() + "' doesnt" + "have this Item!");
        }
    }
    @Completed
    public Ability findAbilityByname(String name) {             //COOL!!!!!!!!!!!!!!
        for (int i = 0; i < heros.size(); i++) {
            Hero currentHero = heros.get(i);
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
            if (currentHero.equals(new Hero(name, "Fighter")) && currentHero.getId() == id) return currentHero;
        }
        return null;
    }
    @Completed
    public Hero findHeroByName(String name) {
        Hero currentHero;
        for (int i = 0; i < heros.size(); i++) {
            currentHero = heros.get(i);
            if (currentHero.equals(new Hero(name, "Fighter"))) return currentHero;
        }
        return null;
    }
}

