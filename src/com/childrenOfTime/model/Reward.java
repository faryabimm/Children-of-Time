package com.childrenOfTime.model;


import java.io.Serializable;
import java.util.Random;

import static com.childrenOfTime.view.IOHandler.printOutput;

/**
 * Created by mohammadmahdi on 5/10/16.
 */
public class Reward implements Serializable {
    private int rewardedXP;
    private int reWardedMoney;
    private int ImmortalityPotion;
    Random randomMaker = new Random();


    public Reward(int rewardedXP, int reWardedMoney, int immortalityPotion) {
        this.rewardedXP = rewardedXP;
        this.reWardedMoney = reWardedMoney;
        ImmortalityPotion = immortalityPotion;

    }

    public void giveReward(Player player) {
        if (player.playerType == PlayerType.Computer) return;
        int rXP = this.rewardedXP;
        int rM = this.reWardedMoney;
        int rIM = this.ImmortalityPotion;

        if (Rules.giveRandomRewardByDifferentiationBetweenWinnerAndLoser) {
            boolean isWinner = !player.isDefeated();
            int probability = randomMaker.nextInt(101);
            probability = isWinner ? probability : (probability / 3);
            rXP = randomMaker.nextInt(this.rewardedXP) * probability / 100;
            rM = randomMaker.nextInt(reWardedMoney) * probability / 100;
            rIM = this.randomMaker.nextInt(ImmortalityPotion) * probability / 100;
        }
        player.changeCurrentExperience(rXP);
        player.changeCurrentWealth(rM);
        player.changeImmortalityPotion(rIM);
        printOutput(player.name + "  :  REWARDED " + "\n XP :  " + rXP + "\n Money :  " + rM + "\n IMP :  " + rIM);
    }


}
