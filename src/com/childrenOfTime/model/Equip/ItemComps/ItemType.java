package com.childrenOfTime.model.Equip.ItemComps;

import java.io.Serializable;

/**
 * Created by SaeedHD on 07/08/2016.
 */
public class ItemType implements Serializable {
    private final Boolean Inflative;
    private final Boolean Reusable;
    private final Boolean autoUseAfterBuoght;
    private final Boolean canBeInInventory;
    private final Boolean shouldRemoveWhenLeftUsageIsZero;
    private final Boolean wearOffAfterSold;
    private final Boolean hasCoolDown;


    private final Integer coolDownTime;
    private final Integer initialPrice;
    private final Integer reusablityNumber;
    private final Integer priceInfaltionRate;


    //TODO make Paramteres nullable !
    public ItemType(Boolean inflative, Boolean reusable, Boolean autoUseAfterBuoght, Boolean canBeInInventory, Boolean shouldRemoveWhenLeftUsageIsZero, Boolean wearOffAfterSold, Boolean hasCoolDown, Integer coolDownTime, Integer initialPrice, Integer reusablityNumber, Integer priceInfaltionRate) {
        Inflative = inflative;
        Reusable = reusable;
        this.autoUseAfterBuoght = autoUseAfterBuoght;
        this.canBeInInventory = canBeInInventory;
        this.shouldRemoveWhenLeftUsageIsZero = shouldRemoveWhenLeftUsageIsZero;
        this.wearOffAfterSold = wearOffAfterSold;
        this.hasCoolDown = hasCoolDown;
        this.coolDownTime = coolDownTime;
        this.initialPrice = initialPrice;
        this.reusablityNumber = reusablityNumber;
        this.priceInfaltionRate = priceInfaltionRate;
    }


    public Boolean getInflative() {
        return Inflative;
    }

    public Boolean getReusable() {
        return Reusable;
    }

    public Boolean getAutoUseAfterBuoght() {
        return autoUseAfterBuoght;
    }

    public Boolean getCanBeInInventory() {
        return canBeInInventory;
    }

    public Boolean getShouldRemoveWhenLeftUsageIsZero() {
        return shouldRemoveWhenLeftUsageIsZero;
    }

    public Boolean getWearOffAfterSold() {
        return wearOffAfterSold;
    }

    public Boolean getHasCoolDown() {
        return hasCoolDown;
    }

    public Integer getCoolDownTime() {
        return coolDownTime;
    }

    public Integer getInitialPrice() {
        return initialPrice;
    }

    public Integer getReusablityNumber() {
        return reusablityNumber;
    }

    public Integer getPriceInfaltionRate() {
        return priceInfaltionRate;
    }
}
