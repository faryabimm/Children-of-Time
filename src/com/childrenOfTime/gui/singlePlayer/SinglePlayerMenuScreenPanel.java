package com.childrenOfTime.gui.singlePlayer;

import com.childrenOfTime.cgd.CustomGameDAO;
import com.childrenOfTime.gui.MainMenuScreenPanel;
import com.childrenOfTime.gui.customizedElements.CustomizedJButton;
import com.childrenOfTime.gui.customizedElements.MenuScreenPanel;
import com.childrenOfTime.gui.fillForms.CustomScenarioSelectorDialog;
import com.childrenOfTime.gui.fillForms.dataHolders.CustomScenarioInfoHolder;
import com.childrenOfTime.gui.notification.NotificationType;
import com.childrenOfTime.model.ChildrenOfTime;
import com.childrenOfTime.utilities.GUIUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by mohammadmahdi on 7/11/16.
 */
public class SinglePlayerMenuScreenPanel extends MenuScreenPanel {
    @Override
    public void initialize() {


        JButton defaultScenario = new CustomizedJButton("the Endless Collection");
        JButton customScenario = new CustomizedJButton("play a custom Scenario");
        JButton back = new CustomizedJButton("Back to Main menu");

        back.setBackground(Color.red);
        back.setForeground(Color.yellow);

        this.add(back);
        this.add(defaultScenario);
        this.add(customScenario);


        back.setLocation(ELEMENT_GAP, ChildrenOfTime.PREFERRED_HEIGHT - CustomizedJButton.BUTTON_HEIGHT - ELEMENT_GAP);
        customScenario.setLocation(ELEMENT_GAP, ChildrenOfTime.PREFERRED_HEIGHT - 2 * CustomizedJButton.BUTTON_HEIGHT - 2 * ELEMENT_GAP);
        defaultScenario.setLocation(ELEMENT_GAP, ChildrenOfTime.PREFERRED_HEIGHT - 3 * CustomizedJButton.BUTTON_HEIGHT - 3 * ELEMENT_GAP);


        customScenario.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (CustomGameDAO.getCurrentUser() == null) {
                    GUIUtils.showNotification("You must first log into your account!", NotificationType.BAD);
                } else {
                    fade();
                    CustomScenarioInfoHolder infoHolder = new CustomScenarioInfoHolder();
                    new CustomScenarioSelectorDialog(infoHolder);
                }
                //TODO To Be IMPLEMENTED
            }
        });
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ChildrenOfTime.changeContentPane(new MainMenuScreenPanel());
            }
        });
        emerge();
    }
}
