package com.childrenOfTime.model;

/**
 * Created by SaeedHD on 05/11/2016.
 */
public enum FighterHero {
    Eley("Overpowered attack", "Swirling attack", "Eley:\n" +
            "Class: Fighter\n" +
            "Ability 3: Overpowered attack\n" +
            "Attacks an enemy with N times power for 2 energy points and\n" +
            "50 magic points\n" +
            "Upgrade 1: N=1.2 for 2 xp points, needs Fight training upgrade\n" +
            "1\n" +
            "Upgrade 2: N=1.4 for 4 xp points, needs Fight training upgrade\n" +
            "2\n" +
            "Upgrade 3: N=1.6 for 6 xp points, needs Fight training upgrade\n" +
            "3\n" +
            "Success message: “Eley just did an overpowered attack on “ +\n" +
            "(target) + “ with “ + (damage done) + “ damage”\n" +
            "Ability 4: Swirling attack\n" +
            "While attacking, non-targeted enemies also take P percent of\n" +
            "its damage\n" +
            "Upgrade 1: P=10 for 2 xp points, needs Work out upgrade 1\n" +
            "Upgrade 2: P=20 for 3 xp points\n" +
            "Upgrade 3: P=30 for 4 xp points"),
    Chrome("Sacrifice", "Critical strike", "Chrome:\n" +
            "Class: Fighter\n" +
            "Ability 3: Sacrifice\n" +
            " Damages all the enemies with 3H power at the cost of\n" +
            "H of his own health, needs 3 energy points, 60 magic points and\n" +
            "has a 1 turn cooldown\n" +
            " Upgrade 1: H=40 for 2 xp points, needs Work out\n" +
            "upgrade 1\n" +
            "  Upgrade 2: H=50 for 3 xp points, needs Work out\n" +
            "upgrade 2\n" +
            " Upgrade 3: H=60 for 4 xp points, needs Work out\n" +
            "upgrade 3\n" +
            " Success message: “Chrome just sacrificed himself to\n" +
            "damage all his enemies with “ + (damage done) + “ power“\n" +
            "Ability 4: Critical strike\n" +
            "Has a permanent P percent chance of doing an attack\n" +
            "with double power (does not affect other abilities)\n" +
            "Upgrade 1: P=20 for 2 xp points, needs Fight training\n" +
            "upgrade 1\n" +
            "Upgrade 2: P=30 for 3 xp points\n" +
            "Upgrade 3: P=40 for 4 xp points");
    String heroDescription;

    String ability3,ability4;

    FighterHero(String ability1, String ability2, String heroDescription) {
        this.ability3 = ability1;
        this.ability4 = ability2;
        this.heroDescription = heroDescription;
    }
}


