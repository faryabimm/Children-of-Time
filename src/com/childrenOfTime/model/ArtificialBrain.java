package com.childrenOfTime.model;

import com.childrenOfTime.model.Equip.AbilComps.Ability;
import com.childrenOfTime.model.Equip.AbilComps.Upgrade;
import com.childrenOfTime.model.Equip.Target;
import com.childrenOfTime.model.Warriors.ActionType;
import com.childrenOfTime.model.Warriors.Warrior;

import java.io.Serializable;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Random;

/**
 * Created by SaeedHD on 07/10/2016.
 */
public class ArtificialBrain implements Serializable {
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

    int tempEP;

    public LinkedList<Act> playATurn() {


        LinkedList<Act> actsToReturn = new LinkedList<>();

        for (Warrior hero : this.team) {
            if (hero.isDead()) continue;
            tempEP = hero.getCurrentEP();
            boolean CanAttack = hero.getInfo().getCanAttack();
            boolean CanHaveFinalBossFeaturs = hero.getInfo().getCanHaveFBFeatures();
            boolean CanBurnEP = hero.getInfo().getCanBurnEP();
            boolean CanChangeEP = hero.getInfo().getCanChangeEP();
            boolean CanCastAb = hero.abilities.size() != 0;
            int attackTmes = CanHaveFinalBossFeaturs ? 2 : 1;

            if (!CanChangeEP) {
                for (int i = 0; i < attackTmes; i++) {

                    actsToReturn.addLast(attack(hero));

                }
                actsToReturn.addAll(castAbility(hero, true, false));
            }

            if (CanChangeEP && CanAttack && CanCastAb) {
                actsToReturn.addAll(castAbility(hero, false, true));

                while (tempEP > 0) {
                    try {

                        actsToReturn.addLast(attack(hero));
                        tempEP -= Warrior.DEFAULT_Attack_EP_COST;
                    } catch (Exception e) {
                        e.printStackTrace();
                        break;
                    }
                }
            }
            if (CanChangeEP && !CanCastAb) {

                while (tempEP > 0) {
                    try {
                        actsToReturn.addLast(attack(hero));
                        tempEP -= Warrior.DEFAULT_Attack_EP_COST;

                    } catch (Exception e) {
                        e.printStackTrace();
                        break;
                    }
                }
            }
            if (CanChangeEP && !CanAttack && CanCastAb) {
                actsToReturn.addAll(castAbility(hero, false, false));

            }
            if (CanBurnEP) {
                Warrior[] targets = new Warrior[attackTmes];
                targets = findTarget(true, false, attackTmes);
//            hero.burnEP(targets);
                actsToReturn.addLast(new Act(ActionType.BurnEP, hero, targets, null, null));
            }
        }


        return actsToReturn;
    }


    public void doTheAct(Act act) {

        switch (act.getActionType()) {
            case Attack:
                act.getPerformer().attack(act.getSelectedTargets(), null, null, this.enemyTeam, this.team);
                break;
            case AbilityCast:
                for (Ability ab : act.getPerformer().abilities) {
                    if (ab.hashCode() == act.getHashCodeOfAbility()) {
                        act.getPerformer().castAbility(ab, act.getSelectedTargets(), this.enemyTeam, this.team);
                        break;
                    }
                }
                break;
            case BurnEP:
                act.getPerformer().burnEP(act.getSelectedTargets());
                break;
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

        HashSet<Warrior> toReturn = new HashSet<>();

        while (toReturn.size() < count) {
            int index = this.random.nextInt(allTargets.length);

            toReturn.add(allTargets[index]);
        }
        return Player.toArray(toReturn);

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
        HashSet<Warrior> targets = new HashSet<>();

        boolean isTargetGood = true;

        for (Warrior e : this.enemyTeam) {
                if (e.isDead()) continue;
                boolean isHard = false;
                switch (Rules.DIFFICUALTY) {
                    case Hard:
                        isHard = true;
                    case NightMare:
                        isTargetGood = (((Arrays.binarySearch(byDamageIndex, e, (a, b) -> a.getDamageEfficiencyIntelligenceOutOfTen() - b.getDamageEfficiencyIntelligenceOutOfTen()) + 1) * Arrays.binarySearch(byHeatlh, e, (a, b) -> a.getCurrentHealth() - b.getCurrentHealth()) + 1) ^ 3) / Arrays.binarySearch(byAP, e, (a, b) -> a.getAttackPower() - b.getAttackPower()) < minLong;
                        if (isHard) isTargetGood &= this.random.nextBoolean();
                        break;
                    case Easy:
                        isTargetGood = Arrays.binarySearch(byHeatlh, e, (a, b) -> a.getCurrentHealth() - b.getCurrentHealth()) < minLong && this.random.nextBoolean();
                        break;
                    case Medium:
                        isTargetGood = ((Arrays.binarySearch(byDamageIndex, e, (a, b) -> a.getDamageEfficiencyIntelligenceOutOfTen() - b.getDamageEfficiencyIntelligenceOutOfTen()) + 1) * ((Arrays.binarySearch(byHeatlh, e, (a, b) -> a.getCurrentHealth() - b.getCurrentHealth()) + 1) ^ 3) < minLong && this.random.nextBoolean());
                        break;
                }
            isTargetGood = teamMate ? !isTargetGood : isTargetGood;
            if (isTargetGood) targets.add(e);
            if (targets.size() >= count) break;
        }
        System.out.println(targets.size());
        return Player.toArray(targets);
    }

    private Warrior[] findTarget(boolean randomly, boolean teamMate, int count) {
        return randomly ? findRandomTarget(teamMate, count) : findIntelligentTarget(teamMate, count);

    }

    private LinkedList<Act> castAbility(Warrior myWarrior, boolean CastJustThoseWithoutEPCost, boolean CastJustPowerfuls) {

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

        LinkedList<Act> abilityAct = new LinkedList<>();
        try {
            for (Ability toCastAbility : toCast) {
                Target targetType = toCastAbility.getTargetType();
                Warrior[] warriors = findTarget(chooseTargetRandomly, targetType.isTeammate(), targetType.getNumberOftargetsNeededToChoose());
//              myWarrior.castAbility(toCastAbility, warriors , this.enemyTeam, team);
                abilityAct.addLast(new Act(ActionType.AbilityCast, myWarrior, warriors, toCastAbility.hashCode(), null));
                this.tempEP -= toCastAbility.getCurrentLevel().getMasrafEP();
            }
        } catch (Exception e) {
            e.printStackTrace();
            //TODO Remove This
        }
        return abilityAct;
    }


    private Act attack(Warrior warrior) {
        Warrior[] targets = findTarget(false, false, Rules.Quantitiy_Of_Targets_For_Manual_Multiple_Target_Choosing);
//        warrior.attack(targets, null, null, this.enemyTeam, this.team);
        return new Act(ActionType.Attack, warrior, targets, null, null);
    }

    private void acquireAbilities() {
        if (Rules.DIFFICUALTY == DIFFICUALTY.Easy) return;
        for (Warrior upgrader : this.team) {
            for (Ability ability : upgrader.abilities) {
                ability.acquire(upgrader, this.team);
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
                return random.nextInt(max - min + 1) / 3 + min;
            case Hard:
                return random.nextInt(max - min + 1) + min;
            case NightMare:
                return random.nextInt(max - min + 1) / 3 * -1 + max;
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
