package com.childrenOfTime.model;

import java.io.Serializable;

/**
 * Created by mohammadmahdi on 7/12/16.
 */
public class Story implements Serializable {

    private String story;
    String name;

    public Story(String name, String s) {
        this.name = name;
        this.story = s;
    }

    public String getName() {
        return name;
    }

    public String getStory() {
        return story;
    }
}
