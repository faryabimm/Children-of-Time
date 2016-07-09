package com.childrenOfTime.model.Equip.ItemComps;

/**
 * Created by SaeedHD on 07/08/2016.
 */
public class Messages {


    public String description;
    private String SuccessMessage;
    public String epFailureMessage;
    public String mpSuccessMessage;
    public String coolDownFailureMessage;

    public Messages() {
        this.description = "No Description Added";
        SuccessMessage = "No Description Added";
        this.epFailureMessage = "No Description Added";
        this.mpSuccessMessage = "No Description Added";
        this.coolDownFailureMessage = "No Description Added";
    }


    public Messages(String description, String successMessage, String epFailureMessage, String mpSuccessMessage, String coolDownFailureMessage, String notAcquiredFailureMessage) {
        this.description = description;
        SuccessMessage = successMessage;
        this.epFailureMessage = epFailureMessage;
        this.mpSuccessMessage = mpSuccessMessage;
        this.coolDownFailureMessage = coolDownFailureMessage;
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
