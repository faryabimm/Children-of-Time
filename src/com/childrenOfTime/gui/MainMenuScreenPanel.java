package com.childrenOfTime.gui;

import com.childrenOfTime.cgd.CustomGameDAO;
import com.childrenOfTime.controller.GameEngine;
import com.childrenOfTime.gui.announcementPanels.ModalAnnouncer;
import com.childrenOfTime.gui.announcementPanels.StoryAnnouncementPanel;
import com.childrenOfTime.gui.customGame.CustomGameMenuScreenPanel;
import com.childrenOfTime.gui.customGame.CustomGameUserHubPanel;
import com.childrenOfTime.gui.customizedElements.CustomizedJButton;
import com.childrenOfTime.gui.customizedElements.CustomizedJImage;
import com.childrenOfTime.gui.customizedElements.MenuScreenPanel;
import com.childrenOfTime.gui.fillForms.SettingsScreenDialog;
import com.childrenOfTime.gui.multiPlayer.MultiPlayerConnectionScreenPanel;
import com.childrenOfTime.gui.notification.NotificationType;
import com.childrenOfTime.gui.singlePlayer.SinglePlayerMenuScreenPanel;
import com.childrenOfTime.model.ChildrenOfTime;
import com.childrenOfTime.model.Story;
import com.childrenOfTime.utilities.GUIUtils;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

/**
 * Created by mohammadmahdi on 7/7/16.
 */
public class MainMenuScreenPanel extends MenuScreenPanel {


    public static final int MAIN_MENU_ART_WIDTH = 500;
    public static final int MAIN_MENU_ART_HEIGHT = 408;

    public static boolean isFirstRun = true;


    @Override
    public void initialize() {
        JButton singlePlayerButton = new CustomizedJButton("Single Player");
        JButton pvpGameModeButton = new CustomizedJButton("Versus Mode");
        JButton customGameButton = new CustomizedJButton("Game Editor");
        JButton settingsButton = new CustomizedJButton("Options");
        JButton quitButton = new CustomizedJButton("Quit the Game");
        CustomizedJImage mainMenuArt = new CustomizedJImage("src/ui/icon/main_menu_art.png", MAIN_MENU_ART_WIDTH, MAIN_MENU_ART_HEIGHT);

        quitButton.setBackground(Color.red);
        quitButton.setForeground(Color.yellow);

        this.add(singlePlayerButton);
        this.add(pvpGameModeButton);
        this.add(customGameButton);
        this.add(settingsButton);
        this.add(quitButton);
        this.add(mainMenuArt);

        quitButton.setLocation(ELEMENT_GAP,
                ChildrenOfTime.PREFERRED_HEIGHT - CustomizedJButton.BUTTON_HEIGHT - ELEMENT_GAP);
        settingsButton.setLocation(ELEMENT_GAP,
                ChildrenOfTime.PREFERRED_HEIGHT - 2 * CustomizedJButton.BUTTON_HEIGHT - 2 * ELEMENT_GAP);
        customGameButton.setLocation(ELEMENT_GAP,
                ChildrenOfTime.PREFERRED_HEIGHT - 3 * CustomizedJButton.BUTTON_HEIGHT - 3 * ELEMENT_GAP);
        pvpGameModeButton.setLocation(ELEMENT_GAP,
                ChildrenOfTime.PREFERRED_HEIGHT - 4 * CustomizedJButton.BUTTON_HEIGHT - 4 * ELEMENT_GAP);
        singlePlayerButton.setLocation(ELEMENT_GAP,
                ChildrenOfTime.PREFERRED_HEIGHT - 5 * CustomizedJButton.BUTTON_HEIGHT - 5 * ELEMENT_GAP);

        mainMenuArt.setLocation(ChildrenOfTime.PREFERRED_WIDTH - MAIN_MENU_ART_WIDTH, ChildrenOfTime.PREFERRED_HEIGHT - MAIN_MENU_ART_HEIGHT);


        quitButton.addActionListener(e -> System.exit(0));
        settingsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fade();
                new SettingsScreenDialog();
            }
        });
        customGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (CustomGameDAO.getCurrentUser() == null) {
                    ChildrenOfTime.changeContentPane(new CustomGameMenuScreenPanel());
                } else {
                    ChildrenOfTime.changeContentPane(new CustomGameUserHubPanel());
                }
            }
        });
        singlePlayerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ChildrenOfTime.changeContentPane(new SinglePlayerMenuScreenPanel());
                ModalAnnouncer announcer = new ModalAnnouncer();
                announcer.addPanel(new StoryAnnouncementPanel(new Story("welcome!", "Hello, and welcome to witness result of days " +
                        "of continues work and restless effort, welcome to \"CHILDREN OF TIME\"! We hope you enjoy it!" +
                        " Mohammadmahdi Faryabi - Saeed Haddadan")));
            }
        });
        pvpGameModeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (CustomGameDAO.getCurrentUser() == null) {
                    GUIUtils.showNotification("You must first log into your account!", NotificationType.BAD);
                } else {
                    ChildrenOfTime.changeContentPane(new MultiPlayerConnectionScreenPanel());
                }
            }
        });

        emerge();
        if (isFirstRun) {
            GUIUtils.showNotification("Welcome!", NotificationType.NORMAL);
            isFirstRun = false;
        }


        GameEngine.playThemeMusic();

    }
}
