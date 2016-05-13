package com.childrenOfTime.model;

import com.childrenOfTime.Completed;
import com.childrenOfTime.InProgress;
import com.childrenOfTime.exceptions.*;

/**
 * Created by mohammadmahdi on 5/8/16.
 */
public class Ability implements Durable {
    InformationOfAbilities info;
    protected int currentLevel;
    protected int currentCost;
    protected boolean isInCoolDown = false;

    @Completed
    public boolean isFullyUpgraded() {
        return currentLevel == 3;
    }

    @InProgress
    public String cast(Hero hero, Warrior warrior) throws AttackException {
        if (this.currentLevel == 0) {
            throw new AbilityNotAquiredException();
        }
        String s = "";
        switch (InformationOfAbilities.valueOf(this.info.name.split(" ")[0])) {
            case Fight:
                FightTraining(hero);
                break;
            case Work:
                WorkOut(hero);
                break;
            case Quick:
                QuickAsABunny(hero);
                break;
            case Magic:
                MagicLessons(hero);
                break;
            case Overpowered:
                s = OverpoweredAttack(hero, (Foe) warrior);
                break;
            case Swirling:
                SwirlingAttack(hero);
                break;
            case Sacrifice:
                //TODO Sacrifice()
                break;
            case Critical:
                CriticalStrike(hero);
                break;
            case Elixir:
                s = Elixir(hero, (Hero) warrior);
                break;
            case Caretaker:
                s = CareTaker(hero, (Hero) warrior);
                break;
            case Boost:
                s = Boost(hero, (Hero) warrior);
                break;
            case Mana:
                s = ManaBeam(hero, (Hero) warrior);
                break;
        }
        return s;
    }


    @Completed
    public String upgrade(Hero hero, Player player) throws UpgradeException {
        info.setUpgradeRequirements(hero);
        switch (this.currentLevel) {
            case 0:
                if (!info.upgradeRequirement1) throw new RequirementsNotMetException();
                player.changeCurrentExperience(info.xp1);
                currentLevel += 1;
                break;
            case 1:
                if (!info.upgradeRequirement2) throw new RequirementsNotMetException();
                player.changeCurrentExperience(info.xp2);
                currentLevel += 1;
                break;
            case 2:
                if (!info.upgradeRequirement3) throw new RequirementsNotMetException();
                player.changeCurrentExperience(info.xp3);
                currentLevel += 1;
                break;
            case 3:
                throw new AbilityMaxLevelReachedException();
                break;
        }
    }

    @Override
    public void wearOff() {

    }


    @Completed
    public Ability(String name) {
        String firstWord = name.split(" ")[0];
        this.info = InformationOfAbilities.valueOf(firstWord);

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
        hero.damagePercent = 0.1 * currentLevel;
    }

    @InProgress
    public String Sacrifice(Hero hero) {
        //TODO we need a list of all foes
        return null;
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

    @Completed
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
                    isInCoolDown = true;
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

    @Completed
    private String Boost(Hero hero, Hero hero2) throws AttackException {
        hero.changeEP(-2);
        try {
            hero.changeMagic(-50);
        } catch (NotEnoughMagicPointsException e) {
            hero.changeEP(2);
            throw new NotEnoughMagicPointsException();
        }
        int a = 0;
        switch (this.currentLevel) {
            case 1:
                if (this.isInCoolDown) throw new AbilityInCooldownException();
                hero2.changeAttackPower(20);
                isInCoolDown = true;
                a = 20;
                break;
            case 2:
                if (this.isInCoolDown) throw new AbilityInCooldownException();
                hero2.changeAttackPower(30);
                isInCoolDown = true;
                a = 30;
                break;
            case 3:
                hero2.changeAttackPower(30);
                a = 30;
                break;
        }
        return "Bolti just boosted " + hero2 + " with " + a + " power";
    }

    @Completed
    private String ManaBeam(Hero hero, Hero hero2) throws AttackException {
        hero.changeEP(-1);
        try {
            hero.changeMagic(-50);
        } catch (NotEnoughMagicPointsException e) {
            hero.changeEP(1);
            throw new NotEnoughMagicPointsException();
        }
        int m = 0;
        switch (this.currentLevel) {
            case 1:
                if (this.isInCoolDown) throw new AbilityInCooldownException();
                hero2.changeMagic(50);
                isInCoolDown = true;
                m = 50;
                break;
            case 2:
                if (this.isInCoolDown) throw new AbilityInCooldownException();
                hero2.changeMagic(80);
                isInCoolDown = true;
                m = 80;
                break;
            case 3:
                hero2.changeMagic(80);
                m = 80;
                break;
        }
        return "Bolti just helped " + hero2 + " with " + m + " magic points";
    }


    public String getSuccessMessage() {
        //TODO implement
        return null;
    }

    public int getCurrentLevel() {
        return currentLevel;
    }

    public void showDescription() {
    }
}
