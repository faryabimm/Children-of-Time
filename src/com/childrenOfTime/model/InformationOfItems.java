package com.childrenOfTime.model;

/**
 * Created by SaeedHD on 05/13/2016.
 */
public enum InformationOfItems {
    Toughen("Toughen", 4, 2, false, 1, "Items which alter a hero’s traits (don’t take up inventory’s space):\n" +
            "“Toughen”: +20 maximum health\n" +
            "These items initially cost 4 dollars but get 2 dollars more expensive each time you purchase one. "),
    Guide("Guide", 4, 2, false, 1, "Items which alter a hero’s traits (don’t take up inventory’s space):\n" +
            "“Guide”: +20 maximum magic\n" +
            "These items initially cost 4 dollars but get 2 dollars more expensive each time you purchase one."),
    Defy("Defy", 4, 2, false, 1, "Items which alter a hero’s traits (don’t take up inventory’s space):\n" +
            "“Defy”: +8 attack power\n" +
            "These items initially cost 4 dollars but get 2 dollars more expensive each time you purchase one."),
    Sword("Sword", 25, 0, true, 1, "Items which have a permanent effect on a hero:\n" +
            "“Sword”: +80 attack power, costs 25 dollars\n"),
    Energy("Energy Boots", 20, 0, true, 1, "Items which have a permanent effect on a hero:\n" +
            "“Energy Boots”: +1 energy point, costs 20 dollars\n"),
    Armor("Armor", 25, 0, true, 1, "Items which have a permanent effect on a hero:\n" +
            "“Armor”: +200 maximum health, costs 25 dollars\n"),
    MagicStick("Magic stick", 28, 0, true, 3, "Items which have a permanent effect on a hero:\n" +
            "“Magic stick”: +150 maximum magic, costs 28 dollars"),
    Magic("Magic potion", 15, 0, true, 3, "Items which you can use during the battle up to 3 times (they free the inventory after 3 uses)\n" +
            "“Magic potion”: +50 magic points for the user or one of his/her allies, costs 15 dollars\n" +
            "Usage message: (user) + “ just offered ” + (target) + “ 50 magic points”"),
    Health("Health potion", 15, 0, true, 3, "Items which you can use during the battle up to 3 times (they free the inventory after 3 uses)\n" +
            "“Health potion”: +100 health points for the user or one of his/her allies, costs 15 dollars\n" +
            "Usage message: (user) + “ just healed ” + (target) + “ with 100 health points”\n");

    private String name;
    private int initialPrice;
    private int priceIncreament;
    private boolean hasVolume;
    private int allowedUsages;

    private String description;


    InformationOfItems(String name, int initialPrice, int priceIncreament, boolean hasVolume, int allowedUsages, String description) {
        this.name = name;
        this.initialPrice = initialPrice;
        this.priceIncreament = priceIncreament;
        this.hasVolume = hasVolume;
        this.allowedUsages = allowedUsages;
        this.description = description;
    }


    public String getName() {
        return name;
    }

    public int getInitialPrice() {
        return initialPrice;
    }

    public int getPriceIncreament() {
        return priceIncreament;
    }

    public boolean isHasVolume() {
        return hasVolume;
    }

    public int getAllowedUsages() {
        return allowedUsages;
    }

    public String getDescription() {
        return description;
    }
}
