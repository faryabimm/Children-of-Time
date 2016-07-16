package com.childrenOfTime.gui.customGame;

import com.childrenOfTime.cgd.CustomGameDAO;
import com.childrenOfTime.gui.MainMenuScreenPanel;
import com.childrenOfTime.gui.customizedElements.CustomizedJButton;
import com.childrenOfTime.gui.customizedElements.CustomizedJImage;
import com.childrenOfTime.gui.customizedElements.MenuScreenPanel;
import com.childrenOfTime.model.ChildrenOfTime;

import javax.swing.*;
import java.awt.*;

/**
 * Created by mohammadmahdi on 7/7/16.
 */
public class CustomGameUserHubPanel extends MenuScreenPanel {

    @Override
    public void initialize() {


        JButton openEditor = new CustomizedJButton("Open Editor");


        JButton states = new CustomizedJButton("States");
        JButton signOut = new CustomizedJButton("Sign Out");
        JButton back = new CustomizedJButton("Back to Main Menu");


        back.setBackground(Color.red);
        back.setForeground(Color.yellow);

        this.add(back);
        this.add(signOut);
        this.add(states);
        this.add(openEditor);


        back.setLocation(ELEMENT_GAP, ChildrenOfTime.PREFERRED_HEIGHT - CustomizedJButton.BUTTON_HEIGHT - ELEMENT_GAP);
        signOut.setLocation(ELEMENT_GAP, ChildrenOfTime.PREFERRED_HEIGHT - 2*CustomizedJButton.BUTTON_HEIGHT - 2*ELEMENT_GAP);
        states.setLocation(ELEMENT_GAP, ChildrenOfTime.PREFERRED_HEIGHT - 3*CustomizedJButton.BUTTON_HEIGHT - 3*ELEMENT_GAP);
        openEditor.setLocation(ELEMENT_GAP, ChildrenOfTime.PREFERRED_HEIGHT - 4*CustomizedJButton.BUTTON_HEIGHT - 4*ELEMENT_GAP);



        back.addActionListener(e -> ChildrenOfTime.changeContentPane(new MainMenuScreenPanel()));
        signOut.addActionListener(e -> {
            CustomGameDAO.setCurrentUser(null);
            ChildrenOfTime.changeContentPane(new CustomGameMenuScreenPanel());
        });
        openEditor.addActionListener(e -> ChildrenOfTime.changeContentPane(new CusomGameEditorMenu()));
        CustomizedJImage mainMenuArt = new CustomizedJImage("src/ui/Children Of Time Art Assets/COT (58).png", 200, 200);
        this.add(mainMenuArt);
        mainMenuArt.setLocation(ChildrenOfTime.PREFERRED_WIDTH - 200 - ELEMENT_GAP, ChildrenOfTime.PREFERRED_HEIGHT - 200 - ELEMENT_GAP);



        emerge();
    }


}
