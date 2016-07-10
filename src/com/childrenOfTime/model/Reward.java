package com.childrenOfTime.model;


import java.util.Random;

import static com.childrenOfTime.view.IOHandler.printOutput;

/**
 * Created by mohammadmahdi on 5/10/16.
 */
public class Reward {
    private int rewardedXP;
    private int reWardedMoney;
    private int ImmortalityPotion;
    Random randomMaker = new Random();


    public Reward() {
        this.rewardedXP = randomMaker.nextInt(5);
        this.reWardedMoney = randomMaker.nextInt(20);
        this.ImmortalityPotion = (randomMaker.nextInt(3));

    }

    public void giveRevard(Player player) {
        if (player.playerType == PlayerType.Computer) return;
        boolean isWinner = !player.isDefeated();


        int probability = randomMaker.nextInt(101);
        probability = isWinner ? probability : (probability / 3);
        player.changeCurrentExperience((this.rewardedXP * probability) / 100);
        player.changeCurrentWealth((this.reWardedMoney * probability) / 100);
        player.changeImmortalityPotion((this.ImmortalityPotion * probability) / 100);
        printOutput(player.name + "  :  REWARDED " + "\n XP :  " + this.reWardedMoney + "\n Money :  " + this.reWardedMoney + "\n IMP :  " + this.ImmortalityPotion);
    }


}
