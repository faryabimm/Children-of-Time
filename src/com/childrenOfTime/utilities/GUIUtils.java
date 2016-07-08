package com.childrenOfTime.utilities;

/**
 * Created by mohammadmahdi on 7/8/16.
 */
public class GUIUtils {
    public static int randomInt(int min, int max) {
        max+=1;
//        System.out.println((int) Math.abs(Math.random()*(max - min) + min));
        return (int) Math.abs(Math.random()*(max - min) + min);
    }
}
