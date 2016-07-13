package com.childrenOfTime.model.MultiPlayer;

import com.childrenOfTime.model.Player;
import com.childrenOfTime.model.Warriors.Warrior;

/**
 * Created by SaeedHD on 07/13/2016.
 */
public class PackTranslator {

    public static void translate(Player player, TransferPack pack) {
        if (pack == null) return;
        if (player == null) return;
        if (pack.playerInfo != null) {
            player.setCurrentExperience(pack.playerInfo[0]);
            player.setCurrentWealth(pack.playerInfo[1]);
            player.setImmprtalityPotions(pack.playerInfo[2]);
        }
        if (pack.heroInfo != null) {
            if (player.getMyTeam() == null) return;
            for (Warrior hero : player.getMyTeam()) {
                if (hero.hashCode() == pack.heroHashCode) {
                    hero.setCurrentAttackPower(pack.heroInfo[0]);
                    hero.setCurrentHealth(pack.heroInfo[1]);
                    hero.getInfo().setMaxHealth(pack.heroInfo[2]);
                    hero.setCurrentMagic(pack.heroInfo[3]);
                    hero.getInfo().setMaxMagic(pack.heroInfo[4]);
                    hero.setCurrentEP(pack.heroInfo[5]);
                }
            }
        }
    }
}
