package com.childrenOfTime.model;

import com.childrenOfTime.Completed;
import com.childrenOfTime.InProgress;
import com.childrenOfTime.exceptions.NoImmortalityPotionLeftException;
import com.childrenOfTime.exceptions.TradeException;
import com.childrenOfTime.exceptions.UpgradeException;

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
}

