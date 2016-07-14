package com.childrenOfTime.model.MultiPlayer;

import com.childrenOfTime.model.Act;
import com.childrenOfTime.model.Player;
import com.childrenOfTime.model.Warriors.Warrior;

/**
 * Created by SaeedHD on 07/13/2016.
 */
public class PackTranslator {

    public static Act translate(Player player1, Player player2, TransferAct pack) {
        if (pack == null) return null;
        if (player1 == null) return null;
        if (player2 == null) return null;
        Player performerPlayer = null;

        for (Warrior warrior : player1.getMyTeam()) {
            if (performerPlayer.isDefeated()) {
//                pack.playerHashCode. ;
            }
        }
        return null;
    }
}
