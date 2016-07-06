package com.childrenOfTime.model;

import java.util.ArrayList;

/**
 * Created by SaeedHD on 07/06/2016.
 */
public class AbilityMaker {
    private static InformationofAbility ability;

    public static void newAbility(String name, Target targetType) {
        ability = new InformationofAbility(name, new BST<Upgrade>(), targetType);
    }

    public static void addUpgrade(int numberOfUpgrade, Integer COOLDOWN_TIME, int XPCost, int masrafEP, int masrafMP, String description, Boolean upgradeCondition) {
        Upgrade newUpgrade = new Upgrade(numberOfUpgrade, COOLDOWN_TIME, XPCost, masrafEP, masrafMP, upgradeCondition);
        ability.getUpgrades().add(newUpgrade);
        newUpgrade.setDescription(description);
        newUpgrade.setEffects(new ArrayList<Effects>(1));
    }

    public static Upgrade getUpgradeByNumber(int i) {
        return ability.getUpgradeByNumber(i);
    }

    public static void addDurableEffect(Integer upgradeNumber, Double factorAttackPower_WithAttack_Amount, int factorAttackPower_WithAttack_Duration, Double factorAttackPower_WithoutAttack_Amount,
                                        int factorAttackPower_WithoutAttack_Duration, int giveMagicPoints_Amount, int giveMagicPoints_Duration, int giveHealth_amount, int giveHealth_Duration, int giveEP_amount, int giveEP_Duration) {
        Upgrade upgrade = getUpgradeByNumber(upgradeNumber);
        if (upgrade == null) return;
        Effects effect = new DurableEffects(factorAttackPower_WithAttack_Amount, factorAttackPower_WithAttack_Duration, factorAttackPower_WithoutAttack_Amount,
                factorAttackPower_WithoutAttack_Duration, giveMagicPoints_Amount, giveMagicPoints_Duration, giveHealth_amount, giveHealth_Duration, giveEP_amount, giveEP_Duration);
        upgrade.addEffect(effect);
    }

    public static void addPermanentEffect(Integer upgradeNumber, Integer giveAttackPowerPermanently, Double factorAttackPowerPermanently, Integer giveMaxMagicPermanently) {
        Upgrade upgrade = getUpgradeByNumber(upgradeNumber);
        if (upgrade == null) return;
        Effects effect = new PermanentEffects(giveAttackPowerPermanently, factorAttackPowerPermanently, giveMaxMagicPermanently);
        upgrade.addEffect(effect);

    }

    public static InformationofAbility returnAbility() {
        InformationofAbility ab = ability;
        ability = null;
        return ab;
    }
}
