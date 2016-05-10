package com.childrenOfTime.model;

import java.util.ArrayList;

/**
 * Created by SaeedHD on 05/11/2016.
 */
public enum FighterHero{
    Eley("Elixir","Caretaker"),
    Chrome("Boost","Mana beam");
    String ability1,ability2;

    FighterHero(String ability1, String ability2) {
        this.ability1 = ability1;
        this.ability2 = ability2;
    }
}


