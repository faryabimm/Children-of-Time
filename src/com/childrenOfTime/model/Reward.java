package com.childrenOfTime.model;


import com.childrenOfTime.Completed;

/**
 * Created by mohammadmahdi on 5/10/16.
 */
public class Reward {
    private int rewardedXP;
    private int reWardedMoney;

    @Completed
    public Reward(int rewardedXP, int reWardedMoney) {
        this.reWardedMoney = reWardedMoney;
        this.rewardedXP = rewardedXP;
    }

    @Completed
    public int getRewardedXP() {
        return rewardedXP;
    }

    @Completed
    public int getReWardedMoney() {
        return reWardedMoney;
    }
}
