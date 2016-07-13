package com.childrenOfTime.model.Warriors;

import com.childrenOfTime.exceptions.ItemCannotBeSold;
import com.childrenOfTime.exceptions.NotEnoughEnergyPointsException;
import com.childrenOfTime.exceptions.NotEnoughMagicPointsException;
import com.childrenOfTime.exceptions.TradeException;
import com.childrenOfTime.model.Equip.AbilComps.Ability;
import com.childrenOfTime.model.Equip.AlterPackage;
import com.childrenOfTime.model.Equip.Effect;
import com.childrenOfTime.model.Equip.EffectPerformer;
import com.childrenOfTime.model.Equip.Inventory;
import com.childrenOfTime.model.Equip.ItemComps.Item;
import com.childrenOfTime.model.Equip.ItemComps.ItemType;
import com.childrenOfTime.model.Interfaces.TurnBase;
import com.childrenOfTime.model.Rules;

import javax.swing.*;
import java.io.Serializable;
import java.util.*;

import static com.childrenOfTime.view.IOHandler.printOutput;

/**
 * Created by mohammadmahdi on 5/8/16.
 */
public class Warrior implements Serializable, TurnBase {
    public transient static int DEFAULT_Attack_EP_COST = 2;


    public transient boolean PlayerAllowsUsingImmortality = false;



    private String name;

    public String getName() {
        return name;
    }

    private int id = 0;

    public ImageIcon getImage() {
        return image;
    }

    private ImageIcon image;

    private int currentAttackPower;
    private int currentHealth;
    private int currentMagic;
    private int currentEP;
    private transient boolean asksForImmortalityPotion = false;
    private boolean isDead = false;
    private Inventory inventory;
    private HeroClass info;
    private transient ArrayList<Ability> specificHeroAbilities;

    //private ExAbiltyInfo exAbiltyInfo;
    //private ExAbiltyInfo exAbiltyInfo;
    //private Boolean CanHaveHeroAbilities;
    //private Bool CanAttackMoreThanOneTarget;

    private transient Map<AlterPackage, Integer> imPermanentTurnBasedAPs = new HashMap<>();
    private transient Map<AlterPackage, Integer> autoRepeatAPList = new HashMap<>();
    private transient Set<Effect> passiveEffects = new HashSet<>(3);

    public List<Ability> abilities = new ArrayList<>();

    public void setId(int id) {
        this.id = id;
    }

    public Warrior(String name, HeroClass heroClass, ArrayList<Ability> specificHeroAbilities, ImageIcon imageIcon) {
        if (specificHeroAbilities == null) specificHeroAbilities = new ArrayList<>();
        this.info = new HeroClass(heroClass);
        this.name = name;
        this.image = imageIcon;
        this.image = imageIcon;
        this.specificHeroAbilities = specificHeroAbilities;


        for (Ability cAbility : info.classAbilities) {
            abilities.add(cAbility);
        }
        for (Ability hAbility : specificHeroAbilities) {
            abilities.add(hAbility);
        }
        inventory = new Inventory(info.getInventorySize());
        this.currentHealth = info.maxHealth;

        if (info.CanHaveFBFeatures) {
            this.currentAttackPower = this.currentHealth > info.healthBound ? info.attackPowerInHighHealth : info.attackPowerInLowHealth;
        } else this.currentAttackPower = this.info.attackPowerInHighHealth;
        this.currentAttackPower = this.currentHealth > info.healthBound ? info.attackPowerInHighHealth : info.attackPowerInLowHealth;
        this.currentEP = info.initialEP;
        this.currentMagic = info.maxMagic;
    }


    public void receiveAlterPack(AlterPackage alterPack) {
        if (isDead()) return;
        if (alterPack == null) return;
        try {

            Integer[] DELTA = alterPack.DELTA;
            Double[] FACTORS = alterPack.FACTORS;


            this.changeMaxHealth((int) (DELTA[2] + this.getMaxHealth() * (FACTORS[2] - 1)));
            int deltaMMP = (int) (DELTA[5] + getMaxMagic() * (FACTORS[5] - 1));
            this.changeAttackPower((int) (DELTA[0] + this.getAttackPower() * (FACTORS[0] - 1)));
            this.changeHealth((int) (DELTA[1] + this.getCurrentHealth() * (FACTORS[1] - 1)), null);
            int deltaHRF = (int) (DELTA[3] + getHealthRefillRate() * (FACTORS[3] - 1));
            int deltaMP = (int) (DELTA[4] + getCurrentMagic() * (FACTORS[4] - 1));
            int deltaMPRF = (int) (DELTA[6] + getMagicRefillRate() * (FACTORS[6] - 1));
            int deltaEP = (int) (DELTA[7] + getCurrentEP() * (FACTORS[7] - 1));

            changeHealthRefillRate(deltaHRF);
            changeCurrentMagic(deltaMP);
            changeMagicRefillRate(deltaMPRF);
            changeMaxMagic(deltaMMP);
            changeEP(deltaEP);

        } catch (NotEnoughMagicPointsException e) {
            if (alterPack.asCost) {
                throw new NotEnoughMagicPointsException(e.getMessage());
            } else currentMagic = 0;

        } catch (NotEnoughEnergyPointsException e) {
            if (alterPack.asCost) {
                throw new NotEnoughEnergyPointsException(e.getMessage());
            } else currentMagic = 0;

        }

    }

    public void addPassiveEffect(Effect effect) {
        passiveEffects.add(effect);
    }

    public boolean containsPassiveEffect(Effect effect) {
        return passiveEffects.contains(effect);
    }

    public void addToImPermanentTurnBasedEffectsList(AlterPackage aPackage, Integer duration) {
        Integer newDuration = duration;

        if (imPermanentTurnBasedAPs.containsKey(aPackage)) {
            newDuration += imPermanentTurnBasedAPs.get(aPackage);
        }
        imPermanentTurnBasedAPs.put(aPackage, newDuration);

    }


    public void addToAutoRepeatEffList(AlterPackage effect, Integer duration) {
        autoRepeatAPList.put(effect, duration);

    }

    public void decreasDuration(Map<Effect, Integer> list, Integer duration) {
        int newDuration;
        for (Effect ef : list.keySet()) {
            newDuration = list.get(ef) - duration;
            list.put(ef, newDuration);
        }

    }


    public Map<AlterPackage, Integer> getImPermanentTurnBasedAPs() {
        return imPermanentTurnBasedAPs;
    }


    //TODO check More This One
    public void useItem(Item item, Warrior[] selectedTargets, Warrior[] allEnemies, Warrior[] allTeamMates) {
        if (isDead()) return;
        item.cast(this, selectedTargets, allEnemies, allTeamMates);

    }

    public void attack(Warrior[] targets, Integer realAttack, Integer EPCost, Warrior[] allEnemies, Warrior[] allTeamMates) throws NotEnoughEnergyPointsException {
        if (isDead()) return;
        if (EPCost == null) EPCost = DEFAULT_Attack_EP_COST;

        EffectPerformer.performEffects(true, this.passiveEffects, this, targets, allEnemies, allTeamMates);

        changeEP(-EPCost);
        if (realAttack == null) realAttack = this.getAttackPower();


        for (Warrior tar : targets) {
            if (tar == null) continue;
            int damage = tar.changeHealth(-realAttack, null);
            //TODO DOROST SHAVAD
            printOutput(toString() + " has successfully attacked " + /*Inja h*/ tar.toString() + " with " + getAttackPower() + " power " + "\nDamage Made : " + damage);
        }

        Set<Effect> toWearOff = new HashSet<>();
        for (Effect eff : this.passiveEffects) {
            if (eff.getEffectType().isIfPassiveInstantEffectJustForAttack())
                toWearOff.add(eff);
        }
        EffectPerformer.wearOffEffects(toWearOff, this, targets, allEnemies, allTeamMates);

    }

    public void IWannaBuyItemForYou(Item item, Warrior[] allTeamMates) {
        if (isDead()) return;
        ItemType itemType = item.getType();
        if (itemType.getCanBeInInventory()) {
            inventory.addToInventoryIfYouCan(item);
        }
        if (itemType.getAutoUseAfterBuoght()) {
            useItem(item, null, null, allTeamMates);
        }


    }


    @Override
    public void aTurnHasPassed() {
        if (isDead()) return;
        this.currentEP = info.initialEP;
        changeHealth(this.info.getHealthRefillRate(), 100);
        changeCurrentMagic(this.info.getMagicRefillRate());
        this.abilities.forEach(Ability::aTurnHasPassed);
        this.inventory.getItems().forEach(Item::aTurnHasPassed);
        for (AlterPackage aPackage : this.imPermanentTurnBasedAPs.keySet()) {
            int i = imPermanentTurnBasedAPs.get(aPackage) - 1;
            if (aPackage.woreOff) i = -1;
            if (i <= 0) {
                imPermanentTurnBasedAPs.remove(aPackage);
                if (i == 0) {
                    aPackage.wearOff(this);
                    printOutput("Impermanent Effect :  " + aPackage.name + "   wore off by : " + this.toString());
                }

            } else imPermanentTurnBasedAPs.put(aPackage, i);
        }
        for (AlterPackage aPackage : this.autoRepeatAPList.keySet()) {
            int i = autoRepeatAPList.get(aPackage) - 1;
            if (aPackage.woreOff) i = -1;
            if (i <= 0) {
                autoRepeatAPList.remove(aPackage);
            }
            if (i >= 0) {
                aPackage.perform(this);
                printOutput("Auto repeat Effect :  " + aPackage.name + "   performed on : " + this.toString());
            }
        }

    }


    public void IWannaSellThisItem(Item item, Warrior[] allTeamMates) {
        if (isDead()) return;
        ItemType itemType = item.getType();
        if (!inventory.contains(item)) throw new TradeException("Hero doesn't have this . ");
        if (!item.canBeSold()) throw new ItemCannotBeSold("This Item Is not permitted to sell ! ");
        inventory.removeFromInventoryIfYouCan(item);
        if (itemType.getWearOffAfterSold()) {
            item.removedFromInventory(this, null, allTeamMates);

        }

    }


    public void castAbility(Ability ability, Warrior[] selectedTargets, Warrior[] allEnemies, Warrior[] allTeamMates) {
        if (isDead()) return;
        if (!abilities.contains(ability)) return;
        ability.cast(this, selectedTargets, allEnemies, allTeamMates);
    }
/*
    public void castAbility(String name, Warrior[] selectedTargets) {
        Ability ability = findAbilityByName(name);
        if (ability != null) ability.cast(this, selectedTargets, allEnemies, allTeamMates);
    }

*/

    public void upgradeAbility(Ability ability, Integer i, Warrior[] allTeamMates) {
        if (isDead()) return;
        ability.upgrade(this, i, allTeamMates);

    }


    public void changeAttackPower(int num) {
        int newAP = this.currentAttackPower + num;
        if (newAP < 0) {
            if (!Rules.AttackPowerCanBeNegative) this.currentAttackPower = newAP;
            printOutput(toString() + "'s " + "AttackPower has Gone Under zero !! ");
        }
        this.currentAttackPower = newAP;
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

    public int changeHealth(int i, Integer damageEffitioncyFactor) {
        if (isDead) return 0;
        int initHealth = currentHealth;
        if (damageEffitioncyFactor == null) damageEffitioncyFactor = calculateDamageEffitioncy();
        if (i >= 0) damageEffitioncyFactor = 100;
        i = (int) (i * damageEffitioncyFactor / 100.0);
        if (!wasAlive() & willDye(i)) {

            throw new RuntimeException("He is dead !");

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
                    isDead = false;
                    PlayerAllowsUsingImmortality = false;
                    asksForImmortalityPotion = false;
                } else {
                    isDead = true;
                    printOutput(this + info.getDyingActionMessage());

                }

            } else {
                isDead = true;
                printOutput(this + info.getDyingActionMessage());

            }
        }

        if (info.CanHaveFBFeatures && !isDead()) {
            updateFinalBossHealthChanges();
        }

        return currentHealth - initHealth;
    }

    public void changeMaxHealth(int i) {
        int newMH = i + this.info.maxHealth;
        if (getMaxMagic() < 0) newMH = 0;
        this.info.maxHealth = newMH;

    }

    public void changeEP(int i) throws NotEnoughEnergyPointsException {
        if (!this.info.CanChangeEP) {
            return;
        }
        if (this.currentEP + i < 0) {
            throw new NotEnoughEnergyPointsException(toString() + " doesn't have Enough EP to perform this" +
                    " move\ncurrent EP : " + currentEP + "\nrequired EP : " + -i + "\nYou need " +
                    (-i - currentEP) + " additional EPs.");
        }
        this.currentEP += i;

        //TODO PakShavad
        printOutput(this + "current EP : " + currentEP);

    }


    //Utilities :
//    public Ability findAbilityByName(String name) {
//        try {
//            return abilities.get(name);
//        } catch (Exception e) {
//        }
//        return null;
//    }
/*
    private void chooseTargtsThenPerform(Effect eff, Warrior[] targets) {
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
            case SingleEnemy:
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
        boolean firstTime = true;
        if (this.currentHealth <= this.info.healthBound) {
            if (this.currentAttackPower >= info.attackPowerInLowHealth) return;
            if (firstTime) {
            this.currentAttackPower = this.info.attackPowerInLowHealth;
                printOutput(info.getMutationMessage());
                firstTime = false;
            }
        }

    }

    public void changeHealthWithInsuranceOfLiving(int quantity) {
        currentHealth = currentHealth + quantity > info.maxHealth ? info.maxHealth : currentHealth + quantity;
    }


    public void burnEP(Warrior[] targets) {
        if (!info.CanHaveFBFeatures) return;
        for (Warrior target : targets) {
            int amount = -new Random().nextInt(info.heroBurningEnergy[1] - info.heroBurningEnergy[0]) + info.heroBurningEnergy[0];
            target.getInfo().changeInitialEP(amount);
            target.changeEP(amount);
        }
    }

    public boolean wasAlive() {
        return currentHealth > 0;
    }

    public boolean willDye(int q) {
        return currentHealth + q <= 0;
    }

    public void useImmortalityPotion() {
        changeHealth(this.info.maxHealth, null);
    }

    public String toString() {
        return this.name + " " + this.getId() + " ," + "  (" + this.info.getClassName() + ") - ";
    }

    private transient Random random = new Random();
    public Integer calculateDamageEffitioncy() {
        int i = this.info.damageEfficiencyIntelligenceOutOfTen;
        int j = (int) (this.currentHealth + 1);
        int sout = (100 - ((i * 7 * 1400) / j * (random.nextInt(30 - i * 2) + 70 + 2 * i) / 1000));
        if (sout > 100) sout = 100;
        if (sout < 0) sout = 0;
        return sout;
    }

    public int getDamageEfficiencyIntelligenceOutOfTen() {
        return this.info.damageEfficiencyIntelligenceOutOfTen;
    }

    public int getMagicRefillRate() {
        return info.magicRefillRate;
    }

    public int getHealthRefillRate() {
        return info.healthRefillRate;
    }

    public int getMaxMagic() {
        return info.maxMagic;
    }

    public int getCurrentMagic() {
        return currentMagic;
    }

    public int getAttackPower() {
        return this.currentAttackPower;
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
        String toPrint = this.toString() + " has ";
        for (Item i : inventory.getItems()) {
            toPrint += i.getName() + " worth " + i.getInitialPrice() + " dollars(InitialPrice), ";
        }
        if (toPrint.equals(this.toString() + " has ")) {
            printOutput(toPrint + "no items yet!");
        } else {
            printOutput(toPrint);
        }
    }

    public void showCurrentAbilities() {
        String toPrint = "";
        String state = "";
        for (Ability entry : abilities) {
            if (!entry.isAcquired()) {
                state = "not acquired";
            } else state = "Upgrade " + entry.getCurrentLevel().getNumberOfUpgrade() + "";
            toPrint += "\t" + entry.getName() + " : " + state + "\n";
        }
        printOutput(toPrint);
    }


    public boolean doesAskForImmortalityPotion() {
        return asksForImmortalityPotion;
    }


    public Inventory getInventory() {
        return inventory;
    }

    public boolean isDead() {
        return this.currentHealth <= 0;
    }

    public HeroClass getInfo() {
        return info;
    }


    public void setCurrentAttackPower(int currentAttackPower) {
        this.currentAttackPower = currentAttackPower;
    }

    public void setCurrentHealth(int currentHealth) {
        this.currentHealth = currentHealth;
    }

    public void setCurrentMagic(int currentMagic) {
        this.currentMagic = currentMagic;
    }

    public void setCurrentEP(int currentEP) {
        this.currentEP = currentEP;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Warrior warrior = (Warrior) o;

        if (id != warrior.id) return false;
        return name.equals(warrior.name);

    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + id;
        return result;
    }

    public Set<Effect> getPassiveEffects() {
        return passiveEffects;
    }
}
