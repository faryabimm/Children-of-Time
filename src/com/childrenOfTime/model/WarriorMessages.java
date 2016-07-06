package com.childrenOfTime.model;

import com.childrenOfTime.Completed;

import java.util.List;

/**
 * Created by SaeedHD on 05/11/2016.
 */
public class WarriorMessages {

        @Completed
        public static String getDiedMessageForHero(Hero hero){
            //TODO I do not know whats this method s assignment
            String s = hero.name + hero.getId() + " is dead and so is the spirit of this adventure, Game Over! ”";
            return s;
        }



        @Completed
        public static String getDyingMessageForHero(Player player,Hero hero){
            String s = hero.name + hero.getId() + " is dying, immortality potion was used for reincarnation process, you now have "
                    + player.getImmprtalityPotions() + "immortality potions left";
            return s;
        }
        @Completed
        public static String getDiedMessageForFoe(Foe foe){
            return foe.getName() + foe.getId() + " has died" ;
        }

        @Completed
        public static String getSuccessfulAttackMessage(Hero hero, Warrior enemy, int attackpower) {
            return hero.getName() + hero.getId() + " has successfully attacked " + enemy.getName() + enemy.getId() + " with " + attackpower + " power";
        }

    @Completed
    public static String getAction_1_MessageForFoe(Foe foe, Warrior... targets) {

        String toReturn = "";
        switch (foe.getName()) {
            case "Thug":
                toReturn = foe + " just attacked " + targets[0] + targets[0].getId() + " with " + foe.attackPower + " power";
                break;
            case "Angel":
                Foe t = (Foe) targets[0];
                toReturn = foe + " just healed " + t + " with " + foe.healingAmount + " health points";
                break;
            case "Tank":
                toReturn = " Tank just damaged all of your heroes with " + foe.attackPower + " power";
                break;
            case "Final":
                toReturn = " Collector just attacked " + targets[0] + " with " + foe.attackPower + " power";
                break;

        }
        return toReturn;
    }

    @Completed
    public static String getAction_2_MessageForFoe(Foe foe, Warrior... targets) {
        String toReturn = "";
        switch (foe.getName()) {
            case "FinalBoss":
                Hero h = (Hero) targets[0];
                toReturn = "Collector just burned " + h.getCurrentEnergyPoints() + " energy points from " + h;
                break;
        }
        return toReturn;
    }

    @Completed
    public static String getMutationMessageForFinalBoss(Foe foe) {
        String toReturn = "";
        switch (foe.getName()) {
            case "FinalBoss":
                toReturn = "Collector has mutated";
                break;
        }
        return toReturn;
    }


    public static String getSuccessfulSwirlingAttackMessage(Hero hero, List<Warrior> enemyList, int i) {
        String toReturn = hero.getName() + hero.getId() + " has successfully swirling attacked" + " with " + i + " power to : ";
        for (Warrior enemy : enemyList) {
            toReturn += "\t" + enemy.getName() + enemy.getId() + ",";
        }
        return toReturn;
    }
}


