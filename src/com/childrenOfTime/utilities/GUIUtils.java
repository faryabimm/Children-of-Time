package com.childrenOfTime.utilities;

import com.childrenOfTime.cgd.CustomGameDAO;
import com.childrenOfTime.controller.GameEngine;
import com.childrenOfTime.gui.customizedElements.MenuScreenPanel;
import com.childrenOfTime.gui.notification.NotificationPopup;
import com.childrenOfTime.gui.notification.NotificationType;
import com.childrenOfTime.model.Battle;
import com.childrenOfTime.model.Equip.AbilComps.Ability;
import com.childrenOfTime.model.Equip.Effect;
import com.childrenOfTime.model.Equip.ItemComps.Item;
import com.childrenOfTime.gui.customizedElements.Scenario;
import com.childrenOfTime.model.Store;
import com.childrenOfTime.model.Story;
import com.childrenOfTime.model.Warriors.HeroClass;
import com.childrenOfTime.model.Warriors.Warrior;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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


            ObjectInputStream objectIO9 = new ObjectInputStream(new FileInputStream(
                    new File(CustomGameDAO.currentUserCGDataPath + "stories.dat")));
            CustomGameDAO.currentUserCustomStories = (ArrayList<Story>) objectIO9.readObject();
            System.out.println("deserialized");
            objectIO9.close();


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

        return new ImageIcon(filePath);
    }

    public static ImageIcon getScaledIcon(ImageIcon source, int width, int height, int hints) {
        if (source == null) return null;
        return new ImageIcon(source.getImage().getScaledInstance(width, height, hints));
    }

    public static ImageIcon getScaledIconByFilePath(String filePath, int width, int height, int hints) {
        ImageIcon source = new ImageIcon(filePath);
        return GUIUtils.getScaledIcon(source, width, height, hints);
    }

    public static Image getScaledImageByFilePath(String filePath, int width, int height, int hints) {
        return GameEngine.DEFAULT_TOOLKIT.getImage(filePath).getScaledInstance(width, height, hints);
    }


    public static void showNotification_ALPHA(String message, NotificationType type) {
        System.out.println("SHIT");
    }


    public static void showNotification(String message, NotificationType type) {

        SwingUtilities.invokeLater(() -> {
//                try {
//                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
//                } catch (final Exception e1) {
//                    e1.printStackTrace();
//                }
            final NotificationPopup f = new NotificationPopup(type);

            final Container c = f.getContentPane();
            c.setLayout(new GridBagLayout());

            final GridBagConstraints constraints = new GridBagConstraints();
            constraints.gridx = 0;
            constraints.gridy = 0;
            constraints.weightx = 1.0f;
            constraints.weighty = 1.0f;
            constraints.insets = new Insets(5, 5, 5, 5);
            constraints.fill = GridBagConstraints.BOTH;

            final JLabel l = new JLabel(message);
            l.setForeground(NotificationPopup.NOTIFICATION_FONT_COLOR);
            l.setHorizontalAlignment(SwingConstants.CENTER);
            l.setOpaque(false);
            c.add(l, constraints);
            constraints.gridx++;
            constraints.weightx = 0f;
            constraints.weighty = 0f;
            constraints.fill = GridBagConstraints.NONE;
            constraints.anchor = GridBagConstraints.NORTH;
//        final JButton b = new JButton(new AbstractAction("x") {
//
//          @Override
//          public void actionPerformed(final ActionEvent e) {
//            f.dispose();
//          }
//        });
//
//        b.setOpaque(false);
//        b.setMargin(new Insets(1, 4, 1, 4));
//        b.setFocusable(false);
//
//        c.add(b, constraints);
            f.setVisible(true);
            new Timer(NotificationPopup.NOTIFICATION_DELAY_TIME, e -> f.dispose()).start();
        });
    }
}