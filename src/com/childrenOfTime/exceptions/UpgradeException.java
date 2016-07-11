package com.childrenOfTime.exceptions;

import java.io.Serializable;

/**
 * Created by mohammadmahdi on 5/8/16.
 */
public class UpgradeException extends GameException implements Serializable {
    public UpgradeException(String message) {
        super(message);
    }
}
