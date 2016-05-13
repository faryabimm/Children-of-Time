package com.childrenOfTime.model;

import com.childrenOfTime.Completed;
import com.childrenOfTime.InProgress;

import static com.childrenOfTime.view.IOHandler.printOutput;

/**
 * Created by mohammadmahdi on 5/8/16.
 */
public class Item implements Durable {
    private int leftUsages;
    private InformationOfItems info;


    int timesBought = 0;


    public Item(String name) {
        if (name.equals("Magic stick")) {
            info = InformationOfItems.MagicStick;
        } else {
            info = InformationOfItems.valueOf(name.split(" ")[0]);
        }
        this.leftUsages = info.getAllowedUsages();
    }

    @InProgress
    public void use(Hero hero, Warrior warrior) {
        if (leftUsages <= 0) return;
        Hero hero2 = null;
        Foe foe = null;
        if (warrior instanceof Hero) {
            hero2 = (Hero) warrior;
        }
        if (warrior instanceof Foe) {
            foe = (Foe) warrior;
        }
        switch (info) {
            //type1
            case Toughen:
                hero2.maxHealth += 20;
                break;
            case Guide:
                hero2.changeMaxMagic(20);
                break;
            case Defy:
                hero2.changeAttackPower(8);
                break;
            case Sword:
                hero2.changeAttackPower(80);
                break;
            case Energy:
                hero2.changeEP(1);
                break;
            case Armor:
                hero2.maxHealth += 200;
                break;
            case MagicStick:
                hero2.changeMaxMagic(150);
                break;
            case Health:
                hero2.changeHealth(100);
                printOutput(hero + " just healed " + hero2 + " with 100 health points");
                break;
            case Magic:
                hero2.changeMagic(50);
                printOutput(hero + " just offered " + hero2 + " 50 magic points");
                break;
        }

        leftUsages--;
    }

    @Override
    public void wearOff() {
    }

    @Completed
    public void showDescription() {
        printOutput(info.getDescription());
    }

    @Completed
    public InformationOfItems getInfo() {
        return info;
    }

    public void aTurnHasPassed() {
    }

    public int getCurrentPrice() {
        return info.getInitialPrice() + info.getPriceIncreament() * timesBought;
    }
}
