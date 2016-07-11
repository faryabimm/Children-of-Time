package com.childrenOfTime.gui.fillForms.dataHolders;

import com.childrenOfTime.model.Equip.AbilComps.Upgrade;
import com.childrenOfTime.model.Equip.Target;

import java.util.ArrayList;

/**
 * Created by mohammadmahdi on 7/10/16.
 */
public class AbilityDataHolder {
    public String abilityName;
    public Target targetType;
    public int power;

    public String description;
    public String successMessage;
    public String EPFailiureMessage;
    public String MPFailiureMessage;
    public String cooldownFailiureMessage;
    public String notAcqiredFailiureMessage;
    public String imagePath;

    public ArrayList<Upgrade> upgrades = new ArrayList<>();

}
