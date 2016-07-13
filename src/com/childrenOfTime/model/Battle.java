package com.childrenOfTime.model;

import com.childrenOfTime.Completed;
import com.childrenOfTime.model.Warriors.Warrior;

import java.io.Serializable;
import java.util.ArrayList;

import static com.childrenOfTime.view.IOHandler.printOutput;

/**
 * Created by mohammadmahdi on 5/8/16.
 */
public class Battle implements Serializable {

    static int count = 0;

    public String getName() {
        return name;
    }

    public String name;
    protected String DefeatStory;
    private Player You;
    private Player Enemy;
    private Reward reward;
    private boolean itIsFirstTurn = true;
    ArtificialBrain aritificailBrain;
    ArrayList<Warrior> defualtFoes;

    public Battle(String name, String defeatStory, Reward reward, ArrayList<Warrior> defualtFoes) {
        this.defualtFoes = defualtFoes;
        this.name = name;
        DefeatStory = defeatStory;
        this.reward = reward;

        //TODO doros she inja
    }


    public void startSinglePlayer(Player human) {
        startPvP(human, createComputerBot());

    }


    public void startPvP(Player You, Player Enemy) {
        this.You = You;

        if (Enemy != null) {
            this.Enemy = Enemy;
        }

    }

    public Player createComputerBot() {
        Player bot = new Player(defualtFoes, name, PlayerType.Computer);
        this.initiateComputerEnemy(bot);
        return bot;
    }

    public ArrayList<Warrior> getDefualtFoes() {
        return this.defualtFoes;
    }


    public void playATurnIfComputer() {
        if (this.Enemy.playerType == PlayerType.Computer)
            this.aritificailBrain.playATurn();
    }


    /*
          public void playStory() {
              printOutput("Story:");
              printOutput(story);

          }
          */
    public String getDefeatMessage() {
        return ("Battle " + this.name + "Has Concluded!" + "\n" + "Defeat! You’ve failed to defeat all of your enemies" + Enemy.name);
    }

    public String getVictory() {
        return "Battle " + this.name + " Has Completed!     Winner : " + You.name + "\n" + "Victory! You’ve defeated all of your enemies";
    }


    private void initiateComputerEnemy(Player bot) {
        if (bot.playerType == PlayerType.Computer) {
            this.aritificailBrain = new ArtificialBrain(bot, You);
            // aritificailBrain.
        }
    }


    public void showCurrentFoeStats() {
        printOutput("Enemy Stats:");

        String toPrint = "";
        toPrint += "You’ve encountered:\n";

        for (Warrior w : Enemy.getMyTeam()) {
            int num = 0;
            toPrint += w.toString() + "\n";
            // } else {
            //    toPrint += num + " " + type.name() + " ";

        }
        printOutput(toPrint);
    }


    public void goToNextTurn() {

        You.aTurnHasPassed();
        if (this.Enemy.playerType == PlayerType.Computer) Enemy.aTurnHasPassed();
        showCurrentBattleInfo();


    }







//
//        while (true) {
//            try {
//                if (inputTemp.equals("done")) break;
//                boolean invalidCommand = true;
//
//
//                Pattern p = Pattern.compile(".*\\w+(\\s+.*\\w)?+\\?");
//                Matcher m = p.matcher(inputTemp);
//                boolean matchFound = m.matches();
//                if (matchFound) {
//                    String temp = inputTemp.substring(0, inputTemp.length() - 1);
//
//                    Item targetItem = currentPlayer.getItembyName(temp);
//                    Ability targetAbility = currentPlayer.findAbilityByName(temp);
//                    Hero targetHero = currentPlayer.findHeroByName(temp);
//                    if (targetHero != null) {
//                        targetHero.showHeroDescription();
//                    }    //TODO should throw an exception if item is not found
//                    if (targetAbility != null) {
//                        targetAbility.showDescription();
//                    }    //TODO should throw an exception if item is not found
//                    if (targetItem != null) {
//                        targetItem.showDescription();
//                    }
//                    //TODO should throw an exception if HERO is not found
//                    //TODO should throw an exception if ability is not found
//                    invalidCommand = false;
//                }
//
//                // p = Pattern.compile(".*\\w+\\s(.*\\w\\s)?Cast.*\\w(\\s.*\\w)?\\s\\d+on+");
//                // m = p.matcher(inputTemp);
//                // matchFound = m.matches();
//                if (inputTemp.contains("Cast")) {
//                    Hero castingHero = currentPlayer.findHeroByName(inputTemp.substring(0, inputTemp.indexOf("Cast") - 1));
//                    Ability castedAbility = currentPlayer.findAbilityByNameAndOwner(inputTemp.substring(inputTemp.indexOf("Cast") + 5, inputTemp.indexOf(" on ")), castingHero);
//                    //TODO badbakht shodim
//                    Warrior targetWarrior = null;
//                    try {
//
//                        targetWarrior = findWarriorByNameAndId(inputTemp.substring(inputTemp.indexOf(" on ") + 4, inputTemp.length() - 2), Integer.parseInt(inputTemp.substring(inputTemp.length() - 1, inputTemp.length())), currentPlayer);
//                    } catch (Exception e) {
//                        printOutput("You Have to write Like this :" +
//                                "\n Eley Cast OverPowered attack on Eley 0");
//                    }
//
//                    if (castingHero != null && castedAbility != null & targetWarrior != null) {
//                        currentPlayer.castAbility(castingHero, castedAbility, targetWarrior);
//                    }
//
//
//                    invalidCommand = false;
//                }
//
//                //p = Pattern.compile("\\w+\\s+Use+\\s+\\w+\\s+(.*\\w+\\s)?on+\\s+\\w+\\s?+.*?");
//                //m = p.matcher(inputTemp);
//                //matchFound = m.matches();
//                if (inputTemp.contains("Use")) {
//                    Hero usingHero = currentPlayer.findHeroByName(inputTemp.substring(0, inputTemp.indexOf("Use") - 1));
//                    Item usedItem = currentPlayer.getItembyNameAndOwner(inputTemp.substring(inputTemp.indexOf("Use") + 4, inputTemp.indexOf("on") - 1), usingHero);
//                    Warrior targetWarrior = findWarriorByNameAndId(inputTemp.substring(inputTemp.indexOf("on") + 3, inputTemp.length() - 1), 0, currentPlayer);
//
//                    if (usingHero != null & usedItem != null & usingHero != null) {
//                        currentPlayer.useItem(usingHero, usedItem, targetWarrior);
//                    }
//
//                    invalidCommand = false;
//
//                }
//
//                p = Pattern.compile(".*\\w+\\s(.*\\w\\s)?Attack.*\\w(\\s.*\\w)?\\s\\d+");
//
//                m = p.matcher(inputTemp);
//                matchFound = m.matches();
//                if (matchFound) {
//
//                    Hero attackingHero = currentPlayer.findHeroByName(inputTemp.substring(0, inputTemp.indexOf("Attack") - 1));
////                printOutput("'" + inputTemp.substring(0, inputTemp.indexOf("Attack") - 1) + "'");
//                    int id = Integer.parseInt(inputTemp.substring(inputTemp.lastIndexOf(" ") + 1, inputTemp.length()));
////                printOutput("'" + inputTemp.substring(0, inputTemp.indexOf("Attack") - 1) + "'");
////                printOutput("'" + inputTemp.substring(inputTemp.indexOf("Attack") + 7,
////                        inputTemp.lastIndexOf(" ") - 1) + "'");
//                    Foe targetFoe = findFoeByNameAndId(inputTemp.substring(inputTemp.indexOf("Attack") + 7,
//                            inputTemp.lastIndexOf(" ")), id);
//
//
//                    if (attackingHero != null && targetFoe != null) {
//                        if (!targetFoe.isDead) currentPlayer.giveAttack(attackingHero, targetFoe);
//                    }
//                    invalidCommand = false;
//                    if (targetFoe.isDead)
//                        throw new RuntimeException(targetFoe + "is Dead and You cannot attack him more !");
//
//                } else {
//
//                    p = Pattern.compile(".*\\w+\\s(.*\\w\\s)?Attack.*\\w(\\s.*\\w)?");
//                    m = p.matcher(inputTemp);
//                    matchFound = m.matches();
//                    if (matchFound) {
//
//                        Hero attackingHero = currentPlayer.findHeroByName(inputTemp.substring(0, inputTemp.indexOf("Attack") - 1));
//                        int id = 0;
//                        Foe targetFoe = findFoeByNameAndId(inputTemp.substring(inputTemp.indexOf("Attack") + 7
//                                , inputTemp.length()), id);
//
//
//                        if (attackingHero != null && targetFoe != null) {
//                            currentPlayer.giveAttack(attackingHero, targetFoe);
//                        }
//                        invalidCommand = false;
//                    }
//
//
//                }
//
//
//                p = Pattern.compile("again|help|information|done");
//                m = p.matcher(inputTemp);
//                matchFound = m.matches();
//                if (matchFound) {
//                    switch (inputTemp) {
//                        case "again":
//                        case "information":
//                            showCurrentBattleInfo(currentPlayer);
//                            invalidCommand = false;
//                            break;
//                        case "help":
//                            fightHelp();
//                            invalidCommand = false;
//                            break;
//                        /*
//                        case "done":
//                            ChildrenOfTime.getInstance().doneCommand(this);
//                            defeat();
//                            invalidCommand = false;
//                            break;
//                        */
//                    }
//                }
//
//
//                if (invalidCommand) {
//                    printOutput("Invalid Command ! Be careful , enemies are attacking you !");
//                }
//
//
//            } catch (Exception e) {
//                printOutput(e.getMessage());
//            }
//        }
//        actFoes();
//    }
//
//
//
//
//
//
//
//
//
//
//
//



    /*
    @Completed
    public void help() {
        ChildrenOfTime.getInstance().helpCommand(this);
    }
    */

//

//    @Completed
//    private Warrior findWarriorByNameAndId(String name, int id, Object currentPlayer) {
//
//        Foe foeToReturn = findFoeByNameAndId(name, id);
//        if (foeToReturn != null) return foeToReturn;
//        Hero heroToReturn = currentPlayer.findHeroByNameAndId(name, id);
//        if (heroToReturn != null) return heroToReturn;
//        return null;
//
//    }
//    @Completed
//    private Warrior findWarriorByName(String name, Object currentPlayer) {
//        Foe foeToReturn = findFoeByName(name);
//        if (foeToReturn != null) return foeToReturn;
//        Hero heroToReturn = currentPlayer.findHeroByName(name);
//        if (heroToReturn != null) return heroToReturn;
//        return null;
//    }
//    @Completed
//    private Foe findFoeByNameAndId(String name, Integer id) {
//        Foe currentFoe;
//        for (Foe foe : defualtFoes) {
//            if (foe.getName().equals(name) & foe.getId() == id) return foe;
//        }
//
//
//        // TODO Why Did you write this ? this is just fucking the project ?
//        /*
//        for (int i = 0; i < defualtFoes.size(); i++) {
//            currentFoe = defualtFoes.get(i);
//            if (currentFoe.equals(new Foe(name, StrengthOfFoes.Able, 0)) && currentFoe.getId() == id) return currentFoe;
//        }
//        */
//        return null;
//
//    }
////    @Completed
//    private Foe findFoeByName(String name) {
//        Foe currentFoe;
//        for (int i = 0; i < defualtFoes.size(); i++) {
//            currentFoe = defualtFoes.get(i);
//            if (currentFoe.equals(new Foe(name, StrengthOfFoes.Able, 0))) return currentFoe;
//        }
//        return null;
//    }


    public void finishTheBattle() {
        reward.giveReward(You);
        reward.giveReward(Enemy);
    }

    public int shouldBattleContinue() {
        if (You.isDefeated()) {
            finishTheBattle();
            return 1;
        }
        if (Enemy.isDefeated()) {
            finishTheBattle();
            return -1;
        }
        return 0;

    }

    @Completed
    private void showCurrentBattleInfo() {

//        printOutput("\n\nYour Heros:\n");
//        for (Warrior hero : Player1.getHeros()) {
//            printOutput(hero.getName() + " " + hero.getId());
//            hero.showCurrentTraits();
//            printOutput("\n---------------------\n");
//        }
//
//        printOutput("\n\nYour Foes:\n");
//        for (Foe foe : defualtFoes) {
//            printOutput(foe.getName() + " " + foe.getId());
//            printOutput(foe.showCurrentTraits());
//            printOutput("\n---------------------\n");
//        }

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
                "8\tagain\n" +
                "9\thelp\n" +
                "10\tinformation\n" +
                "11\tdone  -be careful! will result in your defeat!-");
    }

    @Completed
    public void storeHelp() {
        printOutput("Valid Commands in This Stage are:\n" +
                "\n" +
                "1\t(item name) + “?”  (item description)\n" +
                "2\t“Buy “ + (item name) + “ for “ + (hero name)\n" +
                "3\t“Sell “ + (item name) + “ of” + (hero name)\n" +
                "4\tagain\n" +
                "5\thelp\n" +
                "6\tinformation\n" +
                "7\tdone  -proceed to next stage-");

    }

    @Completed
    public void upgradeHelp() {
        printOutput("Valid Commands in This Stage are:\n" +
                "\n" +
                "1\t(hero name) + “ “ +(ability name) + “?”\n" +
                "2\t“Acquire “ + (ability name) + “ for “ + (hero name)\n" +
                "3\tagain\n" +
                "4\thelp\n" +
                "5\tinformation\n" +
                "6\tdone  -proceed to next stage-\t");
    }

    @Completed
    public void informationHelp() {
        printOutput("Valid Commands in This Stage are:\n" +
                "\n" +
                "1\t(enemy name) + “?”\n" +
                "2\t(Class name) + “?”\n" +
                "3\t(hero name) + “?”\n" +
                "4\tagain\n" +
                "5\thelp\n" +
                "6\tdone  -proceed to next stage-");
    }

    @Completed
    public void storyHelp() {
        printOutput("Valid Commands in This Stage are:\n" +
                "\n" +
                "1\tagain\n" +
                "2\thelp\n" +
                "4\tdone  -proceed to next stage-");
    }

    public Player getYou() {
        return You;
    }

    public Player getEnemy() {
        return Enemy;
    }
}





/*
    public void startStoreSession(boolean firstTime) {       // should handle again and help commands in it

        try {

        Object currentPlayer = ChildrenOfTime.getInstance().getPlayers().get(0);
        Store currentStore = Store.getStores().get(0);
        if (firstTime) {
            currentStore.showItems();       //not implemented yet
            currentPlayer.getHeros().forEach(Hero::showCurrentItems);
            printOutput("Your current wealth is: $" + currentPlayer.getCurrentWealth());
        }


        ChildrenOfTime.getInstance().firstTime = false;



            String inputTemp = getInput();
            boolean invalidCommand = true;

            Pattern p = Pattern.compile("\\w+\\?");
            Matcher m = p.matcher(inputTemp);
            boolean matchFound = m.matches();
            if (matchFound) {
                String temp = inputTemp.substring(0, inputTemp.length() - 1);
                InformationOfItems targetItem = currentStore.getStoreRawItembyName(temp);
                targetItem.getDescription();
                invalidCommand = false;
            }

            p = Pattern.compile("[Bb]uy+\\s.*\\w+.*(\\s+.*\\w)?+for+.*\\w");
        m = p.matcher(inputTemp);
        matchFound = m.matches();
        if (matchFound) {
            String itemName = inputTemp.substring(inputTemp.indexOf("Buy") + 4, inputTemp.indexOf("for") - 1);
            String targetHeroName = inputTemp.substring(inputTemp.indexOf("for") + 4, inputTemp.length());
            Hero targetHero = currentPlayer.findHeroByName(targetHeroName);
            if (targetHero != null) {
                InformationOfItems targetItem = currentStore.getStoreRawItembyName(itemName);
                if (targetItem != null) {
                    currentPlayer.buy(targetItem, targetHero); //TODO fix buy !!!
                    invalidCommand = false;
                }
            }
        }

            p = Pattern.compile("[Ss]ell+\\s+.*\\w+\\s.*(\\s+.*\\w)?+(of)+\\s+.*\\w");
            m = p.matcher(inputTemp);
            matchFound = m.matches();
            if (matchFound) {
                String itemName = inputTemp.substring(inputTemp.indexOf("Sell") + 5, inputTemp.indexOf("of") - 1);
                String targetHeroName = inputTemp.substring(inputTemp.indexOf("of") + 3, inputTemp.length());
                Hero targetHero = currentPlayer.findHeroByName(targetHeroName);
                if (targetHero != null) {
                    Item targetItem = currentPlayer.getItembyNameAndOwner(itemName, targetHero);
                    if (targetItem != null) {
                        currentPlayer.sell(targetItem, targetHero);
                        invalidCommand = false;
                    } else {
                        throw new TradeException("this Hero has no such Item in its inventory! (" + itemName + ")");
                    }
                }
            }
            p = Pattern.compile("again|help|information|done");
            m = p.matcher(inputTemp);
            matchFound = m.matches();
            if (matchFound) {
                invalidCommand = false;
                switch (inputTemp) {
                    case "again":
                        startStoreSession(true);
                        break;
                    case "help":
                        storeHelp();
                        break;
                    case "information":
                        currentPlayer.getHeros().forEach(Hero::showCurrentItems);
                        printOutput("Your current wealth is:" + currentPlayer.getCurrentWealth());
                        break;
                    case "done":
                        ChildrenOfTime.getInstance().doneCommand(this);
                        break;
                }
            }


            if (invalidCommand) {
                printOutput("Invalid Command");
            }


        } catch (Exception e) {
            printOutput(e.getMessage());
        }
    }
    @Completed
    public void startUpgradeSession(boolean showFirstThings) {
        try {
            Object currentPlayer = ChildrenOfTime.getInstance().getPlayers().get(0);
            if (showFirstThings) {
                printOutput("Your current experience is:" + currentPlayer.getCurrentExperience());
                for (int i = 0; i < currentPlayer.getHeros().size(); i++) {
                    printOutput(currentPlayer.getHeros().get(i).getName());
                    currentPlayer.getHeros().get(i).showAbDes();
                    //            currentPlayer.getHeros().get(i).showCurrentItems();
                }
            }

            ChildrenOfTime.getInstance().firstTime = false;

            String inputTemp = getInput();
            boolean invalidCommand = true;

            Pattern p = Pattern.compile("[Aa]cquire+\\s+.*\\w+(\\s+.*\\w)?+.*\\w");
            Matcher m = p.matcher(inputTemp);
            boolean matchFound = m.matches();
            if (matchFound) {
                String abilityName = inputTemp.substring(inputTemp.indexOf("Acquire") + 8, inputTemp.indexOf("for") - 1);
                String targetHeroName = inputTemp.substring(inputTemp.indexOf("for") + 4, inputTemp.length());
                Hero targetHero = currentPlayer.findHeroByName(targetHeroName);
                Ability targetAbility = targetHero.abilities.get(abilityName);
                if (targetAbility != null) {
                    currentPlayer.upgradeAbility(targetAbility, targetHero, 1);  //TODO IntegerGiri!
                    printOutput("Your current experience is:" + currentPlayer.getCurrentExperience());
                    invalidCommand = false;

                }
            }

            p = Pattern.compile("\\w+\\s+\\w+\\?");   //TODO you have to give some two part words for Abilities
            m = p.matcher(inputTemp);
            matchFound = m.matches();
            if (matchFound) {
                String temp[] = inputTemp.split("\\s");
                Hero targetHero = currentPlayer.findHeroByName(temp[0]);
                if (targetHero != null) {
                    Ability targetAbility = currentPlayer.findAbilityByNameAndOwner(temp[1].substring(0, temp[1].length() - 1), targetHero);
                    if (targetAbility != null) {
                        targetHero.abilityDescription(targetAbility);
                        invalidCommand = false;
                    }
                }
            }

            p = Pattern.compile("again|help|information|done");
            m = p.matcher(inputTemp);
            matchFound = m.matches();
            if (matchFound) {
                invalidCommand = false;
                switch (inputTemp) {
                    case "again":
                        startUpgradeSession(true);
                        break;
                    case "help":
                        upgradeHelp();
                        break;
                    case "information":
                        currentPlayer.getHeros().forEach(Hero::showCurrentTraits);
                        printOutput("Your current experience is:" + currentPlayer.getCurrentExperience());
                        break;
                    case "done":
                        ChildrenOfTime.getInstance().doneCommand(this);
                        break;
                }
            }

            if (invalidCommand) {
                printOutput("Invalid Command");
            }
        } catch (Exception e) {
            printOutput(e.getMessage());
        }
    }

*/

