package com.childrenOfTime.model;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by mohammadmahdi on 5/14/16.
 */
public class Testing {

    public static void main(String[] args) {
        Hero hero = new Hero("Eley", "Fighter", 1);
        Hero hero2 = new Hero("Chrome", "Fighter", 1);
        ArrayList a = new ArrayList<Hero>();
        a.add(hero);
        a.add(hero2);

        Player player = new Player(a);
        Foe foe = new Foe("Thug", StrengthOfFoes.Able, 1);
        hero2.abilities.get("Fight training").upgrade(hero2, player);
        hero.abilities.get("Fight training").upgrade(hero, player);
        hero.abilities.get("Work out").upgrade(hero, player);
        hero2.abilities.get("Critical strike").upgrade(hero2, player);
        hero2.abilities.get("Critical strike").upgrade(hero2, player);
        hero2.abilities.get("Critical strike").upgrade(hero2, player);
        hero2.useAbility(hero2.abilities.get("Critical strike"), foe);
        hero2.showAbDes();

        //hero.abilities.get("Sw").showDescription();
        System.out.println(hero.getCurrentEnergyPoints());
        System.out.println(foe.toString());
        hero2.attackManual(foe);
        hero2.attackManual(foe);
        hero2.attackManual(foe);

        System.out.println(foe.currentHealth);

        for (Map.Entry<String, Ability> entry : hero.abilities.entrySet()) {
            System.out.println(entry.getKey());
        }
    }
}
