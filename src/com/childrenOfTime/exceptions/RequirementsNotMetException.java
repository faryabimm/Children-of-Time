package com.childrenOfTime.exceptions;

/**
 * Created by mohammadmahdi on 5/8/16.
 */
public class RequirementsNotMetException extends UpgradeException {
    public RequirementsNotMetException() {
        super("the requirements are not yet met" +
                "for this upgrade.");
    }

    public RequirementsNotMetException(String message) {
        super(message);
    }
}
