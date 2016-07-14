package com.childrenOfTime.model.MultiPlayer;

import com.childrenOfTime.model.Act;
import com.childrenOfTime.model.Player;
import com.childrenOfTime.model.Warriors.Warrior;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by SaeedHD on 07/13/2016.
 */
public class PackTranslator {

    public static Act translate(Player player1, Player player2, TransferAct pack) {
        if (pack == null) return null;
        if (player1 == null) return null;
        if (player2 == null) return null;

        Warrior performer = null;
        LinkedList<Warrior> selectedTargets = new LinkedList<>();
        List<Integer> hashList = Arrays.asList(pack.getSelectedTargets());
        for (Warrior warrior : player1.getMyTeam()) {
            if (pack.getPlayerHashCode() == player1.hashCode() && warrior.hashCode() == pack.getPerformerHashCode()) {
                performer = warrior;
            }
            if (hashList.contains(warrior.hashCode())) {
                selectedTargets.add(warrior);
            }
        }
        for (Warrior warrior : player2.getMyTeam()) {
            if (pack.getPlayerHashCode() == player2.hashCode() && warrior.hashCode() == pack.getPerformerHashCode()) {
                performer = warrior;
            }
            if (hashList.contains(warrior.hashCode())) {
                selectedTargets.add(warrior);
            }

        }

        Act newAct = null;

        try {

            newAct = new Act(pack.getActionType(), performer, Player.toArray(selectedTargets), pack.getHashCodeOfAbility(), pack.getHashCodeOfItem());

        } catch (Exception e) {

        }
        return newAct;
    }
}
