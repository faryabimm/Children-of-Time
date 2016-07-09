package com.childrenOfTime.model;

import com.childrenOfTime.exceptions.NoImmortalityPotionLeftException;
import com.childrenOfTime.exceptions.NotEnoughMoneyException;
import com.childrenOfTime.exceptions.NotEnoughXPException;
import com.childrenOfTime.exceptions.TradeException;
import com.childrenOfTime.model.Equip.AbilComps.Ability;
import com.childrenOfTime.model.Equip.ItemComps.Item;
import com.childrenOfTime.model.Warriors.Warrior;

import java.util.ArrayList;
import java.util.Collection;

import static com.childrenOfTime.view.IOHandler.printOutput;

/**
 * Created by mohammadmahdi on 5/8/16.
 */
public class Player {

    PlayerType playerType;
    String name;
    //TODO When You wanna add a Hero for a player You have to check if the player has another hero with the same name or not,assigning id for a hero by method setID is your duty
    private int currentWealth;
    private int currentExperience;
    private int immprtalityPotions = 3;
    private ArrayList<Item> itemsBougt;
    private Collection<Warrior> myTeam;
    private Collection<Warrior> enemyTeam;


    public void useImmortalityPotion() throws NoImmortalityPotionLeftException {
        if (immprtalityPotions - 1 < 0) {
            throw new NoImmortalityPotionLeftException(name + " : No Immortality Potion Left");
        }
    }

    public void checkForImmortatlitryRequest() {


        Thread immortalityRequest = new Thread(() -> {
            while (true) {

                for (Warrior myHero : myTeam) {
                    try {
                        Thread.sleep(0, 100000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if (myHero.doesAskForImmortalityPotion()) {
                        myHero.PlayerAllowsUsingImmortality = true;
                        useImmortalityPotion();
                    }
                }
            }
        });

        immortalityRequest.setPriority(Thread.NORM_PRIORITY + 2);
        immortalityRequest.setDaemon(true);
        immortalityRequest.setName("Immortality Request");
        immortalityRequest.start();

    }

    private int getNumbersBought(Item item) {

        int count = 0;
        for (Item item2 : itemsBougt) {
            if (item.equals(item2)) {
                count++;
            }
        }
        return count;
    }

    public void upgradeAbility(Ability ability, Warrior targetHero, int id) {


        changeCurrentExperience(-ability.getUpgradeByNumber(id).getXPCost());
        targetHero.upgradeAbility(ability, id, toArray(this.enemyTeam), toArray(this.myTeam));
    }

    public void buy(Item item, Warrior hero) throws TradeException {

        int itemsPrice = item.getCurrentPriceToBuy(getNumbersBought(item));
        changeCurrentWealth(-itemsPrice);
        try {
            hero.IWannaBuyItemForYou(item, toArray(this.enemyTeam), toArray(this.myTeam));
        } catch (RuntimeException e) {
            changeCurrentWealth(itemsPrice);
            throw new RuntimeException(e.getMessage());
        }
        //TODO Jaye In moshakhas shavad
        printOutput(item.getName() + " bought Successfully\n" +
                "your Current Wealth is: $" + getCurrentWealth());
    }

    public void castAbility(Warrior castingHero, Ability castedAbility, Warrior... selectedTargets) {

        castingHero.castAbility(castedAbility, selectedTargets, toArray(this.enemyTeam), toArray(this.myTeam));

    }

    public void useItem(Warrior usingHero, Item usedItem, Warrior... selectedTargets) {


        usingHero.useItem(usedItem, selectedTargets, toArray(this.enemyTeam), toArray(this.myTeam));
    }

    public void sell(Item item, Warrior target) throws TradeException {


        target.IWannaSellThisItem(item);
        int itemCurrenPriceToSell = item.getCurrentPriceToSell();
        changeCurrentWealth(itemCurrenPriceToSell);
        printOutput("Item " + item.getName() + " was successfully sold for $" +
                itemCurrenPriceToSell + " and was removed form (" +
                target.getIdentity() + ") Hero.");
        printOutput("your Current Wealth is: $" + getCurrentWealth());
    }

    public boolean isDefeated() {
        switch (this.playerType) {
            case Human:
                int countDead = 0;
                for (Warrior warrior : this.myTeam) {
                    if (warrior.isDead()) countDead++;
                    if (countDead == Rules.NUMBER_OF_HEROS_DYE_FROM_HUMAN_PLAYER_TO_DEFEAT || countDead == this.myTeam.size())
                        return true;
                }
                return false;
            case Computer:
                for (Warrior warrior : this.myTeam) {
                    if (!warrior.isDead()) return false;
                }
                break;
        }
        return true;
    }

    //TODO Each Hero Can Attack Multiple Targets
    public void giveAttack(Warrior attackingHero, Warrior[] selectedTargets) {
        attackingHero.attack(selectedTargets, null, null, toArray(this.enemyTeam), toArray(this.myTeam));
    }

    public Warrior[] toArray(Collection<Warrior> collection) {
        return (Warrior[]) collection.toArray();
    }


    public void showCurrentHeroStats() {
        String toReturn = "";
        for (Warrior hero : myTeam) {
            toReturn += hero.toString();
        }
        printOutput(toReturn.substring(0, toReturn.length() - 2));
    }

    public void changeCurrentExperience(int num) throws NotEnoughXPException {
        if (this.currentExperience + num < 0) {
            throw new NotEnoughXPException("You don't have Enough XP points to apply this upgrade\n" +
                    "your current XP : " + currentExperience + " \nrequired XP : " + -num + "\nYou need " +
                    (-num - currentExperience) + " additional XP points.");
        } else {
            this.currentExperience += num;
        }
    }

    public void changeCurrentWealth(int i) {
        if (i + currentWealth < 0)
            throw new NotEnoughMoneyException("You don't have Enough Money to apply this upgrade\n" +
                    "your current Wealth : " + currentWealth + "$\nrequired Money : " +
                    i + "$\nYou need " +
                    (i - currentWealth) + "$ additional Money.");
        else {
            this.currentExperience += i;
        }
    }


    public int getCurrentExperience() {
        return currentExperience;
    }

    public int getCurrentWealth() {
        return currentWealth;
    }

    public int getImmprtalityPotions() {
        return immprtalityPotions;
    }





}






/*
    public Player(ArrayList<Hero> heros) {
        this.currentWealth = Rules.INITIAL_MONEY ;
        this.currentExperience = Rules.INITIAL_XP;
        this.immprtalityPotions = 3;
        this.heros = heros;


        for (:heros) {

        }
        checkForImmortatlitryRequest();
    }
    */




    /*
        @Completed
        public Ability findAbilityByNameAndOwner(String name, Hero owner) {             //COOL!!!!!!!!!!!!!!
            Hero currentHero = findHeroByName(owner.name);


            for (String keyAbilityName : currentHero.abilities.keySet()) {
                if (keyAbilityName.equals(name)) {
                    return currentHero.abilities.get(keyAbilityName);
                }
            }
            return null;
        }

        @Completed
        public Ability findAbilityByName(String name) {
            Hero currentHero = null;

            for (int i = 0; i < heros.size(); i++) {
                for (String keyAbilityName : currentHero.abilities.keySet()) {
                    if (keyAbilityName.equals(name)) {
                        return currentHero.abilities.get(keyAbilityName);
                    }
                }
            }

            return null;
        }
    */

//
//    @Completed
//    public Hero findHeroByNameAndId(String name, int id) {
//        Hero currentHero;
//        for (int i = 0; i < heros.size(); i++) {
//            currentHero = heros.get(i);
//            if (currentHero.getName().equals(name) && currentHero.getId() == id) return currentHero;
//        }
//        return null;
//    }
//
//    @Completed
//    public Hero findHeroByName(String name) {
//        Hero currentHero;
//        for (int i = 0; i < heros.size(); i++) {
//            currentHero = heros.get(i);
//            try {
//                if (currentHero.equals(new Hero(name, "Fighter", 0)))
//                    return currentHero;
//            } catch (Exception e) {
//                try {
//                    if (currentHero.equals(new Hero(name, "Supporter", 0)))
//                        return currentHero;
//                } catch (Exception e2) {
//                    return null;
//                }
//                ;
//            }
//        }
//        return null;
//    }
//
//
//    @Completed
//    public Item getItembyNameAndOwner(String name, Hero usingHero) {
//        for (Item item : usingHero.inventory.getItems()) {
//            if (item.getInfo().getName().equals(name)) {
//                return item;
//            }
//        }
//        return null;
//    }
//
//    @Completed
//    public Item getItembyName(String name) {
//        Item toReturn = null;
//        for (int i = 0; i < heros.size(); i++) {
//            Hero usingHero = heros.get(i);
//            toReturn = getItembyNameAndOwner(name, usingHero);
//            if (toReturn != null) {
//                return toReturn;
//            }
//        }
//        return null;
//    }
//
//    @Completed
//    public void aTurnHasPassed() {
//        for (int i = 0; i < heros.size(); i++) {
//            heros.get(i).aTurnHasPassed();
//        }
//    }
//
//    public boolean isAnyImmortalityPotionLeft() {
//        return immprtalityPotions > 0;
//    }
//
//
//}
//
//
