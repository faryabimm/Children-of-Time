package com.childrenOfTime.model;

/**
 * Created by mohammadmahdi on 5/8/16.
 */
public enum TypesOfHero {
    Fighter(200, 10, 2, 120, 120, 5, 6, "Fight training", "Work out", "Fighter class:\n" +
            "Maximum health: 200\n" +
            "Health refill rate: 10 percent of maximum health\n" +
            "Maximum magic: 120\n" +
            "Magic refill rate: 5 percent of maximum magic\n" +
            "Attack power: 120\n" +
            "Energy points: 6\n" +
            "Inventory size: 2\n" +
            "Ability 1: Fight training\n" +
            " Permanently increases attack power\n" +
            "Upgrade1: +30 attack power for 2 xp points\n" +
            "Upgrade2: +30 attack power for 3 xp points\n" +
            "Upgrade3: +30 attack power for 4 xp points\n" +
            "Ability 2: Work out\n" +
            " Permanently increases maximum health\n" +
            "Upgrade 1: +50 maximum health for 2 xp points\n" +
            "Upgrade 2: +50 maximum health for 3 xp points\n" +
            "Upgrade 3: +50 maximum health for 4 xp points"),
    Supporter(200, 10, 2, 120, 120, 5, 6, "Quick as a bunny", "Magic lessons", "Supporter class:\n" +
            "Maximum health: 220\n" +
            "Health refill rate: 5 percent of maximum health\n" +
            "Maximum magic: 200\n" +
            "Magic refill rate: 10 percent of maximum magic\n" +
            "Attack power: 80\n" +
            "Energy points: 5\n" +
            "Inventory size: 3\n" +
            "Ability 1: Quick as a bunny\n" +
            "Permanently increases energy points\n" +
            "Upgrade1: +1 energy point for 2 xp points\n" +
            "Upgrade2: +1 energy point for 3 xp points\n" +
            "Upgrade3: +1 energy point for 4 xp points\n" +
            "Ability 2: Magic lessons\n" +
            "Permanently increases maximum magic\n" +
            "Upgrade 1: +50 maximum magic for 2 xp points\n" +
            "Upgrade 2: +50 maximum magic for 3 xp points\n" +
            "Upgrade 3: +50 maximum magic for 4 xp points");
    int maxHealth;
    int healthRefillRate;
    int inventorySize;
    int attackPower;
    int maxMagic;
    int magicRefillRate;
    int initialEP;
    String ability2, ability1;
    String classDescription;

    TypesOfHero(int maxHealth, int healthRefillRate, int inventorySize, int attackPower, int maxMagic, int magicRefillRate, int initialEP, String ability1, String ability2, String classDescription) {
        this.maxHealth = maxHealth;
        this.healthRefillRate = healthRefillRate;
        this.inventorySize = inventorySize;
        this.attackPower = attackPower;
        this.maxMagic = maxMagic;
        this.magicRefillRate = magicRefillRate;
        this.initialEP = initialEP;
        this.ability1 = ability1;
        this.ability2 = ability2;
        this.classDescription = classDescription;
    }

}
