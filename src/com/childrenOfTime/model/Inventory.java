package com.childrenOfTime.model;

import java.util.ArrayList;

/**
 * Created by mohammadmahdi on 5/8/16.
 */
public class Inventory {
    private int maxCapacity;
    private ArrayList<Item> items = new ArrayList<>();

    public Inventory(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    public ArrayList<Item> getItems() {
        return items;
    }
    public int getAvailableCapacity(){
        int sum=0;
        for(Item i:items){
            sum+=i.getSize();
        }
        return maxCapacity-sum;
    }


}
