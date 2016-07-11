package com.childrenOfTime.exceptions;

import java.io.Serializable;

/**
 * Created by mohammadmahdi on 5/8/16.
 */
public class AbilityNotAquiredException extends AttackException implements Serializable {
    public AbilityNotAquiredException(String message) {
        super(message);
    }
}
