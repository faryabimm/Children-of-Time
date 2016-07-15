package com.childrenOfTime.model;

import com.childrenOfTime.model.Equip.AbilComps.Ability;
import com.childrenOfTime.model.Warriors.ActionType;
import com.childrenOfTime.model.Warriors.Warrior;

/**
 * Created by SaeedHD on 07/13/2016.
 */
public class Act {

    private final ActionType actionType;
    private final Warrior performer;
    private final Warrior[] selectedTargets;
    private final Integer hashCodeOfAbility;
    private final Integer hashCodeOfItem;

    public Act(ActionType actionType, Warrior performer, Warrior[] selectedTargets, Integer hashCodeOfAbility, Integer hashCodeOfItem) {
        this.actionType = actionType;
        this.performer = performer;
        this.selectedTargets = selectedTargets;
        this.hashCodeOfAbility = hashCodeOfAbility;
        this.hashCodeOfItem = hashCodeOfItem;
    }

    public ActionType getActionType() {
        return actionType;
    }

    public Integer getHashCodeOfItem() {
        return hashCodeOfItem;
    }

    public Warrior getPerformer() {
        return performer;
    }

    public Warrior[] getSelectedTargets() {
        return selectedTargets;
    }

    public int getHashCodeOfAbility() {
        return hashCodeOfAbility;
    }

    public String toString() {
        String toReturn = "\n" + actionType.name() + "  \n  " + " Performer  :  " + performer;
        switch (this.getActionType()) {
            case AbilityCast:
                for (Ability ab : performer.abilities) {
                    if (ab.hashCode() == this.getHashCodeOfAbility()) {
                        toReturn += "\n   Ability : " + ab.getName() + "\n      Target Type :  " + ab.getTargetType() + "\n      Selected Targets If needed : ";
                        break;
                    }
                }
                break;
            case Attack:
            case BurnEP:
                toReturn += "\n   Selected Targets : ";


                break;

        }
        if (selectedTargets == null) return toReturn + "No Target !";

        for (Warrior warrior : this.selectedTargets) {
            toReturn += warrior;
        }
        return toReturn;
    }

}


