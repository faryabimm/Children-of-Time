package com.childrenOfTime.model;

import com.childrenOfTime.Completed;
import com.childrenOfTime.cgd.Store;
import com.childrenOfTime.view.IOHandler;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.childrenOfTime.view.IOHandler.getInput;
import static com.childrenOfTime.view.IOHandler.printOutput;

/**
 * Created by mohammadmahdi on 5/8/16.
 */
public class Battle {

    static int count = 0;

    protected String story;
    protected int id;
    public BattleState battleState = BattleState.story;
    private ArrayList<Foe> foes = new ArrayList<>();
    private Reward reward;

    public Battle(String story, Reward reward) {
        this.story = story;
        this.id = ++count;
        this.reward = reward;
    }

    @Completed
    public void playStory() {
        printOutput("Story:");
        printOutput(story);

    }

    @Completed
    public void defeat() {
        printOutput("Battle #" + (id + 1) + "Has Concluded!");
        printOutput("Defeat! You’ve failed to defeat all of your enemies");
    }

    @Completed
    public void victory() {
        printOutput("Battle #" + (id + 1) + "Has Completed!");
        printOutput("Victory! You’ve defeated all of your enemies");
    }


    @Completed
    public Reward giveReward() {
        return reward;
    }

    @Completed
    public void showCurrentFoeStats() {
        printOutput("Enemy Stats:");
        for (Foe foe : foes) {
            printOutput(foe.toString());
        }
    }


    @Completed
    public void help() {
        ChildrenOfTime.getInstance().helpCommand(this);
    }


    public void fightSession() {
        Player currentPlayer = ChildrenOfTime.getInstance().getPlayers().get(0);
        currentPlayer.getHeros().forEach(Hero::showCurrentTraits);
        foes.forEach(Foe::showCurrentTraits);

        String inputTemp = getInput();
        boolean invalidCommand = true;

        Pattern p = Pattern.compile("\\w+\\?");
        Matcher m = p.matcher(inputTemp);
        boolean matchFound = m.matches();
        if (matchFound) {
            String temp = inputTemp.substring(0, inputTemp.length() - 1);

            Item targetItem = currentPlayer.getItembyName(temp);
            Ability targetAbility = currentPlayer.findAbilityByname(temp);
            Hero targetHero = currentPlayer.findHeroByName(temp);
            targetItem.showDescription();       //TODO should throw an exception if item is not found
            targetHero.showDescription();       //TODO should throw an exception if HERO is not found
            targetAbility.showDescription();    //TODO should throw an exception if ability is not found
            invalidCommand = false;
        }

        p = Pattern.compile("\\w+\\s+cast+\\s+\\w+\\s+on+\\s+\\w+\\s?+.*?");
        m = p.matcher(inputTemp);
        matchFound = m.matches();
        if (matchFound) {
            String temp[] = inputTemp.split("\\s");
            Hero castingHero = currentPlayer.findHeroByName(temp[0]);
            Ability castedAbility = currentPlayer.findAbilityByname(temp[2]);

            Warrior targetWarrior;

            if (temp.length == 5) {
                targetWarrior = this.findWarriorByName(temp[4]);
            } else {
                targetWarrior = findFoeByNameAndId(temp[4], Integer.parseInt(temp[5]));
            }
            currentPlayer.castAbility(castingHero, castedAbility, targetWarrior);
            invalidCommand = false;
        }

        p = Pattern.compile("\\w+\\s+use+\\s+\\w+\\s+on+\\s+\\w+\\s?+.*?");
        m = p.matcher(inputTemp);
        matchFound = m.matches();
        if (matchFound) {
            String temp[] = inputTemp.split("\\s");
            Hero usingHero = currentPlayer.findHeroByName(temp[0]);
            Item usedItem = currentPlayer.getItembyName(temp[2]);

            Warrior targetWarrior;

            if (temp.length == 5) {
                targetWarrior = this.findWarriorByName(temp[4]);
            } else {
                targetWarrior = findFoeByNameAndId(temp[4], Integer.parseInt(temp[5]));
            }
            currentPlayer.useItem(usingHero, usedItem, targetWarrior);
            invalidCommand = false;
        }

        p = Pattern.compile("\\w+\\s+attack+\\s+\\w+\\s?+.*?");
        m = p.matcher(inputTemp);
        matchFound = m.matches();
        if (matchFound) {
            String temp[] = inputTemp.split("\\s");
            Hero attackingHero = currentPlayer.findHeroByName(temp[0]);
            Foe targetFoe;
            if (temp.length == 3) {
                targetFoe = this.findFoeByName(temp[2]);
            } else {
                targetFoe = findFoeByNameAndId(temp[2], Integer.parseInt(temp[3]));
            }
            currentPlayer.giveAttack(attackingHero, targetFoe);
            invalidCommand = false;
        }

        if (invalidCommand) {
            printOutput("Invalid Command");
        }
    }

    private Warrior findWarriorByName(String s) {
    }

    private Foe findFoeByNameAndId(String s, Integer integer) {
    }

    private Foe findFoeByName(String s) {
    }


    public void startStoreSession() {       // should handle again and help commands in it
        Player currentPlayer = ChildrenOfTime.getInstance().getPlayers().get(0);
        Store currentStore = Store.getStores().get(0);
        currentStore.showItems();
        currentPlayer.getHeros().forEach(Hero::showCurrentItems);
        printOutput("Your current wealth is:" + currentPlayer.getCurrentWealth());

        String inputTemp = getInput();
        boolean invalidCommand = true;

        Pattern p = Pattern.compile("\\w+\\?");
        Matcher m = p.matcher(inputTemp);
        boolean matchFound = m.matches();
        if (matchFound) {
            String temp = inputTemp.substring(0, inputTemp.length() - 1);
            Item targetItem = currentStore.getItembyName(temp);
            targetItem.showDescription();
            invalidCommand = false;
        }

        p = Pattern.compile("Buy+\\s+\\w+\\s+for+\\s+\\w");
        m = p.matcher(inputTemp);
        matchFound = m.matches();
        if (matchFound) {
            String temp[] = inputTemp.split("\\s");
            Hero targetHero = currentPlayer.findHeroByName(temp[3]);
            Item targetItem = currentStore.getItembyName(temp[1]);
            currentPlayer.buy(targetItem, targetHero);
            invalidCommand = false;
        }

        p = Pattern.compile("Sell+\\s+\\w+\\s+of+\\s+\\w");
        m = p.matcher(inputTemp);
        matchFound = m.matches();
        if (matchFound) {
            String temp[] = inputTemp.split("\\s");
            Hero targetHero = currentPlayer.findHeroByName(temp[3]);
            Item targetItem = currentStore.getItembyName(temp[1]);
            currentPlayer.sell(targetItem, targetHero);
            invalidCommand = false;
        }

        p = Pattern.compile("again|help|information|done");
        m = p.matcher(inputTemp);
        matchFound = m.matches();
        if (matchFound) {
            invalidCommand = false;
            switch (inputTemp) {
                case "again":
                    startStoreSession();
                    break;
                case "help":
                    storeHelp();
                    break;
                case "information":
                    currentPlayer.getHeros().forEach(Hero::showCurrentItems);
                    printOutput("Your current wealth is:" + currentPlayer.getCurrentWealth());
                    break;
                case "done":
                    battleState = BattleState.fight;
                    break;
            }
        }

        if (invalidCommand) {
            printOutput("Invalid Command");
        }
    }






    public void startUpgradeSession() {

        Player currentPlayer = ChildrenOfTime.getInstance().getPlayers().get(0);
        currentPlayer.getHeros().forEach(Hero::showCurrentTraits);
        printOutput("Your current experience is:" + currentPlayer.getCurrentExperience());

        String inputTemp = getInput();
        boolean invalidCommand = true;

        Pattern p = Pattern.compile("Acquire+\\s+\\w+\\s+for+\\s+\\w");
        Matcher m = p.matcher(inputTemp);
        boolean matchFound = m.matches();
        if (matchFound) {
            String temp[] = inputTemp.split("\\s");
            Hero targetHero = currentPlayer.findHeroByName(temp[3]);
            Ability targetAbility = targetHero.findAbilityName(temp[1]);
            currentPlayer.upgradeAbility(targetAbility, targetHero);
            invalidCommand = false;
        }

        p = Pattern.compile("\\w+\\s+\\w+\\?");
        m = p.matcher(inputTemp);
        matchFound = m.matches();
        if (matchFound) {
            String temp[] = inputTemp.split("\\s");
            Hero targetHero = currentPlayer.findHeroByName(temp[0]);
            Ability targetAbility = targetHero.findAbilityName(temp[1].substring(0, temp[1].length() - 1));
            targetHero.abilityDescription(targetAbility);
            invalidCommand = false;
        }

        p = Pattern.compile("again|help|information|done");
        m = p.matcher(inputTemp);
        matchFound = m.matches();
        if (matchFound) {
            invalidCommand = false;
            switch (inputTemp) {
                case "again":
                    startUpgradeSession();
                    break;
                case "help":
                    upgradeHelp();
                    break;
                case "information":
                    currentPlayer.getHeros().forEach(Hero::showCurrentTraits);
                    printOutput("Your current experience is:" + currentPlayer.getCurrentExperience());
                    break;
                case "done":
                    battleState = BattleState.storeSession;
                    break;
            }
        }

        if (invalidCommand) {
            printOutput("Invalid Command");
        }
    }

    public void startFight() {              // should handle again and help commands in it

    }

    @Completed
    public void fightHelp() {
        printOutput("Valid Commands in This Stage are:\n" +
                "\n" +
                "1\t(item name) + “?”\n" +
                "2\t(ability name) + “?”\n" +
                "3\t(hero name) + “?”\n" +
                "4\t(enemy name) + “?”\n" +
                "5\t(hero name) + “ cast “ + (ability name) + “ on “ + (hero name / enemy name and id)\n" +
                "6\t(hero name) + “ use “ + (item name) + “ on “ + (hero name / enemy name and id)\n" +
                "7\t(hero name) + “ attack “ + (enemy name and id)\n" +
                "8\tAgain\n" +
                "9\tHelp\n" +
                "10\tInformation\n" +
                "11\tDone  -be careful! will result in your defeat!-");
    }

    @Completed
    public void storeHelp() {
        printOutput("Valid Commands in This Stage are:\n" +
                "\n" +
                "1\t(item name) + “?”  (item description)\n" +
                "2\t“Buy “ + (item name) + “ for “ + (hero name)\n" +
                "3\t“Sell “ + (item name) + “ of” + (hero name)\n" +
                "4\tAgain\n" +
                "5\tHelp\n" +
                "6\tInformation\n" +
                "7\tDone  -proceed to next stage-");

    }


    @Completed
    public void upgradeHelp() {
        printOutput("Valid Commands in This Stage are:\n" +
                "\n" +
                "1\t(hero name) + “ “ +(ability name) + “?”\n" +
                "2\t“Acquire “ + (ability name) + “ for “ + (hero name)\n" +
                "3\tAgain\n" +
                "4\tHelp\n" +
                "5\tInformation\n" +
                "6\tDone  -proceed to next stage-\t");
    }

    @Completed
    public void informationHelp() {
        printOutput("Valid Commands in This Stage are:\n" +
                "\n" +
                "1\t(enemy name) + “?”\n" +
                "2\t(Class name) + “?”\n" +
                "3\t(hero name) + “?”\n" +
                "4\tAgain\n" +
                "5\tHelp\n" +
                "6\tInformation\n" +
                "7\tDone  -proceed to next stage-");
    }


    @Completed
    public void storyHelp() {
        printOutput("Valid Commands in This Stage are:\n" +
                "\n" +
                "1\tAgain\n" +
                "2\tHelp\n" +
                "3\tInformation\n" +
                "4\tDone  -proceed to next stage-");
    }
}
