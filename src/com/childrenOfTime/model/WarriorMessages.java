package com.childrenOfTime.model;

import com.childrenOfTime.Completed;

/**
 * Created by SaeedHD on 05/11/2016.
 */
public class WarriorMessages {

        @Completed
        public static String getDiedMessageForHero(Hero hero){
            //TODO I do not know whats this method s assignment
            String s=hero.name + " is dead and so is the spirit of this adventure, Game Over! ”";
            return s;
        }



        @Completed
        public static String getDyingMessageForHero(Player player,Hero hero){
            String s = hero.name +" is dying, immortality potion was used for reincarnation process, you now have "
                    + player.getImmprtalityPotions() + "immortality potions left";
            return s;
        }
        @Completed
        public static String getDiedMessageForFoe(Foe foe){
            return foe.getName() + foe.getId() + " has died" ;
        }

        @Completed
        public static String getSuccessfulAttackMessage(Hero hero,Foe foe){
            return hero.getName() + " has successfully attacked " + foe.getName() + foe.getName() + foe.getId() + " with " + hero.info.attackPower + " power"
        }
}


