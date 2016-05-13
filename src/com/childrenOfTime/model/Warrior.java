package com.childrenOfTime.model;

import com.childrenOfTime.Completed;

import static com.childrenOfTime.view.IOHandler.printOutput;

/**
 * Created by mohammadmahdi on 5/8/16.
 */
public abstract class Warrior {
    protected int currentHealth;
    protected int maxHealth;
    protected int id;
    protected int attackPower;
    protected String name;
    protected boolean isDying=false;
    protected boolean isDead=false;


    @Completed
    public Warrior(String name, int id) {
        this.name = name;
    }
    @Completed
    public void changeHealth(int quantitiy){

        if (currentHealth + quantitiy < 0) {
            currentHealth=0;
            if (this instanceof Hero) {
                this.isDying = true;
                if (ChildrenOfTime.getInstance().getPlayers().get(0).isAnyImmortalityPotionLeft()) {
                    ChildrenOfTime.getInstance().getPlayers().get(0).useImmortalityPotion();
                    printOutput(WarriorMessages.getDyingMessageForHero(ChildrenOfTime.getInstance().getPlayers().get(0), (Hero) this));
                } else {
                    isDead = true;
                    printOutput(WarriorMessages.getDiedMessageForHero((Hero) this));
                    printOutput("No Imortatlity Potions left.");

                }
            }
            if (this instanceof Foe) {
                this.isDead = true;
                printOutput(WarriorMessages.getDiedMessageForFoe((Foe) this));
            }
        }
        if (currentHealth + quantitiy > maxHealth) {
            currentHealth = maxHealth;
        } else currentHealth += quantitiy;
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

    public void changeAttackPower(int num) {
        this.attackPower += num;
    }

    public abstract String showCurrentTraits();
}
