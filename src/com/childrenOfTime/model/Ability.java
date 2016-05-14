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
    protected int currentLevel = 0;
    protected int leftTurnsToCoolDown;
    protected boolean isInCoolDown = false;


    @Completed
    public Ability(String name) {
        String firstWord = name.split(" ")[0];
        this.info = InformationOfAbilities.valueOf(firstWord);
        this.leftTurnsToCoolDown = this.info.coolDownTime;

    }


    @Completed
    public boolean isFullyUpgraded() {
        return currentLevel == 3;
    }

    @InProgress
    public void cast(Hero hero, Warrior warrior) throws AttackException {
        if (this.currentLevel == 0) {
            throw new AbilityNotAquiredException("Your " + hero.name + hero.id + " hero, doesn't have this ability (" +
                    info.name + ")");
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
                Sacrifice(hero);
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
    public void upgrade(Hero hero, Player player) {


        info.setUpgradeRequirements(hero);
        switch (this.currentLevel) {
            case 0:
                if (!info.upgradeRequirement1)
                    throw new RequirementsNotMetException("the requirements are not yet met" +
                            "for this upgrade.");
                player.changeCurrentExperience(-info.xp1);
                currentLevel += 1;
                break;
            case 1:
                if (!info.upgradeRequirement2)
                    throw new RequirementsNotMetException("the requirements are not yet met" +
                            "for this upgrade.");
                player.changeCurrentExperience(-info.xp2);
                currentLevel += 1;
                break;
            case 2:
                if (!info.upgradeRequirement3)
                    throw new RequirementsNotMetException("This ability (" + this.info.name + ") has reached its maximum level");
                player.changeCurrentExperience(-info.xp3);
                currentLevel += 1;
                break;
            case 3:
                throw new AbilityMaxLevelReachedException("“This ability cannot be upgraded anymore”");
        }

        String aqORup = "";
        if (this.currentLevel > 1) {
            aqORup = "upgraded";
        }
        if (this.currentLevel == 1) {
            aqORup = "acquired";
        }
        printOutput(this.info.name + " " + aqORup + " successfully, your current experience is: " + player.getCurrentExperience());

    }

    @Override
    public void wearOff() {

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
            throw new NotEnoughMagicPointsException("Your " + hero.name + hero.id + " hero doesn't have Enough MP to" +
                    " perform this move\ncurrent MP : " + hero.currentMagic + "\nrequired MP : " + 50 + "\nYou need " +
                    (50 - hero.currentMagic) + " additional Mps.");
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
    private void Sacrifice(Hero hero) throws AttackException {
        hero.changeEP(-3);
        try {
            hero.changeMagic(-60);
        } catch (NotEnoughMagicPointsException e) {
            hero.changeEP(3);
        }
        int H = 0;
        if (this.isInCoolDown) throw new AbilityInCooldownException("Abiliy is in cooldown");
        switch (currentLevel) {
            case 1:
                H = 40;
                break;
            case 2:
                H = 50;
                break;
            case 3:
                H = 60;
                break;

        }

        for (Foe foe : Battle.getFoes()) {
            foe.changeHealth(-3 * H);
            hero.changeHealth(-H);
        }
        printOutput("Chrome just sacrificed himself to damage all his enemies with " + 3 * H + " power");
    }

    @InProgress
    private void CriticalStrike(Hero hero) {
        hero.criticalIsActivated = true;
        hero.probability = 10 * (1 + currentLevel);

    }

    @Completed
    private void Elixir(Hero hero, Hero hero2) throws AttackException {
        hero.changeEP(-2);
        try {
            hero.changeMagic(-60);
        } catch (NotEnoughMagicPointsException e) {
            hero.changeEP(2);
            throw new NotEnoughMagicPointsException("Your " + hero.name + hero.id + " hero doesn't have Enough MP to" +
                    " perform this move\ncurrent MP : " + hero.currentMagic + "\nrequired MP : " + 60 + "\nYou need " +
                    (60 - hero.currentMagic) + " additional Mps.");
        }
        int h = 0;
        switch (this.currentLevel) {
            case 1:
                if (isInCoolDown)
                    throw new AbilityInCooldownException("This ability (" + this.info.name + ")is  in Cooldown");
                hero2.changeHealth(100);
                h = 100;
                isInCoolDown = true;
                break;
            case 2:
                if (isInCoolDown)
                    throw new AbilityInCooldownException("This ability (" + this.info.name + ")is  in Cooldown");
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
                        throw new AbilityInCooldownException("This ability (" + this.info.name + ")is  in Cooldown");
                    }
                    //break is not needed
                case 2:
                    try {
                        hero.changeEP(-2);
                    } catch (NotEnoughEnergyPointsException e) {
                        hero.changeMagic(30);
                        hero2.changeEP(-1);
                        throw new NotEnoughEnergyPointsException("Your " + hero.name + hero.id + " hero doesn't have Enough EP to" +
                                " perform this move\ncurrent EP : " + hero.currentEnergyPoints + "\nrequired EP : " + 30 + "\nYou need " +
                                (30 - hero.currentEnergyPoints) + " additional Eps.");
                    }
                    isInCoolDown = true;
                    break;
                case 3:
                    try {
                        hero.changeEP(-1);
                    } catch (NotEnoughEnergyPointsException e) {
                        hero.changeMagic(30);
                        hero2.changeEP(-1);
                        throw new NotEnoughEnergyPointsException("Your " + hero.name + hero.id + " hero doesn't have Enough MP to" +
                                " perform this move\ncurrent MP : " + hero.currentMagic + "\nrequired MP : " + 30 + "\nYou need " +
                                (30 - hero.currentMagic) + " additional Mps.");
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
            throw new NotEnoughMagicPointsException("Your " + hero.name + hero.id + " hero doesn't have Enough MP to" +
                    " perform this move\ncurrent MP : " + hero.currentMagic + "\nrequired MP : " + 50 + "\nYou need " +
                    (50 - hero.currentMagic) + " additional Mps.");
        }
        int a = 0;
        switch (this.currentLevel) {
            case 1:
                if (this.isInCoolDown)
                    throw new AbilityInCooldownException("This ability (" + this.info.name + ")is  in Cooldown");
                hero2.changeAttackPower(20);
                isInCoolDown = true;
                a = 20;
                break;
            case 2:
                if (this.isInCoolDown)
                    throw new AbilityInCooldownException("This ability (" + this.info.name + ")is  in Cooldown");
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
            throw new NotEnoughMagicPointsException("Your " + hero.name + hero.id + " hero doesn't have Enough MP to" +
                    " perform this move\ncurrent MP : " + hero.currentMagic + "\nrequired MP : " + 50 + "\nYou need " +
                    (50 - hero.currentMagic) + " additional Mps.");
        }
        int m = 0;
        switch (this.currentLevel) {
            case 1:
                if (this.isInCoolDown)
                    throw new AbilityInCooldownException("This ability (" + this.info.name + ")is  in Cooldown");
                hero2.changeMagic(50);
                isInCoolDown = true;
                m = 50;
                break;
            case 2:
                if (this.isInCoolDown)
                    throw new AbilityInCooldownException("This ability (" + this.info.name + ")is  in Cooldown");
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

    @Completed
    public void showDescription() {
        this.info.setDescription();
        printOutput(this.info.description);
    }


    @Override
    public boolean equals(Object obj) {
        if (obj == null || obj.getClass() != this.getClass()) return false;
        Ability other = (Ability) obj;
        if (!this.info.name.equals(other.info.name)) return false;
        return true;
    }

    @Override
    public void aTurnHasPassed() {
        if (leftTurnsToCoolDown == 1) {
            this.isInCoolDown = false;
            leftTurnsToCoolDown = this.info.coolDownTime;
        } else {
            leftTurnsToCoolDown--;
        }
    }
}

