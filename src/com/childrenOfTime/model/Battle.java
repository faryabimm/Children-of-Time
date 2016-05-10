package com.childrenOfTime.model;

import com.childrenOfTime.Completed;
import com.childrenOfTime.view.IOHandler;
import java.util.ArrayList;

import static com.childrenOfTime.view.IOHandler.printOutput;

/**
 * Created by mohammadmahdi on 5/8/16.
 */
public class Battle {

    protected String story;
    protected int id;
    public BattleState battleState = BattleState.story;

    private ArrayList<Foe> foes = new ArrayList<>();

    public Battle(int id, String story) {
        this.story = story;
        this.id = id;
    }

    @Completed
    public void playStory() {
        printOutput("Story:");
        printOutput(story);

    }

    public void defeat() {
    }

    public void victory() {
    }

    public void giveRewards() {
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

    public void fightHelp() {
    }

    public void storeHelp() {
    }

    public void upgradeHelp() {
    }

    public void informationHelp() {
    }

    public void storyHelp() {
    }
}
