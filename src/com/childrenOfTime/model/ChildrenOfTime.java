package com.childrenOfTime.model;

import com.childrenOfTime.Completed;
import com.childrenOfTime.cgd.Store;
import com.childrenOfTime.exceptions.GameException;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.childrenOfTime.view.IOHandler.getInput;
import static com.childrenOfTime.view.IOHandler.printOutput;

/**
 * Created by mohammadmahdi on 5/7/16.
 */
public final class ChildrenOfTime {

    private static ChildrenOfTime instance;
    private ArrayList<Player> players = new ArrayList<>();
    private ArrayList<Battle> battles = new ArrayList<>();

    @Completed
    public static ChildrenOfTime getInstance() {
        if (instance == null) {
            instance = new ChildrenOfTime();
        }
        return instance;
    }

    @Completed
    private ChildrenOfTime() {


        ArrayList<Foe> battleFoes = new ArrayList<>();

        battleFoes.add(new Foe("Thug", StrengthOfFoes.Weak, 0));
        battleFoes.add(new Foe("Thug", StrengthOfFoes.Weak, 1));
        battleFoes.add(new Foe("Thug", StrengthOfFoes.Weak, 2));
        battleFoes.add(new Foe("Angel", StrengthOfFoes.Weak, 0));
        String storyTemp = "You’ve entered the castle, it takes a while for your eyes to get used to the darkness but\n" +
                "the horrifying halo of your enemies is vaguely visible. Angel’s unsettling presence and\n" +
                "the growling of thugs tell you that your first battle has BEGUN!";
        battles.add(new Battle(storyTemp, new Reward(20, 50), battleFoes));


        battleFoes = new ArrayList<>();
        battleFoes.add(new Foe("Thug", StrengthOfFoes.Able, 0));
        battleFoes.add(new Foe("Thug", StrengthOfFoes.Able, 1));
        battleFoes.add(new Foe("Angel", StrengthOfFoes.Weak, 0));
        battleFoes.add(new Foe("Tank", StrengthOfFoes.Weak, 0));
        storyTemp = "As you wander into the hall you realize the surrounding doors can lead your destiny to\n" +
                "something far worse than you expected. You know what’s anticipating you behind the only\n" +
                "open door but there’s no other choice.";
        battles.add(new Battle(storyTemp, new Reward(25, 60), battleFoes));


        battleFoes = new ArrayList<>();
        battleFoes.add(new Foe("Thug", StrengthOfFoes.Able, 0));
        battleFoes.add(new Foe("Thug", StrengthOfFoes.Mighty, 0));
        battleFoes.add(new Foe("Angel", StrengthOfFoes.Able, 0));
        battleFoes.add(new Foe("Tank", StrengthOfFoes.Weak, 0));
        storyTemp = "The door behind you is shut with a thunderous sound and you progress into the next hall\n" +
                "holding the first key that you’ve found, hoping to seek the second one.";
        battles.add(new Battle(storyTemp, new Reward(30, 70), battleFoes));


        battleFoes = new ArrayList<>();
        battleFoes.add(new Foe("Thug", StrengthOfFoes.Mighty, 0));
        battleFoes.add(new Foe("Thug", StrengthOfFoes.Mighty, 1));
        battleFoes.add(new Foe("Angel", StrengthOfFoes.Able, 0));
        battleFoes.add(new Foe("Tank", StrengthOfFoes.Able, 0));
        battleFoes.add(new Foe("Tank", StrengthOfFoes.Able, 1));
        storyTemp = "Running with the second key in your hand, you unlock the door back to the first hall and\n" +
                "use the first key to burst into your most terrifying nightmares.";
        battles.add(new Battle(storyTemp, new Reward(35, 80), battleFoes));


        battleFoes = new ArrayList<>();
        battleFoes.add(new Foe("Final Boss", StrengthOfFoes.Dramatic, 0));
        storyTemp = "You feel hopeless and exhausted as you stalk to the final door. What’s behind that door\n" +
                "makes your hearts pound and your spines shake with fear, but you came here to do one\n" +
                "thing and backing down is not an option.";
        battles.add(new Battle(storyTemp, new Reward(0, 0), battleFoes));


        ArrayList<Hero> battleHeros = new ArrayList<>();

        battleHeros.add(new Hero("Meryl", "Supporter", 0));
        battleHeros.add(new Hero("Bolti", "Supporter", 0));
        battleHeros.add(new Hero("Eley", "Fighter", 0));
        battleHeros.add(new Hero("Chrome", "Fighter", 0));
        players.add(new Player(battleHeros));

        Store store = new Store();
        Store.addStore(store);
    }

    @Completed
    public void startSinglePlayerMode() {
        Boolean firstTime = true;
        try {
            for (Battle battle : battles) {
                while (battle.battleState != BattleState.finished) {
                    switch (battle.battleState) {
                        case story:
                            battle.playStory();
                            getUserInput(battle);
                            break;
                        case information:
                            players.get(0).showCurrentHeroStats();
                            battle.showCurrentFoeStats();
                            getUserInput(battle);
                            break;
                        case upgradeSession:
                            battle.startUpgradeSession(firstTime);
                            firstTime = false;

                            break;
                        case storeSession:
                            battle.startStoreSession();
                            break;
                        case fight:
                            startFight(battle);
                            break;
                    }
                }

                if (players.get(0).isDefeated()) {
                    battle.defeat();
                    break;
                } else {
                    battle.victory();
                    battle.giveReward();
                }
            }

            if (players.get(0).isDefeated()) {
                singlePlayerGameOver();
            } else {
                singlePlayerGameCompleted();
            }

        } catch (GameException gameException) {
            printOutput(gameException.getMessage());
            gameException.printStackTrace();
        }

    }

    private void startFight(Battle battle) {
        printOutput("Battle #" + battle.id + ":");
        while (battle.battleState != BattleState.finished) {
            if (!players.get(0).isDefeated()) {
                printOutput("A new Turn Has Begun!");
                battle.initiateNextTurn();
            }
        }
    }

    @Completed
    private void singlePlayerGameCompleted() {
        String victoryMessage;
        victoryMessage = "The collector falls down on his knees, he’s strained and desperate but still tries to\n" +
                "drag himself toward Epoch. He knows his era has come to an end. The ancient time machine\n" +
                "calls you to end the disorder and bring unity under its glorious wings, now it’s your\n" +
                "turn to be the MASTERS OF TIME!";
        printOutput("Congratulations! You Won!");
        printOutput(victoryMessage);
    }

    @Completed
    private void singlePlayerGameOver() {
        printOutput("OOPS! You Lose! Try Again!");
    }

    @Completed
    private void getUserInput(Battle battle) {
        String userInput = getInput();

        switch (userInput) {
            case "again":
                againCommand(battle);
                getUserInput(battle);
                break;
            case "help":
                helpCommand(battle);
                getUserInput(battle);
                break;
            case "done":
                doneCommand(battle);
                break;
            default:
                Pattern p = Pattern.compile("\\w+\\?");
                Matcher m = p.matcher(userInput);
                boolean matchFound = m.matches();

                if (matchFound) {
                    String temp = userInput.substring(0, userInput.length() - 1);
                    informationInputIterpreter(temp);
                } else {
                    printOutput("Invalid Command!");
                }
                getUserInput(battle);
                break;
        }

    }

    private void informationInputIterpreter(String userInput) {
        try {
            printOutput(TypesOfFoes.valueOf(userInput).description);

        } catch (Exception e1) {
            try {
                printOutput(TypesOfHero.valueOf(userInput).classDescription);
            } catch (Exception e2) {
                try {
                    printOutput(SupporterHero.valueOf(userInput).heroDescription);

                } catch (Exception e3) {
                    try {
                        printOutput(FighterHero.valueOf(userInput).heroDescription);

                    } catch (Exception e4) {
                        printOutput("Invalid Command!");
                    }
                }
            }
        }
    }

    @Completed
    private void doneCommand(Battle battle) {

        switch (battle.battleState) {
            case story:
                battle.battleState = BattleState.information;
                break;
            case information:
                battle.battleState = BattleState.upgradeSession;
                break;
            case upgradeSession:
                battle.battleState = BattleState.storeSession;
                break;
            case storeSession:
                battle.battleState = BattleState.fight;
                break;
            case fight:
                battle.battleState = BattleState.finished;
                break;
        }

    }

    @Completed
    public void helpCommand(Battle battle) {
        switch (battle.battleState) {
            case story:
                battle.storyHelp();
                break;
            case information:
                battle.informationHelp();
                break;
            case upgradeSession:
                battle.upgradeHelp();
                break;
            case storeSession:
                battle.storeHelp();
                break;
            case fight:
                battle.fightHelp();
                break;
        }

    }

    @Completed
    private void againCommand(Battle battle) {

        switch (battle.battleState) {
            case story:
                battle.playStory();
                break;
            case information:
                players.get(0).showCurrentHeroStats();
                battle.showCurrentFoeStats();
                break;
            case upgradeSession:
                battle.startUpgradeSession(true);
                break;
            case storeSession:
                battle.startStoreSession();
                break;
            case fight:
                battle.showCurrentFoeStats();
                for (Player player : players) {
                    player.showCurrentHeroStats();
                }
                break;
        }


    }

    public ArrayList<Player> getPlayers() {
        return players;
    }
}


