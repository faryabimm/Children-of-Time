package com.childrenOfTime.model;

import com.childrenOfTime.Completed;

import java.util.ArrayList;

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

        String storyTemp = "You’ve entered the castle, it takes a while for your eyes to get used to the darkness but\n" +
                "the horrifying halo of your enemies is vaguely visible. Angel’s unsettling presence and\n" +
                "the growling of thugs tell you that your first battle has BEGUN!";
        battles.add(new Battle(storyTemp, new Reward(20, 50)));

        storyTemp = "As you wander into the hall you realize the surrounding doors can lead your destiny to\n" +
                "something far worse than you expected. You know what’s anticipating you behind the only\n" +
                "open door but there’s no other choice.";
        battles.add(new Battle(storyTemp, new Reward(25, 60)));

        storyTemp = "The door behind you is shut with a thunderous sound and you progress into the next hall\n" +
                "holding the first key that you’ve found, hoping to seek the second one.";
        battles.add(new Battle(storyTemp, new Reward(30, 70)));

        storyTemp = "Running with the second key in your hand, you unlock the door back to the first hall and\n" +
                "use the first key to burst into your most terrifying nightmares.";
        battles.add(new Battle(storyTemp, new Reward(35, 80)));

        storyTemp = "You feel hopeless and exhausted as you stalk to the final door. What’s behind that door\n" +
                "makes your hearts pound and your spines shake with fear, but you came here to do one\n" +
                "thing and backing down is not an option.";
        battles.add(new Battle(storyTemp, new Reward(0, 0)));

    }

    @Completed
    public void startSinglePlayerMode() {

        for (Battle battle : battles) {
            while (battle.battleState != BattleState.finished) {
                switch (battle.battleState) {
                    case story:
                        battle.playStory();
                        getUserInput(battle);
                        break;
                    case information:
                        battle.showCurrentFoeStats();
                        getUserInput(battle);
                        break;
                    case upgradeSession:
                        battle.startUpgradeSession();
                        break;
                    case storeSession:
                        battle.startStoreSession();
                        break;
                    case fight:
                        battle.startFight();
                        break;
                }
            }

            if (players.get(0).isDefeated()) {
                battle.defeat();
                break;
            } else {
                battle.victory();
                battle.giveRewards();
            }
        }

        if (players.get(0).isDefeated()) {
            singlePlayerGameOver();
        } else {
            singlePlayerGameCompleted();
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
            case "Again":
                againCommand(battle);
                getUserInput(battle);
                break;
            case "Help":
                helpCommand(battle);
                getUserInput(battle);
                break;
            case "Done":
                doneCommand(battle);
                break;
            default:
                printOutput("Invalid Command!");
                getUserInput(battle);
                break;
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
    private void helpCommand(Battle battle) {
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
                battle.showCurrentFoeStats();
                break;
            case upgradeSession:
                battle.startUpgradeSession();
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
}
