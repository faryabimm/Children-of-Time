package com.childrenOfTime.model;

import com.childrenOfTime.view.IOHandler;
import java.util.ArrayList;

import static com.childrenOfTime.view.IOHandler.printOutput;

/**
 * Created by mohammadmahdi on 5/8/16.
 */
public class Battle {

    protected String story;
    protected int id;

    private ArrayList<Foe> foes = new ArrayList<>();

    public Battle(int id, String story) {
        this.story = story;
        this.id = id;
    }

    public void playStory() {
        printOutput("Story:");
        printOutput(story);

    }

    public void upgradeSession() {
    }

    public void storeSession() {
    }

    public void defeat() {
    }

    public void victory() {
    }

    public void giveRewards() {
    }

    public void showCurrentStats() {
    }

    public void help() {
    }

    public void fightSession() {
    }


}
