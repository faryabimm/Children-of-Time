package com.childrenOfTime.model;

import com.childrenOfTime.exceptions.NoImmortalityPotionLeftException;
import com.childrenOfTime.exceptions.NotEnoughMoneyException;
import com.childrenOfTime.exceptions.NotEnoughXPException;
import com.childrenOfTime.exceptions.TradeException;
import com.childrenOfTime.model.Equip.AbilComps.Ability;
import com.childrenOfTime.model.Equip.AlterPackage;
import com.childrenOfTime.model.Equip.ItemComps.Item;
import com.childrenOfTime.model.Interfaces.TurnBase;
import com.childrenOfTime.model.MultiPlayer.MultiPlayer;
import com.childrenOfTime.model.MultiPlayer.TransferPack;
import com.childrenOfTime.model.Warriors.Warrior;
import com.sun.istack.internal.NotNull;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import static com.childrenOfTime.view.IOHandler.printOutput;

/**
 * Created by mohammadmahdi on 5/8/16.
 */
public class Player implements TurnBase, Serializable {

    PlayerType playerType;
    String name;
    //TODO When You wanna add a Hero for a player You have to check if the player has another hero with the same name or not,assigning id for a hero by method setID is your duty
    private int currentWealth;
    private int currentExperience;
    private int immprtalityPotions = 3;
    private transient ArrayList<Item> itemsBougt;
    private ArrayList<Warrior> myTeam;




    public Player(@NotNull ArrayList<Warrior> myTeam, String name, PlayerType playerType) {
        if (name == null) name = "COM";
        this.name = name;
        this.myTeam = myTeam;
        this.immprtalityPotions = Rules.INITIAL_IMMORTALITY_POTION;
        this.currentWealth = Rules.INITIAL_MONEY;
        this.currentExperience = Rules.INITIAL_XP;
        this.playerType = playerType;
        checkForImmortatlitryRequest();
    }

    @Override
    public String toString() {
        return "Object{" +
                "name='" + name + '\'' +
                '}';
    }


    public void getImpermanentHalfEP() {
        //  AP , H , MH ,  HRF  , MP , MMP , MPRF  , EP ;
        Double[] a = {null, null, null, null, null, null, null, 0.5};
        for (Warrior warrior : this.myTeam) {
            warrior.addToImPermanentTurnBasedEffectsList(new AlterPackage(null, a), 1);
        }
    }

    private void useImmortalityPotion() throws NoImmortalityPotionLeftException {
        if (immprtalityPotions - 1 < 0) {
            throw new NoImmortalityPotionLeftException(name + " : No Immortality Potion Left");
        } else {
            immprtalityPotions--;
        }
        if (MultiPlayer.Instacne != null) {
            TransferPack TP = new TransferPack(getStats());
            MultiPlayer.Instacne.addToSendObjects(TP);
        }
    }

    public int[] getStats() {
        int[] stats = {this.currentExperience, this.currentWealth, this.immprtalityPotions};
        return stats;
    }


    private void checkForImmortatlitryRequest() {
        if (myTeam == null) return;

        Thread immortalityRequest = new Thread(() -> {
            while (true) {

                for (Warrior myHero : myTeam) {
                    try {
                        Thread.sleep(0, 100000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if (myHero.doesAskForImmortalityPotion()) {
                        //TODO Set Try catch
                        useImmortalityPotion();
                        myHero.PlayerAllowsUsingImmortality = true;
                    }
                }
            }
        });

        immortalityRequest.setPriority(Thread.NORM_PRIORITY + 2);
        immortalityRequest.setDaemon(true);
        immortalityRequest.setName("Immortality Request");
        immortalityRequest.start();
    }


    public int getNumbersBought(Item item) {

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
        targetHero.upgradeAbility(ability, id, toArray(this.myTeam));
    }

    public void buy(Item item, Warrior hero) throws TradeException {

        int itemsPrice = item.getCurrentPriceToBuy(getNumbersBought(item));
        changeCurrentWealth(-itemsPrice);
        try {
            hero.IWannaBuyItemForYou(item, toArray(this.myTeam));
        } catch (RuntimeException e) {
            changeCurrentWealth(itemsPrice);
            throw new RuntimeException(e.getMessage());
        }
        //TODO Jaye In moshakhas shavad
        printOutput(item.getName() + " bought Successfully\n" +
                "your Current Wealth is: $" + getCurrentWealth());


    }


    public void castAbility(Warrior castingHero, Ability castedAbility, ArrayList<Warrior> allEnemies, Warrior... selectedTargets) {

        castingHero.castAbility(castedAbility, selectedTargets, toArray(allEnemies), toArray(this.myTeam));

    }

    public void useItem(Warrior usingHero, Item usedItem, ArrayList<Warrior> allEnemies, Warrior... selectedTargets) {
        usingHero.useItem(usedItem, selectedTargets, toArray(allEnemies), toArray(this.myTeam));
    }

    public void sell(Item item, Warrior target) throws TradeException {


        target.IWannaSellThisItem(item, toArray(myTeam));
        int itemCurrenPriceToSell = item.getCurrentPriceToSell();
        changeCurrentWealth(itemCurrenPriceToSell);
        printOutput("Item " + item.getName() + " was successfully sold for $" +
                itemCurrenPriceToSell + " and was removed form (" +
                target.toString() + ") Hero.");
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
    public void giveAttack(Warrior attackingHero, Warrior[] selectedTargets, ArrayList<Warrior> allEnemies) {
            attackingHero.attack(selectedTargets, null, null, toArray(allEnemies), toArray(this.myTeam));

    }

    public static Warrior[] toArray(Collection<Warrior> collection) {
        for (Warrior warrior : collection) {
//            collection;
        }
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
        if (MultiPlayer.Instacne != null) {
            TransferPack TP = new TransferPack(getStats());
            MultiPlayer.Instacne.addToSendObjects(TP);
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
        if (MultiPlayer.Instacne != null) {
            TransferPack TP = new TransferPack(getStats());
            MultiPlayer.Instacne.addToSendObjects(TP);
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


    public ArrayList<Warrior> getMyTeam() {
        return myTeam;
    }


    public void changeImmortalityPotion(int i) {
        if (immprtalityPotions + i < 0) {
            throw new NoImmortalityPotionLeftException(name + " : No Immortality Potion Left");
        } else {
            this.immprtalityPotions += i;
        }
        if (MultiPlayer.Instacne != null) {
            TransferPack TP = new TransferPack(getStats());
            MultiPlayer.Instacne.addToSendObjects(TP);
        }
    }


    @Override
    public void aTurnHasPassed() {
        for (Warrior warrior :
                myTeam) {
            warrior.aTurnHasPassed();
        }

    }


    public void setCurrentWealth(int currentWealth) {
        this.currentWealth = currentWealth;

    }

    public void setCurrentExperience(int currentExperience) {
        this.currentExperience = currentExperience;
    }

    public void setImmprtalityPotions(int immprtalityPotions) {
        this.immprtalityPotions = immprtalityPotions;
    }

    public String getName() {
        return name;
    }
}






/*
    public Object(ArrayList<Hero> heros) {
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
//
//
//    public boolean isAnyImmortalityPotionLeft() {
//        return immprtalityPotions > 0;
//    }
//
//
//}
//
//
