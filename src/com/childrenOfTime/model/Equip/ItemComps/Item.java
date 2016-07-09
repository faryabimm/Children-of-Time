package com.childrenOfTime.model.Equip.ItemComps;

import com.childrenOfTime.Completed;
import com.childrenOfTime.exceptions.AbilityInCooldownException;
import com.childrenOfTime.exceptions.NotEnoughEnergyPointsException;
import com.childrenOfTime.exceptions.NotEnoughMagicPointsException;
import com.childrenOfTime.model.Equip.*;
import com.childrenOfTime.model.Interfaces.Castable;
import com.childrenOfTime.model.Interfaces.TurnBase;
import com.childrenOfTime.model.Warriors.Warrior;
import com.sun.prism.Image;

import java.util.ArrayList;

import static com.childrenOfTime.view.IOHandler.printOutput;

/**
 * Created by mohammadmahdi on 5/8/16.
 */
public class Item implements Castable, TurnBase {
    public static Image DEFAUL_ITEM_Image;

    private int leftUsages;
    private String name;
    private Integer Id = 0;
    private ItemType type;
    private ArrayList<Effects> effects;
    private Messages messages;
    // private Target targetType ;
    private boolean isInCoolDown = false;
    private Integer turnsLeftToCoolDown;
    private AlterPackage cost = new AlterPackage(null, null);
    Image image;


    public Item(String name, ItemType type, Messages messages, Target targetType, ArrayList<Effects> effects, AlterPackage sideCost, Image image) {
        this.name = name;
        this.type = type;
        this.messages = messages;
        this.effects = effects;
        this.cost = cost;
        this.leftUsages = type.getReusablityNumber();
    }

    @Completed
    public void showDescription() {
        printOutput(messages.getDescription());
    }


    @Override
    public void cast(Warrior performer, Warrior[] selectedTargets, Warrior[] allEnemies, Warrior[] allTeammates) {
        try {

            if (type.getHasCoolDown()) {
                if (isInCoolDown) throw new AbilityInCooldownException(messages.getCoolDownFailureMessage());
            }
            if (type.getReusable()) {
                this.leftUsages--;
            }
            cost.asCost(performer);
            EffectPerformer.performEffects(this.effects, performer, selectedTargets, allEnemies, allTeammates);
            if (type.getHasCoolDown()) {
                isInCoolDown = true;
            }
            printOutput(messages.getSuccessMessage());
        } catch (NotEnoughEnergyPointsException e) {
            throw new NotEnoughEnergyPointsException(messages.getEpFailureMessage());
        } catch (NotEnoughMagicPointsException e) {
            throw new NotEnoughEnergyPointsException(messages.getMpSuccessMessage());
        }
    }

    public void addToInvenTory(Inventory inventory) {

    }

    public void removedFromInventory(Warrior... targets) {
        for (Effects F : effects) {
            F.wearOff(targets);
        }
    }

    public Integer getCurrentPriceToBuy(int timesBought) {
        return type.getInitialPrice() + timesBought * type.getPriceInfaltionRate();
    }

    public Integer getCurrentPriceToSell() {
        return (int) (type.getInitialPrice() * (0.5 * type.getReusablityNumber() - leftUsages) / (type.getReusablityNumber()));
    }


    public Integer getInitialPrice() {
        return type.getInitialPrice();
    }

    public boolean canBeSold() {
        if (!type.getCanBeInInventory())
            return false;
        if (leftUsages <= 0) return false;
        return true;
    }

    @Override
    public void aTurnHasPassed() {
        if (!isInCoolDown) return;
        if (turnsLeftToCoolDown == 1) {
            this.isInCoolDown = false;
            turnsLeftToCoolDown = this.type.getCoolDownTime();
        } else {
            turnsLeftToCoolDown--;
        }
    }


    //Rubbish :


    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public int getLeftUsages() {
        return leftUsages;
    }

    public String getName() {
        return name;
    }

    public ItemType getType() {
        return type;
    }

    public ArrayList<Effects> getEffects() {
        return effects;
    }

    public Messages getMessages() {
        return messages;
    }

    public boolean isInCoolDown() {
        return isInCoolDown;
    }

    public Integer getTurnsLeftToCoolDown() {
        return turnsLeftToCoolDown;
    }

    public AlterPackage getCost() {
        return cost;
    }

    public Image getImage() {
        return image;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Item item = (Item) o;

        return name.equals(item.name);

    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
