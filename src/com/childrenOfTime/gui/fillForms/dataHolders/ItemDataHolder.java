package com.childrenOfTime.gui.fillForms.dataHolders;

import com.childrenOfTime.model.Equip.Effect;
import com.childrenOfTime.model.Equip.Target;

import java.util.ArrayList;

/**
 * Created by mohammadmahdi on 7/10/16.
 */
public class ItemDataHolder {

    public String itemName;
    public Target targetType;
    public int initialPrice;
    public boolean hasInflation;
    public int inflationRate;
    public boolean isReusable;
    public int reusableTimes;
    public boolean hasCooldown;
    public int cooldownTurns;
    public String imageFilePath;
    public boolean autoUseAfterPurchse;
    public boolean placableInInventory;
    public boolean removeFromInventoryAfterUsageLimit;
    public boolean wearOffEffectsAfterSelling;


    public String description;
    public String successMessage;
    public String EPFailiureMessage;
    public String MPFailiureMessage;
    public String cooldownFailiureMessage;
    public String notAcqiredFailiureMessage;
    public String imagePath;

    public ArrayList<Effect> effects = new ArrayList<>();


    public double APCoefficient;
    public int APIncrement;
    public double HCoefficient;
    public int HIncrement;
    public double MPCoefficient;
    public int MPIncrement;
    public double MMPCoefficient;
    public int MMPIncrement;
    public double EPCoefficient;
    public int EPIncrement;
    public double HRRCoefficient;
    public int HRRIncrement;
    public double MMRRCoefficient;
    public int MMRRIncrement;

}