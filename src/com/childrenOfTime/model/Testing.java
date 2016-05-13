package com.childrenOfTime.model;

import sun.misc.FpUtils;

/**
 * Created by mohammadmahdi on 5/14/16.
 */
public class Testing {

    public static void main(String[] args) {
        Foe foe = new Foe("Final Boss", StrengthOfFoes.Able, 1);

        System.out.println(foe.type.equals(TypesOfFoes.Final));

        System.out.println(foe.description);
    }
}
