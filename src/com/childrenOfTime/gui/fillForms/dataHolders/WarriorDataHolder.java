package com.childrenOfTime.gui.fillForms.dataHolders;

import com.childrenOfTime.model.Equip.AbilComps.Ability;
import com.childrenOfTime.model.Warriors.HeroClass;

import java.util.ArrayList;

/**
 * Created by mohammadmahdi on 7/11/16.
 */
public class WarriorDataHolder {

    public boolean operationCancelled = true;

    public String name;
    public HeroClass warriorClass;
    public ArrayList<Ability> specificAbilities = new ArrayList<>();
    public String imageFilePath;
}
