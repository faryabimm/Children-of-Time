package com.childrenOfTime.model;

import com.childrenOfTime.model.Equip.AbilComps.Ability;
import com.childrenOfTime.model.Equip.AbilComps.Upgrade;
import com.childrenOfTime.model.Equip.Target;
import com.childrenOfTime.model.Warriors.Warrior;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Random;

/**
 * Created by SaeedHD on 07/10/2016.
 */
public class ArtificialBrain {
    Player ComPlayer;
    Warrior[] team;
    Warrior[] enemyTeam;
    Player enemy;
    Random random = new Random();


    public ArtificialBrain(Player comPlayer, Player enemy) {
        ComPlayer = comPlayer;
        this.enemy = enemy;
        this.team = Player.toArray(this.ComPlayer.getMyTeam());
        this.enemyTeam = Player.toArray(this.enemy.getMyTeam());
        initialize();
    }

    public void playATurn() {
        for (Warrior hero : this.team) {
            if (hero.isDead()) continue;
            boolean CanAttack = hero.getInfo().getCanAttack();
            boolean CanHaveFinalBossFeaturs = hero.getInfo().getCanHaveFBFeatures();
            boolean CanChangeEP = hero.getInfo().getCanChangeEP();
            boolean CanCastAb = hero.abilities.size() != 0;
            if (!CanChangeEP) {
                int attackTmes = CanHaveFinalBossFeaturs ? 2 : 1;
                for (int i = 0; i < attackTmes; i++) {
                    attack(hero);
                }
                castAbility(hero, true, false);
            }

            if (CanChangeEP && CanAttack && CanCastAb) {
                castAbility(hero, false, true);

                while (true) {
                    try {
                        attack(hero);
                    } catch (Exception e) {
                        e.printStackTrace();
                        break;
                    }
                }
            }
            if (CanChangeEP && !CanCastAb) {

                while (true) {
                    try {
                        attack(hero);
                    } catch (Exception e) {
                        e.printStackTrace();
                        break;
                    }
                }
            }
            if (CanChangeEP && !CanAttack && CanCastAb) {
                castAbility(hero, false, false);
            }
        }
    }

    public void initialize() {
        acquireAbilities();
    }
    private Warrior[] sortEnemiesByHealth(Warrior[] targets) {
        Warrior[] sortedByHealth = Arrays.copyOf(targets, targets.length);
        Arrays.sort(sortedByHealth, (o1, o2) -> o1.getCurrentHealth() - o2.getCurrentHealth());
        return sortedByHealth;
    }

    private Warrior[] sortEnemiesByDamageNumber(Warrior[] targets) {
        Warrior[] sortedByDamageNumber = Arrays.copyOf(targets, targets.length);
        Arrays.sort(sortedByDamageNumber, (o1, o2) -> o1.getDamageEfficiencyIntelligenceOutOfTen() - o2.getDamageEfficiencyIntelligenceOutOfTen());
        return sortedByDamageNumber;
    }

    private Warrior[] sortEnemiesByAttackPower(Warrior[] targets) {
        Warrior[] sortedByAP = Arrays.copyOf(targets, targets.length);
        Arrays.sort(sortedByAP, (o1, o2) -> o1.getAttackPower() - o2.getAttackPower());
        return sortedByAP;
    }

    private Warrior[] findRandomTarget(boolean teamMate, int count) {
        if (count == 0) return null;
        Warrior[] allTargets = teamMate ? this.team : this.enemyTeam;
        int len = allTargets.length;

        if (count >= getAliveWarriorsCount(allTargets)) return allTargets;

        LinkedList<Warrior> toReturn = new LinkedList<>();

        while (toReturn.size() < count) {
            int index = this.random.nextInt(allTargets.length);
            toReturn.add(allTargets[index]);
        }
        return (Warrior[]) toReturn.toArray();

    }

    private Warrior[] findIntelligentTarget(boolean teamMate, int count) {
        if (count == 0) return null;

        Warrior[] allTargets = teamMate ? this.team : this.enemyTeam;
        if (count >= getAliveWarriorsCount(allTargets)) return allTargets;


        Warrior[] byHeatlh = sortEnemiesByHealth(allTargets);
        Warrior[] byDamageIndex = null;
        Warrior[] byAP = null;

        if (!(Rules.DIFFICUALTY == DIFFICUALTY.Easy)) {
            byDamageIndex = sortEnemiesByDamageNumber(allTargets);
            byAP = sortEnemiesByAttackPower(allTargets);
        }



        long minLong = 10000000l;
        LinkedList<Warrior> targets = new LinkedList<>();

        boolean isTargetGood = true;

        for (Warrior e : this.enemyTeam) {
                if (e.isDead()) continue;
                boolean isHard = false;
                switch (Rules.DIFFICUALTY) {
                    case Hard:
                        isHard = true;
                    case NightMare:
                        isTargetGood = (((Arrays.binarySearch(byDamageIndex, e) + 1) * Arrays.binarySearch(byHeatlh, e) + 1) ^ 3) / Arrays.binarySearch(byAP, e) < minLong;
                        if (isHard) isTargetGood &= this.random.nextBoolean();
                        break;
                    case Easy:
                        isTargetGood = Arrays.binarySearch(byHeatlh, e) < minLong && this.random.nextBoolean();
                        break;
                    case Medium:
                        isTargetGood = ((Arrays.binarySearch(byDamageIndex, e) + 1) * ((Arrays.binarySearch(byHeatlh, e) + 1) ^ 3) < minLong && this.random.nextBoolean());
                        break;
                }
            isTargetGood = teamMate ? !isTargetGood : isTargetGood;
            if (isTargetGood) targets.add(e);
            if (targets.size() >= count) break;
        }

        return (Warrior[]) targets.toArray();
    }

    private Warrior[] findTarget(boolean randomly, boolean teamMate, int count) {
        return randomly ? findRandomTarget(teamMate, count) : findIntelligentTarget(teamMate, count);

    }

    private void castAbility(Warrior myWarrior, boolean CastJustThoseWithoutEPCost, boolean CastJustPowerfuls) {

        LinkedList<Ability> toCast = new LinkedList<>();
        boolean chooseTargetRandomly = true;
            switch (Rules.DIFFICUALTY) {
                case NightMare:
                    chooseTargetRandomly = false;
                case Easy:
                case Medium:
                case Hard:
                    break;
            }

        for (Ability ab : myWarrior.abilities) {
            if (CastJustThoseWithoutEPCost && ab.getCurrentLevel().getMasrafEP() == 0) {
                toCast.add(ab);
                continue;
            }
            if (CastJustPowerfuls) if (ab.getPowerOutOften() > 6) {
                toCast.add(ab);
                continue;
            } else toCast.add(ab);

        }


        try {
            for (Ability toCastAbility : toCast) {
                Target targetType = toCastAbility.getTargetType();
                myWarrior.castAbility(toCastAbility, findTarget(chooseTargetRandomly, targetType.isTeammate(), targetType.getNumberOftargetsNeededToChoose()), this.enemyTeam, team);
            }
        } catch (Exception e) {
            e.printStackTrace();
            //TODO Remove This
        }
        }


    private void attack(Warrior warrior) {
        warrior.attack(findTarget(false, false, Rules.Quantitiy_Of_Targets_For_Manual_Multiple_Target_Choosing), null, null, this.enemyTeam, this.team);
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

    private Integer getUpgradeNumber(Ability ability) {
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

    public int getAliveWarriorsCount(Warrior[] warriors) {
        int c = 0;
        for (Warrior w : warriors) {
            if (!w.isDead()) c++;
        }
        return c;
    }
}
