package com.childrenOfTime.gui.singlePlayer;

import com.childrenOfTime.cgd.CustomGameDAO;
import com.childrenOfTime.cgd.User;
import com.childrenOfTime.gui.MainMenuScreenPanel;
import com.childrenOfTime.gui.announcementPanels.ModalAnnouncer;
import com.childrenOfTime.gui.announcementPanels.StoryAnnouncementPanel;
import com.childrenOfTime.gui.customizedElements.CustomizedJButton;
import com.childrenOfTime.gui.customizedElements.CustomizedJImage;
import com.childrenOfTime.gui.customizedElements.MenuScreenPanel;
import com.childrenOfTime.gui.customizedElements.Scenario;
import com.childrenOfTime.gui.fillForms.CustomScenarioSelectorDialog;
import com.childrenOfTime.gui.fillForms.dataHolders.CustomScenarioInfoHolder;
import com.childrenOfTime.gui.notification.NotificationType;
import com.childrenOfTime.model.ChildrenOfTime;
import com.childrenOfTime.model.Story;
import com.childrenOfTime.model.Warriors.Warrior;
import com.childrenOfTime.utilities.GUIUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

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
        defaultScenario.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CustomGameDAO.setCurrentUser(User.users.get(User.users.indexOf(new User("Mohammadmahdi74"))));
                CustomGameDAO.loadCurrentUserData();

                CustomScenarioInfoHolder infoHolder = new CustomScenarioInfoHolder();

                CustomGameDAO.currentUserCustomScenarios.stream().filter(scenario -> scenario.getName().equals("Endless Collection")).forEach(scenario -> infoHolder.playingScenario = scenario);


                for (Warrior warrior : CustomGameDAO.currentUserCustomWarriors) {
                    switch (warrior.getName()) {
                        case "Meryl":
                        case "Bolti":
                        case "Chrome":
                        case "Eley":
                            infoHolder.playerWarriors.add(warrior);
                            break;
                    }
                }

                SinglePlayerGame target = new SinglePlayerGame(infoHolder);
                ChildrenOfTime.changeContentPane(target);
                new ModalAnnouncer(new StoryAnnouncementPanel(new Story("welcome!", "You’ve entered the castle, it takes a while for your eyes to get used to the darkness but\n" +
                        "the horrifying halo of your enemies is vaguely visible. Angel’s unsettling presence and\n" +
                        "the growling of thugs tell you that your first battle has BEGUN!")));
            }
        });

        CustomizedJImage mainMenuArt = new CustomizedJImage("src/ui/Children Of Time Art Assets/COT (24).png", 200, 200);
        this.add(mainMenuArt);
        mainMenuArt.setLocation(ChildrenOfTime.PREFERRED_WIDTH - 200 - ELEMENT_GAP, ChildrenOfTime.PREFERRED_HEIGHT - 200 - ELEMENT_GAP);



        emerge();
    }
}
