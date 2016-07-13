package com.childrenOfTime.model.MultiPlayer;

import java.io.Serializable;

/**
 * Created by SaeedHD on 07/13/2016.
 */
public class TransferPack implements Serializable {
    int[] playerInfo;
    // XP , Money , IMMO
    int heroHashCode;
    int[] heroInfo;

    //  AP , H , MH , MP , MMP , EP ;

    public void setPlayerXP(int XP) {
        if (this.playerInfo == null) this.playerInfo = new int[3];
        this.playerInfo[0] = XP;
    }

    public void setPlayerMoney(int Money) {
        if (this.playerInfo == null) this.playerInfo = new int[3];
        this.playerInfo[1] = Money;
    }

    public void setPlayerImmo(int chIM) {
        if (this.playerInfo == null) this.playerInfo = new int[3];
        this.playerInfo[2] = chIM;
    }


    public TransferPack(int[] playerInfo) {
        this.playerInfo = playerInfo;
    }

    public TransferPack(int heroHashCode, int[] hashChanges) {
        this.heroHashCode = heroHashCode;
        this.heroInfo = hashChanges;
    }


}
