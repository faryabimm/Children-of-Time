package com.childrenOfTime.model;

import java.util.Map;

/**
 * Created by mohammadmahdi on 5/14/16.
 */
public class Testing {

    public static void main(String[] args) {
        Hero hero = new Hero("Eley", "Fighter", 1);

        for (Map.Entry<String, Ability> entry : hero.abilities.entrySet()) {
            System.out.println(entry.getKey());
        }
    }
}
