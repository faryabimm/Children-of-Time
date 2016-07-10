package com.childrenOfTime.model;

import com.childrenOfTime.model.Equip.AbilComps.Ability;
import com.childrenOfTime.model.Equip.AbilComps.Upgrade;
import com.childrenOfTime.model.Warriors.Warrior;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Map;
import java.util.Random;

/**
 * Created by SaeedHD on 07/10/2016.
 */
public class ArtificialBrain {
    Player ComPlayer;
    Warrior[] team;
    Warrior[] enemyTeam;
    Player enemy;
    Battle battle;
    Random random = new Random();
    Map<Ability, Upgrade> abilities;

    public ArtificialBrain() {
        this.team = Player.toArray(this.ComPlayer.getMyTeam());
        this.enemyTeam = Player.toArray(this.enemy.getMyTeam());
    }

    public void playATurn() {

    }

    public void initialize() {
        acquireAbilities();

    }


    private Warrior[] sortEnemiesByHealth() {
        Warrior[] sortedByHealth = Arrays.copyOf(this.enemyTeam, this.enemyTeam.length);
        Arrays.sort(this.enemyTeam, (o1, o2) -> o1.getCurrentHealth() - o2.getCurrentHealth());
        return sortedByHealth;
    }

    private Warrior[] sortEnemiesByDamageNumber() {
        Warrior[] sortedByDamageNumber = Arrays.copyOf(this.enemyTeam, this.enemyTeam.length);
        Arrays.sort(this.enemyTeam, (o1, o2) -> o1.getDamageEfficiencyIntelligenceOutOfTen() - o2.getDamageEfficiencyIntelligenceOutOfTen());
        return sortedByDamageNumber;
    }

    private Warrior[] sortEnemiesByAttackPower() {
        Warrior[] sortedByAP = Arrays.copyOf(this.enemyTeam, this.enemyTeam.length);
        Arrays.sort(this.enemyTeam, (o1, o2) -> o1.getAttackPower() - o2.getAttackPower());
        return sortedByAP;
    }


    private Warrior[] findTarget(int count) {
        Warrior[] health = sortEnemiesByHealth();
        Warrior[] DamageIndex = null;
        Warrior[] AP = null;
        if (!(Rules.DIFFICUALTY == DIFFICUALTY.Easy)) {
            DamageIndex = sortEnemiesByDamageNumber();
            AP = sortEnemiesByAttackPower();
        }


        int hlen = health.length;

        long minLong = 10000000l;
        LinkedList<Warrior> targets = new LinkedList<>();

        while (targets.size() < (count < hlen ? count : hlen)) {
            for (Warrior e : this.enemyTeam) {
                if (e.isDead()) continue;
                boolean isHard = false;
                switch (Rules.DIFFICUALTY) {
                    case Hard:
                        isHard = true;
                    case NightMare:
                        if ((((Arrays.binarySearch(DamageIndex, e) + 1) * Arrays.binarySearch(health, e) + 1) ^ 3) / Arrays.binarySearch(AP, e) < minLong) {
                            if (isHard && random.nextBoolean()) continue;
                            targets.add(e);

                        }
                        break;
                    case Easy:
                        if (Arrays.binarySearch(health, e) < minLong && random.nextBoolean()) {
                            targets.add(e);
                        } else continue;
                        break;
                    case Medium:
                        if ((Arrays.binarySearch(DamageIndex, e) + 1) * ((Arrays.binarySearch(health, e) + 1) ^ 3) < minLong) {
                            if (random.nextBoolean()) continue;
                            targets.add(e);
                        }
                        break;
                }

            }
        }
        return (Warrior[]) targets.toArray();
    }


    private Warrior findTargetForAbility() {
        return null;
    }

    private void castAbility() {
        for (Warrior myWarrior : this.team) {
            switch (Rules.DIFFICUALTY) {
                case Easy:
                    Ability abil = myWarrior.abilities.get(random.nextInt(myWarrior.abilities.size()));
                    myWarrior.castAbility(abil, findTarget(1), team, enemyTeam);
                case Medium:
            }
        }
    }


    private void acquireAbilities() {
        if (Rules.DIFFICUALTY == DIFFICUALTY.Easy) return;
        for (Warrior upgrader : this.team) {
            for (Ability ability : upgrader.abilities) {
                ability.acquire(upgrader, this.enemyTeam, this.team);
                ability.forceUpgrade(upgrader, getUpgradeNumber(ability), this.enemyTeam, this.team);
            }
        }
    }

    public Integer getUpgradeNumber(Ability ability) {
        BST<Upgrade> upgrades = ability.getUpgrades();
        int min = upgrades.getMinElement().getNumberOfUpgrade();
        int max = upgrades.getMaxElement().getNumberOfUpgrade();

        switch (Rules.DIFFICUALTY) {
            case Medium:
                return random.nextInt(max - min) / 3 + min;
            case Hard:
                return random.nextInt(max - min) + min;
            case NightMare:
                return random.nextInt(max - min) / 3 * -1 + max;
        }
        return null;
    }
}
