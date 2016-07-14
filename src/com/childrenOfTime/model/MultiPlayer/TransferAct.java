package com.childrenOfTime.model.MultiPlayer;

import com.childrenOfTime.model.Warriors.ActionType;

import java.io.Serializable;

/**
 * Created by SaeedHD on 07/13/2016.
 */
public class TransferAct implements Serializable {
    int playerHashCode;
    private final ActionType actionType;
    private final Integer performerHashCode;
    private final Integer[] selectedTargets;
    private final Integer hashCodeOfAbility;
    private final Integer hashCodeOfItem;


    public TransferAct(int playerHashCode, Integer performerHashCode, ActionType actionType, Integer[] selectedTargets, Integer hashCodeOfAbility, Integer hashCodeOfItem) {
        this.playerHashCode = playerHashCode;
        this.performerHashCode = performerHashCode;
        this.actionType = actionType;
        this.selectedTargets = selectedTargets;
        this.hashCodeOfAbility = hashCodeOfAbility;
        this.hashCodeOfItem = hashCodeOfItem;
    }
}
