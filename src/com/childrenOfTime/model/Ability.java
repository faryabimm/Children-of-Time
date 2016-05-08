package com.childrenOfTime.model;

/**
 * Created by mohammadmahdi on 5/8/16.
 */
public class Ability implements Durable {

    protected String name;
    protected int coolDownTime;
    protected int duration;
    protected int currentLevel;

    public int getCurrentLevel() {
        return currentLevel;
    }

    public boolean isFullyUpgraded() {
        //TODO implement
        return false;
    }

    @Override
    public void wearOff() {

    }

    public String getSuccessMessage() {
        //TODO implement
        return null;
    }
}
