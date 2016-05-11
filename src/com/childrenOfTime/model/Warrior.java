package com.childrenOfTime.model;

import com.childrenOfTime.Completed;
import com.childrenOfTime.ShouldBeImplementedInChildren;

/**
 * Created by mohammadmahdi on 5/8/16.
 */
public abstract class Warrior {
    protected int currentHealth;
    protected int id;
    protected String name;
    protected boolean isDying=false;
    protected boolean isDead=false;

    @Completed
    public Warrior(String name) {
        this.name = name;
    }
    @Completed
    public void changeHealth(int quantitiy){
        currentHealth=currentHealth+quantitiy;
        if (currentHealth<0) {
            currentHealth=0;
            isDying=true;
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
    public boolean isDying() {
        return isDying;
    }


}
