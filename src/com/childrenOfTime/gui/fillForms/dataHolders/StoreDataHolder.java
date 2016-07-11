package com.childrenOfTime.gui.fillForms.dataHolders;

import com.childrenOfTime.model.Equip.ItemComps.Item;

import java.util.ArrayList;

/**
 * Created by mohammadmahdi on 7/11/16.
 */
public class StoreDataHolder {
    public ArrayList<Item> storeItems = new ArrayList<>();
    public boolean canInflatePrices;
    public boolean acceptsBargains;
    public int inflationRate;
    public String storeName;
}
