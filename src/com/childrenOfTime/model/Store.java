package com.childrenOfTime.model;

import com.childrenOfTime.model.Equip.ItemComps.Item;
import com.childrenOfTime.model.Warriors.Warrior;

import java.util.Map;

/**
 * Created by SaeedHD on 07/10/2016.
 */
public class Store {
    Map<Item, Integer> items;

    public void BuyFor(Player player, Warrior warrior, Item item) {
        if (items.containsKey(item)) return;
        player.buy(item, warrior);
        items.remove(item);
    }

    public void Sell(Player player, Warrior warrior, Item item) {
        if (items.containsKey(item)) return;
        player.buy(item, warrior);
        items.remove(item);
    }

}
