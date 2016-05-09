package com.childrenOfTime.model;

import com.childrenOfTime.Completed;
import com.childrenOfTime.InProgress;

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


    public static ChildrenOfTime getInstance() {
        if (instance == null) {
            instance = new ChildrenOfTime();
        }
        return instance;
    }

    private ChildrenOfTime() {
        String storyTemp = "You’ve entered the castle, it takes a while for your eyes to get used to the darkness but\n" +
                "the horrifying halo of your enemies is vaguely visible. Angel’s unsettling presence and\n" +
                "the growling of thugs tell you that your first battle has BEGUN!";
        int id = battles.size();
        battles.add(new Battle(id, storyTemp));

        storyTemp = "As you wander into the hall you realize the surrounding doors can lead your destiny to\n" +
                "something far worse than you expected. You know what’s anticipating you behind the only\n" +
                "open door but there’s no other choice.";
        id = battles.size();
        battles.add(new Battle(id, storyTemp));

        storyTemp = "The door behind you is shut with a thunderous sound and you progress into the next hall\n" +
                "holding the first key that you’ve found, hoping to seek the second one.";
        id = battles.size();
        battles.add(new Battle(id, storyTemp));

        storyTemp = "Running with the second key in your hand, you unlock the door back to the first hall and\n" +
                "use the first key to burst into your most terrifying nightmares.";
        id = battles.size();
        battles.add(new Battle(id, storyTemp));

        storyTemp = "You feel hopeless and exhausted as you stalk to the final door. What’s behind that door\n" +
                "makes your hearts pound and your spines shake with fear, but you came here to do one\n" +
                "thing and backing down is not an option.";
        id = battles.size();
        battles.add(new Battle(id, storyTemp));

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
            } else {
                battle.victory();
                battle.giveRewards();
            }
        }
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

    @InProgress
    private void doneCommand(Battle battle) {

    }

    @InProgress
    private void helpCommand(Battle battle) {

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
