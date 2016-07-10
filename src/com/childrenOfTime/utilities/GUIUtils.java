package com.childrenOfTime.utilities;

import com.childrenOfTime.cgd.CustomGameDAO;
import com.childrenOfTime.model.Equip.Effect;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
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

    public static Image iconToImage(ImageIcon icon) {
        return icon.getImage();
    }
    public static ImageIcon imageToIcon(Image image) {
        return new ImageIcon(image);
    }

    public static String getPNGImageFilePath(Component parent) {

        String toReturn = null;

        JFileChooser fileChooser = new JFileChooser("~/");
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooser.setDialogTitle("Choose an image file");
        fileChooser.setFileFilter(new FileNameExtensionFilter("PNG image files","png"));

        fileChooser.showOpenDialog(parent);
        try {
            toReturn = fileChooser.getSelectedFile().toString();
        } catch (NullPointerException e) {
            toReturn = null;
        }

        System.out.println(toReturn);

        return toReturn;
    }
}