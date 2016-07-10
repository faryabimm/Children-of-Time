package com.childrenOfTime.utilities;

import com.childrenOfTime.cgd.CustomGameDAO;

import java.io.*;

/**
 * Created by mohammadmahdi on 7/8/16.
 */
public class GUIUtils {
    public static int randomInt(int min, int max) {
        max+=1;
//        System.out.println((int) Math.abs(Math.random()*(max - min) + min));
        return (int) Math.abs(Math.random()*(max - min) + min);
    }

    public static <E> void serializeUserObject(E object, String objectType) throws IOException {
        String fileAddress = CustomGameDAO.currentUserCGDataPath + objectType + ".dat";
        ObjectOutputStream objectIO = new ObjectOutputStream(new FileOutputStream(new File(fileAddress)));
        objectIO.writeObject(object);
        objectIO.close();
    }

    public static void deserializeUserFiles() {
        System.out.println("SHOULD BE IMPLEMENTED");
    }
}
