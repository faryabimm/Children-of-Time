package com.childrenOfTime.model.Equip.AbilComps;

import com.childrenOfTime.exceptions.*;
import com.childrenOfTime.model.BST;
import com.childrenOfTime.model.Equip.ItemComps.Messages;
import com.childrenOfTime.model.Equip.Target;
import com.childrenOfTime.model.Interfaces.Castable;
import com.childrenOfTime.model.Interfaces.TurnBase;
import com.childrenOfTime.model.Warriors.Warrior;
import com.sun.istack.internal.NotNull;
import com.sun.istack.internal.Nullable;

import javax.swing.*;
import java.io.Serializable;

import static com.childrenOfTime.view.IOHandler.printOutput;

/*
 * Created by SaeedHD on 07/05/2016.
 */
public class Ability implements Castable, TurnBase, Serializable {
    public static ImageIcon DEFAUL_AbilityImage;
    String name;
    String description;
    Upgrade baseState;
    private BST<Upgrade> Upgrades;
    Upgrade currentLevel;
    Messages messages;
    final Target targetType;
    final ImageIcon image;
    final int powerOutOften;

    public Ability(String name) {
        this.name = name;
        targetType = null;
        image = null;
        powerOutOften = 1;
    }

    @Override
    public String toString() {
        return "Ability{" +
                "name='" + name + '\'' +
                '}';
    }

    public Ability(@NotNull String name, @Nullable Messages messages, @NotNull BST<Upgrade> upgrades, @NotNull Target targetType, @Nullable ImageIcon image, @NotNull Integer powerOutOften) {
        if (image == null) image = DEFAUL_AbilityImage;
        if (messages == null) messages = new Messages();
        this.name = name;
        this.messages = messages;
        this.Upgrades = upgrades;
        this.targetType = targetType;
        this.image = image;
        this.powerOutOften = powerOutOften;

    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BST<Upgrade> getUpgrades() {
        return Upgrades;
    }

    public boolean isAcquired() {
        return currentLevel == null;
    }

    public void setBaseState(Upgrade baseState) {
        this.baseState = baseState;
    }

    @Override
    public void cast(Warrior caster, Warrior[] selectedTargets, Warrior[] allEnemies, Warrior[] allTeammates) {
        try {

        if (currentLevel == null) throw new AbilityNotAquiredException("You didn't acquire this");
            if (!currentLevel.recastable) {
            if (currentLevel.castedOnce) {
                return;
            }
            currentLevel.castedOnce = true;
        }
        currentLevel.cast(caster, selectedTargets, allEnemies, allTeammates);
        } catch (NotEnoughEnergyPointsException e) {
            throw new NotEnoughEnergyPointsException(messages.getEpFailureMessage());
        } catch (NotEnoughMagicPointsException e) {
            throw new NotEnoughMagicPointsException(messages.getMpSuccessMessage());
        } catch (AbilityInCooldownException e) {
            throw new AbilityInCooldownException(messages.getCoolDownFailureMessage());
        }

    }


    public Integer acquire(Warrior warrior, Warrior[] allEnemies, Warrior[] allTeammates) {
        this.baseState = Upgrades.getGodFatherElement();
        if (!baseState.getUpgradeBoolean()) throw new RequirementsNotMetException();
        this.currentLevel = Upgrades.getMinElement();
        currentLevel.acquired = true;
        printOutput("Ability : " + name + " Upgrade " + currentLevel.numberOfUpgrade + " is accessible now !");
        if (currentLevel.castJustAfterAcquire) cast(warrior, null, allEnemies, allTeammates);
        return currentLevel.getXPCost();
    }

    public Integer upgrade(Warrior performer, Integer i, Warrior[] allEnemies, Warrior[] allTeammates) throws UpgradeException {
        if (currentLevel == null) {
            if (!i.equals(baseState.getNumberOfUpgrade())) return 0;
            return acquire(performer, allEnemies, allTeammates);
        }

        Upgrade fake = new Upgrade(i);
        Upgrade result = (Upgrade) Upgrades.getVar(fake);
        if (result != null) {
            if (!result.getUpgradeBoolean()) throw new RequirementsNotMetException();
            currentLevel = result;
        }
        currentLevel.acquired = true;
        printOutput("Ability : " + name + " Upgrade " + currentLevel.numberOfUpgrade + " is accessible now !");

        if (currentLevel.castJustAfterAcquire) cast(performer, null, allEnemies, allTeammates);

        return result.getXPCost();
    }


    public void forceUpgrade(Warrior performer, Integer i, Warrior[] allEnemies, Warrior[] allTeammates) {
        if (i == null) return;
        Upgrade fake = new Upgrade(i);
        Upgrade result = (Upgrade) Upgrades.getVar(fake);
        if (result != null) {
            currentLevel = result;
        }
        if (currentLevel.castJustAfterAcquire) cast(performer, null, allEnemies, allTeammates);
    }


    public Upgrade getUpgradeByNumber(Integer i) {
        Object returnedUp = Upgrades.getVar(new Upgrade(i));
        ;
        if (returnedUp == null) throw new RuntimeException(" Such an Upgrade doesn't exist ! ");
        return (Upgrade) returnedUp;

    }

    @Override
    public void aTurnHasPassed() {
        currentLevel.aTurnHasPassed();
    }

    public void showDescription() {
        printOutput(description);
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Upgrade getBaseState() {
        return baseState;
    }

    public Upgrade getCurrentLevel() {
        return currentLevel;
    }



    public Target getTargetType() {
        return targetType;
    }

    public int getPowerOutOften() {
        return powerOutOften;
    }

    public void addToUpgrades(Upgrade upgrade) {
        this.Upgrades.add(upgrade);
        if (upgrade.isBaseUpgrade) baseState = upgrade;
        upgrade.setMessages(this.messages);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Ability ability = (Ability) o;

        return !(name != null ? !name.equals(ability.name) : ability.name != null);

    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }
}



