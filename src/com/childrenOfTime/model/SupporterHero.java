package com.childrenOfTime.model;

/**
 * Created by SaeedHD on 05/11/2016.
 */
public enum SupporterHero {
    Meryl("Elixir", "Caretaker", "Meryl:\n" +
            "Class: Supporter\n" +
            "Ability 3: Elixir\n" +
            "Refills H points of her own health or an ally’s, for 2 energy points and 60 magic points\n" +
            "Upgrade 1: H=100 for 2 xp points and takes 1 turn to cool down\n" +
            "Upgrade 2: H=150 for 3 xp points, takes 1 turn to cool down and needs Magic lessons upgrade 1\n" +
            "Upgrade 3: H=150 for 5 xp points, cools down instantly and needs Magic lessons upgrade 2\n" +
            "Success message: “Meryl just healed “ + (target) + “ with “ + (healing amount) + “ health points”\n" +
            "Ability 4: Caretaker\n" +
            "Gives 1 energy point to an ally for 30 magic points (this ep does not last until the end of the battle and is only\n" +
            "usable during the current turn)\n" +
            "Upgrade 1: takes 2 energy points and has a 1 turn cooldown for 2 xp points, needs Quick as a bunny upgrade 1\n" +
            "Upgrade 2: takes 2 energy points and cools down instantly for 3 xp points, needs Quick as a bunny upgrade 2\n" +
            "Upgrade 3 takes 1 energy point and cools down instantly for 5 xp points, needs Quick as a bunny upgrade 3\n" +
            "Success message: “Meryl just gave “ + (target) + “ 1 energy point”"),
    Bolti("Boost", "Mana beam", "Bolti:\n" +
            "Class: Supporter\n" +
            "Ability 3: Boost\n" +
            "Gives X bonus attack power to himself or an ally, which lasts till the end of the battle, for 2 energy points and 50\n" +
            "magic points (this bonus attack power can stack up)\n" +
            "Upgrade 1: A=20 for 2 xp points and takes 1 turn to cool down\n" +
            "Upgrade 2: A=30 for 3 xp points and takes 1 turn to cool down\n" +
            "Upgrade 3: A=30 for 5 xp points and cools down instantly\n" +
            "Success message: “Bolti just boosted “ + (target) + “ with “ + (A) + “ power”\n" +
            "Ability 4: Mana beam\n" +
            "Gives M magic points to himself or an ally for 1 energy point and 50 magic points\n" +
            "Upgrade 1: M=50 for 2 xp points and takes 1 turn to cool down, needs magic lessons upgrade 1\n" +
            "Upgrade 2: M=80 for 3 xp points and takes 1 turn to cool down, needs magic lessons upgrade 2\n" +
            "Upgrade 3: M=80 for 4 xp points and cools down instantly, needs magic lessons upgrade 3\n" +
            "Success message: “Bolti just helped “ + (target) + “ with “ + (M) + “ magic points”");

    String heroDescription;

    String ability3,ability4;

    SupporterHero(String ability1, String ability2, String heroDescription) {
        this.ability3 = ability1;
        this.ability4 = ability2;
        this.heroDescription = heroDescription;
    }
}
