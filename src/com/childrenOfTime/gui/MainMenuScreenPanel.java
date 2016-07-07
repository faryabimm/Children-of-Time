package com.childrenOfTime.gui;

import com.childrenOfTime.gui.customGame.CustomGameMenuScreenPanel;
import com.childrenOfTime.gui.customizedElements.CustomizedJButton;
import com.childrenOfTime.gui.customizedElements.MenuScreenPanel;
import com.childrenOfTime.model.ChildrenOfTime;

import javax.swing.*;

/**
 * Created by mohammadmahdi on 7/7/16.
 */
public class MainMenuScreenPanel extends MenuScreenPanel {


    public MainMenuScreenPanel() {

    }

    public void initialize() {
        JButton singlePlayerButton = new CustomizedJButton("Single Player");
        JButton pvpGameModeButton = new CustomizedJButton("PvP");
        JButton customGameButton = new CustomizedJButton("Game Editor");
        JButton settingsButton = new CustomizedJButton("Options");
        JButton quitButton = new CustomizedJButton("Quit the Game");

        this.add(singlePlayerButton);
        this.add(pvpGameModeButton);
        this.add(customGameButton);
        this.add(settingsButton);
        this.add(quitButton);

        quitButton.setLocation(ELEMENT_GAP,
                ChildrenOfTime.PREFERRED_HEIGHT - CustomizedJButton.MAIN_MENU_BUTTON_HEIGHT - ELEMENT_GAP);
        settingsButton.setLocation(ELEMENT_GAP,
                ChildrenOfTime.PREFERRED_HEIGHT - 2*CustomizedJButton.MAIN_MENU_BUTTON_HEIGHT - 2*ELEMENT_GAP);
        customGameButton.setLocation(ELEMENT_GAP,
                ChildrenOfTime.PREFERRED_HEIGHT - 3*CustomizedJButton.MAIN_MENU_BUTTON_HEIGHT - 3*ELEMENT_GAP);
        pvpGameModeButton.setLocation(ELEMENT_GAP,
                ChildrenOfTime.PREFERRED_HEIGHT - 4*CustomizedJButton.MAIN_MENU_BUTTON_HEIGHT - 4*ELEMENT_GAP);
        singlePlayerButton.setLocation(ELEMENT_GAP,
                ChildrenOfTime.PREFERRED_HEIGHT - 5*CustomizedJButton.MAIN_MENU_BUTTON_HEIGHT - 5*ELEMENT_GAP);

        quitButton.addActionListener(e -> System.exit(0));
        customGameButton.addActionListener(e -> ChildrenOfTime.changeContentPane(new CustomGameMenuScreenPanel()));
        emerge();
    }
}
