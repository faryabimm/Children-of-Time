package com.childrenOfTime.model;

/**
 * Created by SaeedHD on 05/13/2016.
 */
public enum InformationOfItems {
    Toughen("Toughen", 4, 2, false, 1),
    Guide("Guide", 4, 2, false, 1),
    Defy("Defy", 4, 2, false, 1),
    Sword("Sword", 25, 0, true, 1),
    Energy("Energy Boots", 20, 0, true, 1),
    Armor("Armor", 25, 0, true, 1),
    MagicStick("Magic stick", 28, 0, true, 3),
    Magic("Magic potion", 15, 0, true, 3),
    Health("Health potion", 15, 0, true, 3);

    private String name;
    private int initialPrice;
    private int priceIncreament;
    private boolean hasVolume;
    private int allowedUsages;


    InformationOfItems(String name, int initialPrice, int priceIncreament, boolean hasVolume, int allowedUsages) {
        this.name = name;
        this.initialPrice = initialPrice;
        this.priceIncreament = priceIncreament;
        this.hasVolume = hasVolume;
        this.allowedUsages = allowedUsages;
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
}
