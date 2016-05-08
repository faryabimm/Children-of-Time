package com.childrenOfTime.model;

/**
 * Created by mohammadmahdi on 5/7/16.
 */
public final class ChildrenOfTime {

    private static ChildrenOfTime instance;

    public static ChildrenOfTime getInstance() {
        if (instance == null) {
            instance = new ChildrenOfTime();
        }
        return instance;
    }

    private ChildrenOfTime() {
    }
}
