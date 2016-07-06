package com.childrenOfTime.model;

import com.childrenOfTime.Completed;

/**
 * Created by mohammadmahdi on 5/8/16.
 */
public abstract class Warrior implements HasImpactHealth {
    protected int currentHealth;
    protected int maxHealth;
    protected int id;
    protected int attackPower;
    protected String name;
    protected boolean isDying=false;
    protected boolean isDead=false;


    @Completed
    public Warrior(String name, int id) {
        this.id = id;
        this.name = name;
    }

    public abstract void changeHealth(int quantity);

    public void changeHealthWithInsuranceOfLiving(int quantity) {
        currentHealth = currentHealth + quantity > maxHealth ? maxHealth : currentHealth + quantity;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public int getAttackPower() {
        return attackPower;
    }

    public void setAttackPower(int attackPower) {
        this.attackPower = attackPower;
    }

    public void setId(int id) {
        this.id = id;
    }
    public boolean isDying() {
        return isDying;
    }

    public boolean wasAlive() {
        return currentHealth > 0;
    }

    public boolean willDye(int q) {
        return currentHealth + q < 0;
    }
    public void changeAttackPower(int num) {
        this.attackPower += num;
    }

    public abstract String showCurrentTraits();

    public abstract void attack(Warrior warrior, Integer attackPower, Integer EPCost);

    public abstract void heal(Warrior warrior, Integer healingAmount);
}
