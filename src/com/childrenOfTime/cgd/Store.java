package com.childrenOfTime.cgd;

import com.childrenOfTime.model.InformationOfItems;

import java.util.ArrayList;

import static com.childrenOfTime.view.IOHandler.printOutput;

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
        for (InformationOfItems iOI : InformationOfItems.values()) {
            printOutput(iOI.getDescription());
        }


    }

    public InformationOfItems getStoreRawItembyName(String temp) {
        for (InformationOfItems informationOfItems : InformationOfItems.values()) {
            if (informationOfItems.name().equals(temp)) {
                return informationOfItems;
            }

        }
        return null;
    }
}
