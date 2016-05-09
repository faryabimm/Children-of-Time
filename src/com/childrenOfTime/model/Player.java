package com.childrenOfTime.model;

import com.childrenOfTime.Completed;
import com.childrenOfTime.exceptions.NoImmortalityPotionLeftException;
import com.childrenOfTime.exceptions.TradeException;
import com.childrenOfTime.exceptions.UpgradeException;

import java.util.ArrayList;

import static com.childrenOfTime.view.IOHandler.printOutput;

/**
 * Created by mohammadmahdi on 5/8/16.
 */
public class Player {

    private int currentWealth;
    private int currentExperience;
    private int immprtalityPotions = 3;
    private ArrayList<Hero> heros = new ArrayList<>();


    public void upgradeAbility(Ability ability, Hero target) throws UpgradeException {
    }

    public void buy(Item item, Hero target) throws TradeException {
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

    public boolean isDefeated() {
    }
}
