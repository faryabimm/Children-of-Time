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

    public abstract Warrior findTarget();

}
