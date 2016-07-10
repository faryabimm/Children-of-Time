package com.childrenOfTime.model.Equip.AbilComps;

/**
 * Created by SaeedHD on 07/07/2016.
 */
public class ExtraAbility {
}
//    private Boolean swirlingHeal;
//    private Integer[] swirlingHealPercents;
//
//    private Boolean swirlingAttack1;
//    private Integer[] swirlingAttackPercents;
//
//    private Boolean crticalAttack;
//    private Double[] crticalFactor;
//    private Integer[] crticalProbability;
//
//    private int[] EPCost;
//    private int[] MPCost;
//    private int[] XPCosts;
//
//    private String[] UpgradeDescriptions;
//
//
//    public ExtraAbility(String name, String SuccessMessage, String Description, String[] UpgradeDescriptons, Boolean swirlingHeal, Integer[] UpgradeNumbers, Integer[] swirlingHealPercents, Boolean swirlingAttack1, Integer[] swirlingAttackPercents, Boolean crticalAttack, Double[] crticalFactor, Integer[] criticalProbabiliy, int[] EPCost, int[] MPCost, int[] XPCosts, String[] requirements) {
//        super(name, Target.HimSelf, SuccessMessage, Description, Ability.DEFAUL_AbilityImage);
//        this.swirlingHeal = swirlingHeal;
//        this.swirlingHealPercents = swirlingHealPercents;
//        this.swirlingAttack1 = swirlingAttack1;
//        this.swirlingAttackPercents = swirlingAttackPercents;
//        this.crticalAttack = crticalAttack;
//        this.crticalFactor = crticalFactor;
//        this.EPCost = EPCost;
//        this.MPCost = MPCost;
//        this.XPCosts = XPCosts;
//        this.crticalProbability = criticalProbabiliy;
//        this.UpgradeDescriptions = UpgradeDescriptons;
//        for (int i = 0; i < swirlingAttackPercents.length; i++) {
//            Upgrade newUpgrade = new Upgrade(UpgradeNumbers[i], UpgradeDescriptons[i], 0, XPCosts[i], EPCost[i], MPCost[i], requirements[i]);
//            Upgrades.add(newUpgrade);
//            newUpgrade.currentUserCustomEffects.add(new PermanentPassiveFeatures(swirlingHeal, swirlingHealPercents[i], swirlingAttack1, swirlingAttackPercents[i], crticalAttack, crticalFactor[i], criticalProbabiliy[i]));
//        }
//    }
//}
