package com.childrenOfTime.model;

import com.childrenOfTime.Completed;
import com.childrenOfTime.view.IOHandler;
import java.util.ArrayList;

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

    public void help() {
    }

    public void fightSession() {
    }


    public void startUpgradeSession() {     // should handle again and help commands in it

    }

    public void startStoreSession() {       // should handle again and help commands in it

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
