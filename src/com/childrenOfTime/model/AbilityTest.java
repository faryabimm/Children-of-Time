package com.childrenOfTime.model;

import com.childrenOfTime.exceptions.AbilityNotAquiredException;
import com.childrenOfTime.model.Equip.AbilComps.Ability;
import com.childrenOfTime.model.Equip.Target;
import com.childrenOfTime.model.Warriors.Foe;
import com.childrenOfTime.model.Warriors.Hero;
import com.childrenOfTime.model.Warriors.StrengthOfFoes;
import junit.framework.TestCase;
import org.junit.Test;

/**
 * Created by SaeedHD on 07/06/2016.
 */
public class AbilityTest extends TestCase {
    Ability ability;
    Foe thug0 = null;
    Foe angel0 = null;
    Foe tack0 = null;
    Hero bolti0 = null;
    Hero chrome0 = null;
    Hero eley0 = null;
    Hero eley1 = null;
    Hero eley2 = null;

    public void setUp() throws Exception {
        super.setUp();
        thug0 = new Foe("Thug", StrengthOfFoes.Weak, 0);
        angel0 = new Foe("Angel", StrengthOfFoes.Weak, 0);
        tack0 = new Foe("Tank", StrengthOfFoes.Weak, 0);
        bolti0 = new Hero("Bolti", "Supporter", 0);
        chrome0 = new Hero("Chrome", "Fighter", 0);
        eley0 = new Hero("Eley", "Fighter", 0);
        eley1 = new Hero("Eley", "Fighter", 1);
        eley2 = new Hero("Eley", "Fighter", 1);

    }

    @Test
    public void testCreateAbility() {
        EndlessCollectionInformations overpowered = EndlessCollectionInformations.Overpowered;
        AbilityMaker.newCustomAbility(overpowered.name, Target.SeveralEnemies);
        AbilityMaker.addCustomUpgrade(1, overpowered.coolDownTime, overpowered.xp1, overpowered.masrafEP, overpowered.masrafMP, overpowered.description, "sad");
        AbilityMaker.addCustomUpgrade(2, overpowered.coolDownTime, overpowered.xp2, overpowered.masrafEP, overpowered.masrafMP, overpowered.description, "asd");
        AbilityMaker.addCustomUpgrade(3, overpowered.coolDownTime, overpowered.xp3, overpowered.masrafEP, overpowered.masrafMP, overpowered.description, "sad");
        AbilityMaker.addCustomDurableEffect(1, 1.2d, 1, 1d, 1, 0, 0, 0, 0, 0, 0);
        AbilityMaker.addCustomDurableEffect(2, 1.4d, 3, 1d, 1, 0, 0, 0, 0, 0, 0);
        this.ability = AbilityMaker.returnAbility();
        assertNotNull(ability);
    }

    public void testAqcuireAbility() {
        testCreateAbility();

        ability.upgrade(eley0, 1);
    }

    public void testCastAbility() {
        testCreateAbility();
        try {
            ability.cast(eley0, thug0, thug0);
        } catch (Exception e) {
            assertEquals(e.getClass(), AbilityNotAquiredException.class);
        }
        testAqcuireAbility();
        ability.cast(eley0, thug0, angel0);
        assertEquals(angel0.currentHealth, (int) (angel0.maxHealth - 1.2 * eley0.attackPower));
        assertEquals(thug0.currentHealth, (int) (thug0.maxHealth - 1.2 * eley0.attackPower));

    }

    public void testUpgade2() {
        testAqcuireAbility();
        ability.upgrade(eley0, 2);

    }

    public void testCastOnUp2() throws Exception {
        setUp();
        testUpgade2();
        ability.cast(eley0, thug0, angel0);
        assertTrue(angel0.isDead);
        assertEquals(thug0.currentHealth, (int) (thug0.maxHealth - 1.4 * eley0.attackPower));

    }

    public void testUpgade3() {
        testAqcuireAbility();
        ability.upgrade(eley0, 3);

    }

    public void testCastUntilPoverty() throws Exception {
        setUp();
        testUpgade2();
        ability.cast(eley0, thug0, angel0);
        ability.aTurnHasPassed();
        ability.aTurnHasPassed();
        ability.aTurnHasPassed();
        ability.aTurnHasPassed();
        ability.aTurnHasPassed();
        ability.aTurnHasPassed();
    }



    public void tearDown() throws Exception {
    }
}