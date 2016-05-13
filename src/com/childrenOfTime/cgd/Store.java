package com.childrenOfTime.cgd;

import com.childrenOfTime.model.Item;

import java.util.ArrayList;

/**
 * Created by mohammadmahdi on 5/8/16.
 */
public class Store {
    private static ArrayList<Store> stores = new ArrayList<>();

    public static void addStore(Store store) {
        stores.add(store);
    }

    public static ArrayList<Store> getStores() {
        return stores;
    }


    public ArrayList<String> getItems() {
        //TODO implement
        return null;
    }

    public void showItems() {
    }

    public Item getItembyName(String temp) {

        return null;
    }
}
