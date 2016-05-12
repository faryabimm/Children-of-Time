package com.childrenOfTime.model;

import com.childrenOfTime.Completed;
import com.childrenOfTime.InProgress;
import com.childrenOfTime.NotImplementedYet;
import com.childrenOfTime.exceptions.*;

/**
 * Created by mohammadmahdi on 5/8/16.
 */
public class Ability implements Durable {

    public static double damagePercent;

    protected String name;
    protected int coolDownTime;
    protected int duration;
    protected int currentLevel;
    protected int cost1;
    protected int cost2;
    protected int cost3;
    protected int currentCost;

    protected boolean isInCoolDown = false;


    public boolean isFullyUpgraded() {
        //TODO implement
        return false;
    }

    @NotImplementedYet
    public void cast(Hero hero, Player player, Warrior... warrior) throws NotEnoughEnergyPointsException {
        if (player.getCurrentExperience() < currentCost) {
            throw new NotEnoughEnergyPointsException("");
        }
        player.changeCurrentExperience(2);
    }


    @Override
    public void wearOff() {

    }

    public Ability(String name) {
        this.name = name;
    }

    /*
    switch (currentLevel) {
            case 0:
                throw new AbilityNotAquiredException();
            case 1 :
                player.changeCurrentExperience(-2);
        }
     */
    @Completed
    public void FightTraining(Hero hero) throws NotEnoughXPException {
        hero.attackPower += 30;
    }

    @Completed
    public void WorkOut(Hero hero) {
        hero.maxHealth += 50;
    }

    @Completed
    public void MagicLessons(Hero hero) {
        hero.changeMaxMagic(50);
    }


    @Completed
    public void QuickAsABunny(Hero hero) {
        hero.changeEP(1);
    }

    @Completed
    public String OverpoweredAttack(Hero hero, Foe foe) throws AttackException {
        hero.changeEP(-2);
        try {
            hero.changeMagic(-50);
        } catch (NotEnoughMagicPointsException e) {
            hero.changeEP(2);
            throw new NotEnoughMagicPointsException();
        }

        hero.attackAuto(foe, (int) (hero.attackPower * (1 + 0.2 * currentLevel)));
        return "Eley just did an overpowered attack on " +
                foe + " with " + 1 + 0.2 * currentLevel + " damage";
    }

    @Completed
    public void SwirlingAttack(Hero hero) {
        hero.swirlingisActivated = true;
        damagePercent = 0.1 * currentLevel;
    }

    @InProgress
    public String Sacrifice(Hero hero,) {
        //TODO we need a list of all foes
    }

    @InProgress
    public void CriticalStrike(Hero hero) {

    }

    @Completed
    public String Elixir(Hero hero, Hero hero2) throws AttackException {
        hero.changeEP(-2);
        try {
            hero.changeMagic(-60);
        } catch (NotEnoughMagicPointsException e) {
            hero.changeEP(2);
            throw new NotEnoughMagicPointsException();
        }
        int h = 0;
        switch (this.currentLevel) {
            case 1:
                if (isInCoolDown) throw new AbilityInCooldownException();
                hero2.changeHealth(100);
                h = 100;
                isInCoolDown = true;
                break;
            case 2:
                if (isInCoolDown) throw new AbilityInCooldownException();
                hero2.changeHealth(150);
                h = 150;
                isInCoolDown = true;
                break;
            case 3:
                h = 150;
                hero2.changeHealth(150);
                break;
        }
        return "Meryl just healed " + hero2 + " with " + h + " health points";
    }

    public String CareTaker(Hero hero, Hero hero2) throws AttackException {
        if (!hero2.equals(hero)) {
            hero.changeMagic(-30);
            hero2.changeEP(1);
            switch (this.currentLevel) {
                case 1:
                    if (this.isInCoolDown) {
                        hero2.changeEP(-1);
                        hero.changeMagic(30);
                        throw new AbilityInCooldownException();
                    }
                    //break is not needed
                case 2:
                    try {
                        hero.changeEP(-2);
                    } catch (NotEnoughEnergyPointsException e) {
                        hero.changeMagic(30);
                        hero2.changeEP(-1);
                        throw new NotEnoughEnergyPointsException();
                    }
                    break;
                case 3:
                    try {
                        hero.changeEP(-1);
                    } catch (NotEnoughEnergyPointsException e) {
                        hero.changeMagic(30);
                        hero2.changeEP(-1);
                        throw new NotEnoughEnergyPointsException();
                    }
                    break;


            }

            return "Meryl just gave " + hero2 + " 1 energy point";
        }
        return "";
    }

    private String Boost() {

    }


    public String getSuccessMessage() {
        //TODO implement
        return null;
    }

    public int getCurrentLevel() {
        return currentLevel;
    }
}
