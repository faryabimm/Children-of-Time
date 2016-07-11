package com.childrenOfTime.utilities;

import com.childrenOfTime.cgd.CustomGameDAO;
import com.childrenOfTime.controller.GameEngine;
import com.childrenOfTime.gui.customizedElements.MenuScreenPanel;
import com.childrenOfTime.model.Battle;
import com.childrenOfTime.model.Equip.AbilComps.Ability;
import com.childrenOfTime.model.Equip.Effect;
import com.childrenOfTime.model.Equip.ItemComps.Item;
import com.childrenOfTime.gui.customizedElements.Scenario;
import com.childrenOfTime.model.Store;
import com.childrenOfTime.model.Warriors.HeroClass;
import com.childrenOfTime.model.Warriors.Warrior;

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

            ObjectInputStream objectIO1 = new ObjectInputStream(new FileInputStream(
                    new File(CustomGameDAO.currentUserCGDataPath + "abilities.dat")));
            CustomGameDAO.currentUserCustomAbilities = (ArrayList<Ability>) objectIO1.readObject();
            System.out.println("deserialized");
            objectIO1.close();


            ObjectInputStream objectIO2 = new ObjectInputStream(new FileInputStream(
                    new File(CustomGameDAO.currentUserCGDataPath + "effects.dat")));
            CustomGameDAO.currentUserCustomEffects = (ArrayList<Effect>) objectIO2.readObject();
            System.out.println("deserialized");
            objectIO2.close();


            ObjectInputStream objectIO3 = new ObjectInputStream(new FileInputStream(
                    new File(CustomGameDAO.currentUserCGDataPath + "items.dat")));
            CustomGameDAO.currentUserCustomItems = (ArrayList<Item>) objectIO3.readObject();
            System.out.println("deserialized");
            objectIO3.close();


            ObjectInputStream objectIO4 = new ObjectInputStream(new FileInputStream(
                    new File(CustomGameDAO.currentUserCGDataPath + "battles.dat")));
            CustomGameDAO.currentUserCustomBattles = (ArrayList<Battle>) objectIO4.readObject();
            System.out.println("deserialized");
            objectIO4.close();


            ObjectInputStream objectIO5 = new ObjectInputStream(new FileInputStream(
                    new File(CustomGameDAO.currentUserCGDataPath + "scenarios.dat")));
            CustomGameDAO.currentUserCustomScenarios = (ArrayList<Scenario>) objectIO5.readObject();
            System.out.println("deserialized");
            objectIO5.close();

            ObjectInputStream objectIO6 = new ObjectInputStream(new FileInputStream(
                    new File(CustomGameDAO.currentUserCGDataPath + "warriors.dat")));
            CustomGameDAO.currentUserCustomWarriors = (ArrayList<Warrior>) objectIO6.readObject();
            System.out.println("deserialized");
            objectIO6.close();


            ObjectInputStream objectIO7 = new ObjectInputStream(new FileInputStream(
                    new File(CustomGameDAO.currentUserCGDataPath + "warriorClasses.dat")));
            CustomGameDAO.currentUserCustomWarriorClasses = (ArrayList<HeroClass>) objectIO7.readObject();
            System.out.println("deserialized");
            objectIO7.close();

            ObjectInputStream objectIO8 = new ObjectInputStream(new FileInputStream(
                    new File(CustomGameDAO.currentUserCGDataPath + "stores.dat")));
            CustomGameDAO.currentUserCustomStores = (ArrayList<Store>) objectIO8.readObject();
            System.out.println("deserialized");
            objectIO8.close();


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

    public static ImageIcon getIConByFilePath(String filePath) {

        if (filePath == null) return null;
        Image image = GameEngine.DEFAULT_TOOLKIT.getImage(filePath).getScaledInstance(
                MenuScreenPanel.PREFFERED_ELEMENT_ICON_SIZE, MenuScreenPanel.PREFFERED_ELEMENT_ICON_SIZE, 0);

        return GUIUtils.imageToIcon(image);
    }

    public static ImageIcon getScaledIcon(ImageIcon source, int width, int height, int hints) {
        return new ImageIcon(source.getImage().getScaledInstance(width, height, hints));
    }
}