package com.childrenOfTime.model;

import com.childrenOfTime.exceptions.NotEnoughEnergyPointsException;
import junit.framework.TestCase;

/**
 * Created by SaeedHD on 07/05/2016.
 */
public class HeroTest extends TestCase {
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

    public void tearDown() throws Exception {

    }

    public void testAttackManual() throws Exception {
        bolti0.attack(chrome0, null, 2);
        assertEquals(bolti0.getCurrentEnergyPoints(), 3);
        assertEquals(chrome0.currentHealth, 120);
        bolti0.attack(chrome0, null, 2);
        try {
            bolti0.attack(chrome0, null, 2);
        } catch (Exception e) {
            assertSame(e.getClass(), NotEnoughEnergyPointsException.class);
        }
        assertEquals(bolti0.getCurrentEnergyPoints(), 1);
        eley0.attack(chrome0, null, 2);
        assertEquals(chrome0.isDead, false);
        eley0.attack(chrome0, null, 2);
        eley0.attack(chrome0, null, 2);
        chrome0.attack(eley0, null, 2);
        eley1.attack(eley0, null, 2);
        eley1.attack(eley0, null, 2);
        eley2.attack(eley0, null, 2);
        assertTrue(eley0.isDead);
        eley2.attack(eley0, null, 2); //TODO chera payam nemide ke nagash !
        eley2.attack(eley0, null, 2);

    }

    public void testAttackManuaHeroToFoe() throws Exception {
        bolti0.attack(thug0, null, 2);
        bolti0.attack(thug0, null, 2);
        chrome0.attack(thug0, null, 2);
        assertTrue(thug0.isDead);
        assertTrue(thug0.currentHealth == 0);
        chrome0.attack(thug0, null, 2);
    }


}