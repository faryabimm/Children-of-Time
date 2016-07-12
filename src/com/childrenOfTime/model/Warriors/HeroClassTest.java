package com.childrenOfTime.model.Warriors;

import com.childrenOfTime.model.AbilityMaker;
import com.childrenOfTime.model.Equip.AbilComps.Ability;
import com.childrenOfTime.model.Equip.AbilComps.Upgrade;
import com.childrenOfTime.model.Equip.AlterPackage;
import com.childrenOfTime.model.Equip.Effect;
import com.childrenOfTime.model.Equip.EffectType;
import com.childrenOfTime.model.Equip.Target;
import junit.framework.TestCase;

import java.util.ArrayList;

/**
 * Created by SaeedHD on 07/11/2016.
 */
public class HeroClassTest extends TestCase {
    HeroClass HC1;
    Warrior W1;
    Warrior W2;
    Warrior W3;
    Effect E2;
    Ability AB1;
    private Ability AB2;

    public void setUp() throws Exception {
        super.setUp();

    }

    public void testMakeAWarrior() throws Exception {
        int[] burnEP = {1, 5};
        Integer[] Delta = {5, 5, 5, 5, 5, 5, 5, 1};
        Double[] Factor = {2d, 2d, 2d, 2d, 2d, 2d, 2d, 1d};

        AlterPackage A1 = new AlterPackage(Delta, Factor);
        EffectType ET1 = new EffectType(true, false, true, false, false, false);
        EffectType ET2 = new EffectType(true, true, false, true, true, false);
        Effect E1 = new Effect("Ef1", ET1, A1, Target.AllEnemies, 100, 2, 1);
        E2 = new Effect("Ef2", ET2, A1, Target.AllTeammates, 100, 0, 0);
        ArrayList<Effect> effects = new ArrayList<>(1);
        effects.add(E1);
        Upgrade UP1 = new Upgrade(1, 0, 5, 2, 5, false, true, effects, true, "");
        Upgrade UP2 = new Upgrade(1, 0, 5, 2, 5, false, true, effects, true, "");
        AbilityMaker abMaker = new AbilityMaker();
        abMaker.newCustomAbility("Ab1", Target.AllEnemies, null, null, 5);
        abMaker.addCustomUpgrade(UP1);
        abMaker.addCustomEffect(1, E1);
        AB1 = abMaker.returnAbility();
        abMaker.newCustomAbility("Ab2", Target.AllTeammates, null, null, 5);
        abMaker.addCustomUpgrade(UP2);
        abMaker.addCustomEffect(1, E2);
        AB2 = abMaker.returnAbility();
        ArrayList<Ability> abilities = new ArrayList<>(1);
        abilities.add(AB2);

        HC1 = new HeroClass(true, true, 200, 3000, 80, true, burnEP, 3, "Muta", "Ep burn", true, true, true, false, true, "Man Kardam ", "mordam ", "saeeds", 0, 300, 10, 200, 10, 6, 10, "Koskholan", "Balihan", abilities);
        W1 = new Warrior("saeed", HC1, null, null);
        W2 = new Warrior("saeed", HC1, null, null);
        W3 = new Warrior("saeed", HC1, null, null);
        W2.setId(2);
        W3.setId(3);
    }

    //All Health Changes Have been Completely tested !
    public void testNormalAttack() throws Exception {
        testMakeAWarrior();

        Warrior[] enemy = {W2};
        Warrior[] myTeam = {W2};
        W1.attack(enemy, null, null, enemy, myTeam);
        System.out.println(W2.getAttackPower() + "  " + W2.getCurrentHealth());
        assertEquals(W1.getCurrentEP(), W1.getInfo().getInitialEP() - Warrior.DEFAULT_Attack_EP_COST);
        W1.attack(enemy, null, null, enemy, myTeam);
        System.out.println(W2.getAttackPower());
        W1.attack(enemy, null, null, enemy, myTeam);
        System.out.println(W2.getAttackPower());
    }

    //Cast AutoCast Recastable  Upgrade    imPermanent
    public void testAbilityCast() throws Exception {
        testMakeAWarrior();
        Warrior[] enemy = {W2, W3};
        Warrior[] myTeam = {W1};

        //Allenemies , auto-Repeat 1 turn - impermanet 2 turns !
        W1.upgradeAbility(AB1, 1, enemy, null);
        W2.aTurnHasPassed();
        assertEquals(W2.getCurrentHealth(), (2 * 300 + 5) * 2 + 5);
        W2.aTurnHasPassed();
        assertEquals(W2.getCurrentHealth(), 603);
        W2.aTurnHasPassed();
        W2.aTurnHasPassed();
        assertEquals(W3.getCurrentHealth(), 2 * 300 + 5);
        //W1.castAbility(AB1, null, enemy, null);

        testMakeAWarrior();

    }

    //Recastabilty : tested !!!     passiveEffects :
    public void testAbilityMore() throws Exception {
        testMakeAWarrior();
        Warrior[] myTeam = {W2, W3};
        Warrior[] enemy = {W1};
        W2.upgradeAbility(AB2, 1, enemy, myTeam);
        assertEquals(W2.getCurrentHealth(), 300);
        W2.castAbility(AB2, null, enemy, myTeam);
        W2.castAbility(AB2, null, enemy, myTeam);
//        assertEquals(W3.containsPassiveEffect(E2), false);
//        assertEquals(W2.containsPassiveEffect(E2), true);
//
        System.out.println("W2 Health : " + W3.getCurrentHealth());
        W2.attack(enemy, null, null, enemy, myTeam);
        System.out.println(W2.getAttackPower());
//        W2.attack(enemy , null , null , enemy , myTeam);
//        System.out.println("W2 Health : " + W3.getCurrentHealth());


        // assertEquals(W3.getCurrentHealth(), 300);
//        W2.castAbility(AB2, null, enemy, myTeam);
//        assertEquals(W3.getCurrentHealth(), 300);
//        W2.castAbility(AB2, null, enemy, myTeam);
//        assertEquals(W3.getCurrentHealth(), 300);

    }

    public void testGetMutationMessage() throws Exception {

    }

    public void testGetEpBurningMessage() throws Exception {

    }
}