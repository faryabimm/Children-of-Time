package com.childrenOfTime.model;

import com.childrenOfTime.model.Warriors.ActionType;
import com.childrenOfTime.model.Warriors.Warrior;

/**
 * Created by SaeedHD on 07/13/2016.
 */
public class Act {

    private final ActionType actionType;
    private final Warrior performer;
    private final Warrior[] selectedTargets;
    private final int hashCodeOfAbility;

    public Act(ActionType actionType, Warrior performer, Warrior[] selectedTargets, Integer hashCodeOfAbility) {
        this.actionType = actionType;
        this.performer = performer;
        this.selectedTargets = selectedTargets;
        this.hashCodeOfAbility = hashCodeOfAbility;
    }

    public ActionType getActionType() {
        return actionType;
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
}


