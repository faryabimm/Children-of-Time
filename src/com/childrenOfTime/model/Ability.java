package com.childrenOfTime.model;

import com.childrenOfTime.Completed;
import com.childrenOfTime.InProgress;
import com.childrenOfTime.exceptions.*;

import static com.childrenOfTime.view.IOHandler.printOutput;

/**
 * Created by mohammadmahdi on 5/8/16.
 */
public class Ability implements Durable {
    InformationOfAbilities info;
    protected int currentLevel;
    protected int currentCost;
    protected boolean isInCoolDown = false;
    private String name;

    @Completed
    public boolean isFullyUpgraded() {
        return currentLevel == 3;
    }

    @InProgress
    public void cast(Hero hero, Warrior warrior) throws AttackException {
        if (this.currentLevel == 0) {
            throw new AbilityNotAquiredException();
        }

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
                OverpoweredAttack(hero, (Foe) warrior);
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
                Elixir(hero, (Hero) warrior);
                break;
            case Caretaker:
                CareTaker(hero, (Hero) warrior);
                break;
            case Boost:
                Boost(hero, (Hero) warrior);
                break;
            case Mana:
                ManaBeam(hero, (Hero) warrior);
                break;
        }
    }


    @Completed
    public void upgrade(Hero hero, Player player) throws UpgradeException {
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
        }

        String aqORup = "";
        if (this.currentLevel > 1) {
            aqORup = "upgraded";
        }
        if (this.currentLevel == 1) {
            aqORup = "acquired";
        }
        printOutput(this.name + aqORup + " successfully, your current experience is: " +
                player.getCurrentExperience());

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
    private void FightTraining(Hero hero) throws NotEnoughXPException {
        hero.attackPower += 30;
    }
    @Completed
    private void WorkOut(Hero hero) {
        hero.maxHealth += 50;
    }
    @Completed
    private void MagicLessons(Hero hero) {
        hero.changeMaxMagic(50);
    }
    @Completed
    private void QuickAsABunny(Hero hero) {
        hero.changeEP(1);
    }
    @Completed
    private void OverpoweredAttack(Hero hero, Foe foe) throws AttackException {
        hero.changeEP(-2);
        try {
            hero.changeMagic(-50);
        } catch (NotEnoughMagicPointsException e) {
            hero.changeEP(2);
            throw new NotEnoughMagicPointsException();
        }

        hero.attackAuto(foe, (int) (hero.attackPower * (1 + 0.2 * currentLevel)));
        printOutput("Eley just did an overpowered attack on " +
                foe + " with " + 1 + 0.2 * currentLevel + " damage");
    }
    @Completed
    private void SwirlingAttack(Hero hero) {
        hero.swirlingisActivated = true;
        hero.damagePercent = 0.1 * currentLevel;
    }
    @InProgress
    private String Sacrifice(Hero hero) {
        //TODO we need a list of all foes
        return null;
    }
    @InProgress
    private void CriticalStrike(Hero hero) {

    }
    @Completed
    private void Elixir(Hero hero, Hero hero2) throws AttackException {
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
        printOutput("Meryl just healed " + hero2 + " with " + h + " health points");
    }
    @Completed
    private void CareTaker(Hero hero, Hero hero2) throws AttackException {
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

            printOutput("Meryl just gave " + hero2 + " 1 energy point");
        }
    }
    @Completed
    private void Boost(Hero hero, Hero hero2) throws AttackException {
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
        printOutput("Bolti just boosted " + hero2 + " with " + a + " power");
    }
    @Completed
    private void ManaBeam(Hero hero, Hero hero2) throws AttackException {
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
        printOutput("Bolti just helped " + hero2 + " with " + m + " magic points");
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
    @Override
    public boolean equals(Object obj) {
        if (obj == null || obj.getClass() != this.getClass()) return false;
        Ability other = (Ability) obj;
        if (!this.name.equals(other.name)) return false;
        return true;
    }
}

