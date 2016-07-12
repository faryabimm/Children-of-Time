package com.childrenOfTime.model.Equip.ItemComps;

import java.io.Serializable;

/**
 * Created by SaeedHD on 07/08/2016.
 */
public class Messages implements Serializable {


    public String description = "No Description Added";
    private String SuccessMessage = "Ability / Item Casted Succesful ";
    public String epFailureMessage = "Not Enough EP";
    public String mpSuccessMessage = "Not Enough MP";
    public String coolDownFailureMessage = "Ability / Item is still in Cooldown";

    public Messages() {
    }
    public Messages(String description, String successMessage, String epFailureMessage, String mpSuccessMessage, String coolDownFailureMessage, String notAcquiredFailureMessage) {
        if (description != null) this.description = description;
        if (successMessage != null) SuccessMessage = successMessage;
        if (epFailureMessage != null) this.epFailureMessage = epFailureMessage;
        if (mpSuccessMessage != null) this.mpSuccessMessage = mpSuccessMessage;
        if (coolDownFailureMessage != null) this.coolDownFailureMessage = coolDownFailureMessage;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getSuccessMessage() {
        return SuccessMessage;
    }
    public void setSuccessMessage(String successMessage) {
        SuccessMessage = successMessage;
    }
    public String getEpFailureMessage() {
        return epFailureMessage;
    }
    public void setEpFailureMessage(String epFailureMessage) {
        this.epFailureMessage = epFailureMessage;
    }
    public String getMpSuccessMessage() {
        return mpSuccessMessage;
    }
    public void setMpSuccessMessage(String mpSuccessMessage) {
        this.mpSuccessMessage = mpSuccessMessage;
    }
    public String getCoolDownFailureMessage() {
        return coolDownFailureMessage;
    }
    public void setCoolDownFailureMessage(String coolDownFailureMessage) {
        this.coolDownFailureMessage = coolDownFailureMessage;
    }
}
