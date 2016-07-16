package com.childrenOfTime.model;

import com.childrenOfTime.model.Equip.ItemComps.Item;
import com.childrenOfTime.model.Warriors.Warrior;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

import static com.childrenOfTime.view.IOHandler.printOutput;

/**
 * Created by SaeedHD on 07/10/2016.
 */
public class Store implements Serializable {
    Map<Item, Integer> items;
    boolean canBate;
    boolean canInflatePrices;
    int inflationPercentPer15Minutes;
    long t1 = System.nanoTime();

    private String name;

    public String getName() {
        return name;
    }

    public Store(Map<Item, Integer> items, boolean canBate, boolean canInflatePrices, int inflationPercentPer15Minutes, String name) {
        this.name = name;
        this.items = items;
        this.canBate = canBate;
        this.canInflatePrices = canInflatePrices;
        this.inflationPercentPer15Minutes = inflationPercentPer15Minutes;
    }

    public ArrayList<Item> getItems() {
        return items.keySet().stream().collect(Collectors.toCollection(ArrayList::new));
    }

    private void resetTimer() {
        t1 = System.nanoTime();
    }

    private int inflate(Item item) {
        if (!canInflatePrices) return 0;
        long t2 = System.nanoTime();
        long i = (t2 - t1) / 810000000000000l;
        item.currentPrice = (int) (item.getInitialPrice() * inflationPercentPer15Minutes * i / 100.0);
        printOutput("Time Passed: " + i * 15 + " minutes . Inflation Percent so far : " + inflationPercentPer15Minutes * i);
        return (int) i * inflationPercentPer15Minutes;
    }

    private int bate(Player player, Item item) {
        if (!canBate) return 0;
        int price = item.getInitialPrice();
        int discount = new Random().nextInt(50) * price / 100;
        item.currentPrice = (int) (item.currentPrice * discount / 100.0);      //TODO in PayamHa dorost Shavad
        printOutput("Real Price : " + item.getInitialPrice() + "$" + " \nFor You  :   " + item.getCurrentPriceToBuy(player.getNumbersBought(item)) + "$" + " minutes . Inflation Percent so far : " + inflationPercentPer15Minutes);
        return discount;
    }

    public void BuyFor(Player player, Warrior warrior, Item item) {
        inflate(item);
        bate(player, item);
        if (items.containsKey(item)) return;
        player.buy(item, warrior);
        items.remove(item); //TODO dorost shavad
    }

    public void Sell(Player player, Warrior warrior, Item item) {
        if (items.containsKey(item)) return;
        player.buy(item, warrior);
        items.remove(item);
    }
}
