package com.childrenOfTime.model;

import com.childrenOfTime.Completed;
import com.childrenOfTime.model.Equip.AlterPackage;
import com.childrenOfTime.model.Equip.Effects;
import com.childrenOfTime.model.Interfaces.HasImpactHealth;
import com.childrenOfTime.model.Warriors.Hero;
import com.sun.istack.internal.Nullable;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by mohammadmahdi on 5/8/16.
 */
public abstract class Warrior implements HasImpactHealth {
    public static final Double DEFAULT_RECEIVING_DAMAGE_FACTOR = 1d;


    protected int currentHealth;
    protected int maxHealth;
    protected int id;
    protected int attackPower;
    protected String name;
    protected boolean isDying = false;
    protected boolean isDead = false;

    private Set<Effects> passiveEffects = new HashSet<>(3);

    @Completed
    public Warrior(String name, int id) {
        this.id = id;
        this.name = name;
    }

    public Warrior(String name, @Nullable Integer id, int attackPower, int maxHealth, int currentHealth) {
        this.name = name;
        this.attackPower = attackPower;
        if (id == null) id = 0;
        this.id = id;
        this.maxHealth = maxHealth;
        this.currentHealth = currentHealth;
    }

    public abstract void changeHealth(int quantity, Double reduceFactor);

    public void changeHealthWithInsuranceOfLiving(int quantity) {
        currentHealth = currentHealth + quantity > maxHealth ? maxHealth : currentHealth + quantity;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public int getAttackPower() {
        return attackPower;
    }

    public void setAttackPower(int attackPower) {
        this.attackPower = attackPower;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean wasAlive() {
        return currentHealth > 0;
    }

    public boolean willDye(int q) {
        return currentHealth + q < 0;
    }

    public void changeAttackPower(int num) {
        this.attackPower += num;
    }

    public abstract String showCurrentTraits();

    public int getCurrentHealth() {
        return currentHealth;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }

    //TODO BAD DESIGN

    public void receiveAlterPack(AlterPackage alterPack) {
        if (alterPack != null) return;
        Integer[] DELTA = alterPack.DELTA;
        Double[] FACTORS = alterPack.FACTORS;


        int newMH = DELTA[2] + this.getMaxHealth();
        this.changeAttackPower((int) (DELTA[0] + this.getAttackPower() * (FACTORS[0] - 1)));
        this.changeHealth((int) (DELTA[1] + this.getCurrentHealth() * (FACTORS[1] - 1)), null);
        this.setMaxHealth((int) (DELTA[2] + this.getMaxHealth() * FACTORS[2]));
        if (this instanceof Hero) {
            Hero hero2 = (Hero) this;
            int newHRF = (int) (DELTA[3] + hero2.getHealthRefillRate() * FACTORS[3]);
            int newMP = (int) (DELTA[4] + hero2.getCurrentMagic() * FACTORS[4]);
            int newMMP = (int) (DELTA[5] + hero2.getMaxMagic() * FACTORS[5]);
            int newMPRF = (int) (DELTA[6] + hero2.getMagicRefillRate() * FACTORS[6]);
            int newEP = (int) (DELTA[7] + hero2.getCurrentEnergyPoints() * FACTORS[7]);

            hero2.setHealthRefillRate(newHRF);
            hero2.setCurrentMagic(newMP);
            hero2.setMagicRefillRate(newMPRF);
            hero2.setMaxMagic(newMMP);
            hero2.setCurrentEnergyPoints(newEP);

        }


    }

    public void addPassiveEffect(Effects effect) {
        passiveEffects.add(effect);
    }

    public boolean containsPassiveEffect(Effects effect) {
        return passiveEffects.contains(effect);
    }

    private Map<Effects, Integer> imPermanentEffectsList = new HashMap<>();
    private Map<Effects, Integer> autoRepeatEffList = new HashMap<>();

    public void addToImPermanentEffectsList(Effects effect, Integer duration) {
        Integer newDuration = duration;

        if (imPermanentEffectsList.containsKey(effect)) {
            newDuration += imPermanentEffectsList.get(effect);
        }
        imPermanentEffectsList.put(effect, newDuration);
    }

    public void addToAutoRepeatEffList(Effects effect, Integer duration) {
        autoRepeatEffList.put(effect, duration);
    }

    public void decreasDuration(Map<Effects, Integer> list, Integer duration) {
        int newDuration;
        for (Effects ef : list.keySet()) {
            newDuration = list.get(ef) - duration;
            list.put(ef, newDuration);
        }
    }


    public void removeFromPerformedListOfWarrior(Effects effect) {
        try {
            imPermanentEffectsList.remove(effect);
        } catch (Exception e) {
        }
    }

    public Map<Effects, Integer> getImPermanentEffectsList() {
        return imPermanentEffectsList;
    }
/*
    private Integer[] savedDATA = new Integer[AlterPackage.DEFAULT_COUNT_OF_ALTERING_ATTRIBUTES];
    private void saveDATA() {
        savedDATA[0] = getAttackPower();
        savedDATA[1] =getCurrentHealth() ;
        savedDATA[2] =getMaxHealth() ;
        if(this instanceof Hero) {
            Hero savingH= (Hero) this;
            savedDATA[3] = savingH.getHealthRefillRate();
            savedDATA[4] = savingH.getCurrentMagic();
            savedDATA[5] = savingH.getMaxMagic();
            savedDATA[7] = savingH.getCurrentEnergyPoints();
        }
    }

    public void restore(){

    }
    */

}
