package com.childrenOfTime.gui.fillForms.dataHolders;

import com.childrenOfTime.model.Equip.AbilComps.Ability;
import com.sun.xml.internal.ws.server.ServerRtException;

import java.lang.annotation.Annotation;
import java.util.ArrayList;

/**
 * Created by mohammadmahdi on 7/11/16.
 */
public class WarriorClassDataHolder {
    public ArrayList<Ability> warriorClassAbilities = new ArrayList<>();

    public String backStory;
    public String name;
    public String description;
    public String dyingMessage;
    public String ActionMessage;

    public boolean canAttack;
    public boolean canBurnEP;

    public int burnEPRangeStart;
    public int burnEPRangeEnd;
    public int EPCostForPerformer;

    public boolean healthRelatedAP;

    public int APInHighHealthLevel;
    public int APInLowHealthLevel;

    public String mutationMessage;

    public boolean EPChangeSystem;
    public boolean MPChangeSystem;
    public boolean CanUseImmortalityPotion;
    public boolean CanUseRefilSystemFeatures;

    public int maxH;
    public int maxM;
    public int HRR;
    public int MRR;
    public int AI;

    public int inventorySize;
    public int turnInitialEP;

    public String imagePath;

    public String EPBurningMessage;

    public int mutationHealthLimit;

    public boolean canBuyItems;
}
