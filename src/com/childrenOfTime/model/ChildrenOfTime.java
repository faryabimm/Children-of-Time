package com.childrenOfTime.model;

import java.util.ArrayList;

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

    public void startSinglePlayerMode() {

        for (Battle battle : battles) {
            battle.playStory();
            //TODO to be continued

        }

    }
}
