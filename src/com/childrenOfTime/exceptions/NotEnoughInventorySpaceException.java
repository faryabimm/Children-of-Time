package com.childrenOfTime.exceptions;

/**
 * Created by mohammadmahdi on 5/8/16.
 */
public class NotEnoughInventorySpaceException extends TradeException {

    public NotEnoughInventorySpaceException(String message) {
        super(message);
    }

}
