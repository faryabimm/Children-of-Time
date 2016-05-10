package com.childrenOfTime.model;

/**
 * Created by mohammadmahdi on 5/8/16.
 */
public abstract class Warrior {
    protected int currentHealth;
    protected int id;
    protected String name;
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

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
