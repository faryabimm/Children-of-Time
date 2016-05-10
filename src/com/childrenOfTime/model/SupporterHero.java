package com.childrenOfTime.model;

import java.util.ArrayList;

/**
 * Created by SaeedHD on 05/11/2016.
 */
public enum SupporterHero {
    Meryl("Elixir","Caretaker"),
    Bolti("Boost","Mana beam");
    String ability1,ability2;

    SupporterHero(String ability1, String ability2) {
        this.ability1 = ability1;
        this.ability2 = ability2;
    }
}


