package com.childrenOfTime.model;

/**
 * Created by mohammadmahdi on 5/8/16.
 */
public abstract class Warrior {
    protected int maxHealth;
    protected int currentHealth;
    protected int healthRefillRate;
    protected int attackPower;
    protected int id;
    protected boolean isAlive=true;


    public abstract Warrior findTarget();

    public boolean isAlive() {
        return isAlive;
    }


    public void changeHealth(int quantitiy){
        currentHealth=currentHealth+quantitiy;
        if (currentHealth<0) {
            currentHealth=0;
            isAlive=false;
        }
    }
}
