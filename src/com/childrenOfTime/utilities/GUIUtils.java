package com.childrenOfTime.utilities;

import com.childrenOfTime.cgd.CustomGameDAO;
import com.childrenOfTime.model.Equip.Effect;

import java.io.*;
import java.util.ArrayList;

/**
 * Created by mohammadmahdi on 7/8/16.
 */
public class GUIUtils {
    public static int randomInt(int min, int max) {
        max += 1;
//        System.out.println((int) Math.abs(Math.random()*(max - min) + min));
        return (int) Math.abs(Math.random() * (max - min) + min);
    }

    public static <E> void serializeUserObject(E object, String objectType) throws IOException {
        String fileAddress = CustomGameDAO.currentUserCGDataPath + objectType + ".dat";
        ObjectOutputStream objectIO = new ObjectOutputStream(new FileOutputStream(new File(fileAddress)));
        objectIO.writeObject(object);
        objectIO.close();
    }

    public static void deserializeUserFiles() {
        try {
            ObjectInputStream objectIO = new ObjectInputStream(new FileInputStream(
                    new File(CustomGameDAO.currentUserCGDataPath + "effects.dat")));
            CustomGameDAO.currentUserCustomEffects = (ArrayList<Effect>) objectIO.readObject();
            objectIO.close();

            System.out.println("SHOULD BE COMPLETED");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}