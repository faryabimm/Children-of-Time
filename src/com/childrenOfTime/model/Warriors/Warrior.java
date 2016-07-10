package com.childrenOfTime.model.Warriors;

import com.childrenOfTime.exceptions.ItemCannotBeSold;
import com.childrenOfTime.exceptions.NotEnoughEnergyPointsException;
import com.childrenOfTime.exceptions.NotEnoughMagicPointsException;
import com.childrenOfTime.exceptions.TradeException;
import com.childrenOfTime.model.Equip.AbilComps.Ability;
import com.childrenOfTime.model.Equip.*;
import com.childrenOfTime.model.Equip.ItemComps.Item;
import com.childrenOfTime.model.Equip.ItemComps.ItemType;
import com.childrenOfTime.model.Rules;

import java.awt.*;
import java.util.*;

import static com.childrenOfTime.view.IOHandler.printOutput;

/**
 * Created by mohammadmahdi on 5/8/16.
 */
public class Warrior {
    public static int DEFAULT_Attack_EP_COST = 2;


    public boolean PlayerAllowsUsingImmortality = false;




    private String name;
    private int id = 0;
    private Image image;


    private int currentHealth;
    private int currentMagic;
    private int currentEP;
    private boolean asksForImmortalityPotion = false;
    private boolean isDead = false;
    private Inventory inventory;
    private HeroClass info;
    private ArrayList<Ability> specificHeroAbilities;

    private Warrior[] allTeamMates;
    private Warrior[] allEnemies;
    private String TeamName;
    //private ExAbiltyInfo exAbiltyInfo;
    //private ExAbiltyInfo exAbiltyInfo;
    //private Boolean CanHaveHeroAbilities;
    //private Bool CanAttackMoreThanOneTarget;

    private Map<Effect, Integer> imPermanentTurnBasedEffectsList = new HashMap<>();
    private Map<Effect, Integer> autoRepeatEffList = new HashMap<>();
    private Set<Effect> passiveEffects = new HashSet<>(3);
    private Set<Effect> imPermanentManualWeearOffEffs = new HashSet<>();

    public Map<String, Ability> abilities = new HashMap<>();

    public Warrior(String name, Image image, HeroClass info, ArrayList<Ability> specificHeroAbilities) {
        this.name = name;
        this.image = image;
        this.info = info;
        this.specificHeroAbilities = specificHeroAbilities;
    }

    public void receiveAlterPack(AlterPackage alterPack) {
        if (alterPack != null) return;
        Integer[] DELTA = alterPack.DELTA;
        Double[] FACTORS = alterPack.FACTORS;


        int newMH = DELTA[2] + this.getMaxHealth();
        this.changeAttackPower((int) (DELTA[0] + this.getAttackPower() * (FACTORS[0] - 1)));
        this.changeHealth((int) (DELTA[1] + this.getCurrentHealth() * (FACTORS[1] - 1)), null);
        this.changeMaxHealth((int) (DELTA[2] + this.getMaxHealth() * (FACTORS[2]) - 1));
        int deltaHRF = (int) (DELTA[3] + getHealthcRefillRate() * (FACTORS[3] - 1));
        int deltaMP = (int) (DELTA[4] + getCurrentMagic() * (FACTORS[4] - 1));
        int deltaMMP = (int) (DELTA[5] + getMaxMagic() * (FACTORS[5] - 1));
        int deltaMPRF = (int) (DELTA[6] + getMagicRefillRate() * (FACTORS[6] - 1));
        int deltaEP = (int) (DELTA[7] + getCurrentEP() * (FACTORS[7] - 1));

        changeHealthRefillRate(deltaHRF);
        changeCurrentMagic(deltaMP);
        changeMagicRefillRate(deltaMPRF);
        changeMaxMagic(deltaMMP);
        changeEP(deltaEP);

    }

    public void addPassiveEffect(Effect effect) {
        passiveEffects.add(effect);
    }

    public boolean containsPassiveEffect(Effect effect) {
        return passiveEffects.contains(effect);
    }

    public void addToImPermanentTurnBasedEffectsList(Effect effect, Integer duration) {
        Integer newDuration = duration;

        if (imPermanentTurnBasedEffectsList.containsKey(effect)) {
            newDuration += imPermanentTurnBasedEffectsList.get(effect);
        }
        imPermanentTurnBasedEffectsList.put(effect, newDuration);
    }

    public void addToImPermanentManualEffectsList(Effect effect) {
        imPermanentManualWeearOffEffs.add(effect);
    }

    public void addToAutoRepeatEffList(Effect effect, Integer duration) {
        autoRepeatEffList.put(effect, duration);
    }

    public void decreasDuration(Map<Effect, Integer> list, Integer duration) {
        int newDuration;
        for (Effect ef : list.keySet()) {
            newDuration = list.get(ef) - duration;
            list.put(ef, newDuration);
        }
    }


    private void removeFromImPermanentManualEffectsList(Effect effect) {
        try {
            imPermanentManualWeearOffEffs.remove(effect);
        } catch (Exception e) {
        }
    }

    public void removeFromPerformedListOfWarrior(Effect effect) {
        try {
            imPermanentTurnBasedEffectsList.remove(effect);
        } catch (Exception e) {
        }
    }

    public Map<Effect, Integer> getImPermanentTurnBasedEffectsList() {
        return imPermanentTurnBasedEffectsList;
    }


    //TODO check More This One
    public void useItem(Item item, Warrior[] selectedTargets, Warrior[] allEnemies, Warrior[] allTeamMates) {
        item.cast(this, selectedTargets, allEnemies, allTeamMates);
    }

    public void attack(Warrior[] targets, Integer realAttack, Integer EPCost, Warrior[] allEnemies, Warrior[] allTeamMates) throws NotEnoughEnergyPointsException {
        if (EPCost == null) EPCost = DEFAULT_Attack_EP_COST;
        changeEP(-EPCost);
        if (realAttack == null) realAttack = this.getAttackPower();

        for (Effect eff : passiveEffects) {
            Warrior[] targetsToPerformPassiveEffs = null;
            if (eff.getTargetType() == Target.theAttackedOne) targetsToPerformPassiveEffs = targets;
            EffectPerformer.performEffects(this.passiveEffects, this, targets, allEnemies, allTeamMates);
        }

        for (Warrior tar : targets) {
            tar.changeHealth(-realAttack, null);
            //TODO DOROST SHAVAD
            printOutput(getIdentity() + " has successfully attacked " + /*Inja h*/ getIdentity() + " with " + getAttackPower() + " power");
        }


    }

    public void IWannaBuyItemForYou(Item item, Warrior[] allEnemies, Warrior[] allTeamMates) {
        ItemType itemType = item.getType();
        if (itemType.getCanBeInInventory()) {
            inventory.addToInventoryIfYouCan(item);
        }
        if (itemType.getAutoUseAfterBuoght()) {
            useItem(item, null, allEnemies, allTeamMates);
        }
        if (itemType.getWearOffAfterSold()) {
            for (Effect effect : item.getEffects()) {
                addToImPermanentManualEffectsList(effect);
            }
        }


    }

    public void IWannaSellThisItem(Item item) {
        ItemType itemType = item.getType();
        if (!item.canBeSold()) throw new ItemCannotBeSold("This Item Is not permitted to sell ! ");
        if (!inventory.contains(item)) throw new TradeException("Hero doesn't have this . ");
        if (itemType.getWearOffAfterSold()) {
            for (Effect effect : item.getEffects()) {
                removeFromImPermanentManualEffectsList(effect);
            }
        }

    }

    public void castAbility(Ability ability, Warrior[] selectedTargets, Warrior[] allEnemies, Warrior[] allTeamMates) {
        if (!abilities.containsKey(ability.getName())) return;
        ability.cast(this, selectedTargets, allEnemies, allTeamMates);
    }
/*
    public void castAbility(String name, Warrior[] selectedTargets) {
        Ability ability = findAbilityByName(name);
        if (ability != null) ability.cast(this, selectedTargets, allEnemies, allTeamMates);
    }

*/

    public void upgradeAbility(Ability ability, Integer i, Warrior[] allEnemies, Warrior[] allTeamMates) {
        ability.upgrade(this, i, allEnemies, allTeamMates);
    }


    public void changeAttackPower(int num) {
        int newAP = info.attackPower + num;
        if (newAP < 0) {
            if (!Rules.AttackPowerCanBeNegative) this.info.attackPower = newAP;
            printOutput(getIdentity() + "'s " + "AttackPower has Gone Under zero !! ");
        }
        this.info.attackPower = newAP;
    }

    public void changeMaxMagic(int i) {
        if (!info.CanChangeMP) return;
        int newMMP = i + this.info.maxMagic;
        if (getMaxMagic() < 0) newMMP = 0;
        this.info.maxMagic = newMMP;
    }

    public void changeCurrentMagic(int i) {
        if (!info.CanChangeMP) return;
        int newMP = i + this.currentMagic;
        if (newMP < 0)
            throw new NotEnoughMagicPointsException("Your " + name + id + " hero doesn't have Enough MP to perform this" +
                    " move\ncurrent MP : " + currentMagic + "\nrequired MP : " + -i + "\nYou need " +
                    (-i - currentEP) + " additional MPs.");
        if (newMP > this.info.maxMagic) newMP = this.info.maxMagic;
        this.currentMagic = newMP;
    }

    public void changeMagicRefillRate(int i) {
        if (!(info.CanChangeMP && info.CanUseRefillFeature)) return;
        int newMRR = i + this.info.magicRefillRate;
        if (newMRR < 0) {
            if (!Rules.RefillRatesCanBeNegative) newMRR = 0;
            else printOutput(this.name + " " + getId() + "'s " + "RefillRate has Gone Under zero !! ");
        }
        this.info.magicRefillRate = newMRR;
    }

    public void changeHealthRefillRate(int i) {
        if (!(info.CanUseRefillFeature)) return;
        int newHRR = i + this.info.magicRefillRate;
        if (newHRR < 0) {
            if (!Rules.RefillRatesCanBeNegative) newHRR = 0;
        }
        this.info.healthRefillRate = newHRR;
    }

    public void changeHealth(int i, Integer damageEffitioncyFactor) {
        if (damageEffitioncyFactor == null) damageEffitioncyFactor = calculateDamageEffitioncy();
        i = (int) (i * damageEffitioncyFactor / 100.0);
        if (wasAlive() & !willDye(i)) {
            changeHealthWithInsuranceOfLiving(i);
            return;
        }

        if (wasAlive() & willDye(i)) {
            currentHealth = 0;
            if (info.CanUseImmortalityPotions) {
                asksForImmortalityPotion = true;
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (PlayerAllowsUsingImmortality) {
                    useImmortalityPotion();
                    PlayerAllowsUsingImmortality = false;
                    asksForImmortalityPotion = false;
                } else {
                    isDead = true;
                    printOutput(info.getDyingActionMessage());

                }

            }
        }

        if (info.CanHaveFBFeatures) {
            updateFinalBossHealthChanges();
        }
    }

    public void changeMaxHealth(int i) {
        int newMH = i + this.info.maxHealth;
        if (getMaxMagic() < 0) newMH = 0;
        this.info.maxMagic = newMH;
    }

    public void changeEP(int i) throws NotEnoughEnergyPointsException {
        if (!this.info.CanChangeEP) {
            return;
        }
        if (this.currentEP + i < 0) {
            throw new NotEnoughEnergyPointsException(getIdentity() + " doesn't have Enough EP to perform this" +
                    " move\ncurrent EP : " + currentEP + "\nrequired EP : " + -i + "\nYou need " +
                    (-i - currentEP) + " additional EPs.");
        }
        this.currentEP += i;
        printOutput(this + "current EP : " + currentEP);

    }


    //Utilities :
    public Ability findAbilityByName(String name) {
        try {
            return abilities.get(name);
        } catch (Exception e) {
        }
        return null;
    }
/*
    private void chooseTargtsThenPerform(Effects eff, Warrior[] targets) {
        Warrior[] newTargets;
        switch (eff.getTargetType()) {
            case HimSelf:
                newTargets = new Warrior[1];
                newTargets[0] = this;
                break;
            case AllEnemies:
                newTargets = allEnemies;
                break;
            case AllTeammates:
                newTargets = allTeamMates;
                break;
            case SingleTarget:
            case theAttackedOne:
            case SeveralEnemies:
            case SeveralTeamMates:
                newTargets = targets;
                break;
        }


    }
    */

    public String getId() {
        String toReturn = "";
        if (this.id > 0) toReturn += id;
        return toReturn;
    }

    public void updateFinalBossHealthChanges() {
        if (this.currentHealth <= this.info.healthBound) {
            this.info.attackPower = this.info.attackPowerInLowHealth;
        } else {
            this.info.attackPower = this.info.attackPowerInHighHealth;
        }
    }

    public void changeHealthWithInsuranceOfLiving(int quantity) {
        currentHealth = currentHealth + quantity > info.maxHealth ? info.maxHealth : currentHealth + quantity;
    }

    public boolean wasAlive() {
        return currentHealth > 0;
    }

    public boolean willDye(int q) {
        return currentHealth + q < 0;
    }

    public void useImmortalityPotion() {
        this.currentHealth = this.info.maxHealth;
    }

    public String getIdentity() {
        return this.name + " " + this.getId() + " , ";
    }

    public Integer calculateDamageEffitioncy() {
        int i = 10 - this.info.damageEffitioncyIntelligenceOutOfTen;
        int j = (int) (this.currentHealth * (20 - i));
        return 100 - (i * 2500) / j;
    }


    public int getMagicRefillRate() {
        return info.magicRefillRate;
    }

    public int getHealthcRefillRate() {
        return info.healthRefillRate;
    }

    public int getMaxMagic() {
        return info.maxMagic;
    }

    public int getCurrentMagic() {
        return currentMagic;
    }

    public int getAttackPower() {
        return info.attackPower;
    }

    public int getMaxHealth() {
        return info.maxHealth;
    }

    public int getCurrentHealth() {
        return currentHealth;
    }

    public int getCurrentEP() {
        return currentEP;
    }


    public void showCurrentItems() {
        String toPrint = this.getIdentity() + " has ";
        for (Item i : inventory.getItems()) {
            toPrint += i.getName() + " worth " + i.getInitialPrice() + " dollars(InitialPrice), ";
        }
        if (toPrint.equals(this.getIdentity() + " has ")) {
            printOutput(toPrint + "no items yet!");
        } else {
            printOutput(toPrint);
        }
    }

    public void showCurrentAbilities() {
        String toPrint = "";
        String state = "";
        for (Map.Entry<String, Ability> entry : abilities.entrySet()) {
            if (!entry.getValue().isAcquired()) {
                state = "not acquired";
            } else state = "Upgrade " + entry.getValue().getCurrentLevel().getNumberOfUpgrade() + "";
            toPrint += "\t" + entry.getKey() + " : " + state + "\n";
        }
        printOutput(toPrint);
    }


    public boolean doesAskForImmortalityPotion() {
        return asksForImmortalityPotion;
    }

    public String toString() {
        return this.getIdentity() + " (" + this.info.getClassName() + ") - ";
    }

    public Inventory getInventory() {
        return inventory;
    }

    public boolean isDead() {
        return isDead;
    }
}
