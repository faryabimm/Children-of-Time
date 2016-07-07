package com.childrenOfTime.gui.customGame;
import com.childrenOfTime.gui.MainMenuScreenPanel;
import com.childrenOfTime.gui.customizedElements.CustomizedJButton;
import com.childrenOfTime.gui.customizedElements.MenuScreenPanel;
import com.childrenOfTime.model.ChildrenOfTime;
import javax.swing.*;

/**
 * Created by mohammadmahdi on 7/7/16.
 */
public class CustomGameMenuScreenPanel extends MenuScreenPanel {
    @Override
    public void initialize() {
        JButton signUp = new CustomizedJButton("Sing in");
        JButton signIn = new CustomizedJButton("Sign up");
        JButton goBack = new CustomizedJButton("Return to Main Menu");
        this.add(signIn);
        this.add(signUp);
        this.add(goBack);
        signUp.setLocation(ELEMENT_GAP,
                ChildrenOfTime.PREFERRED_HEIGHT - 3*CustomizedJButton.MAIN_MENU_BUTTON_HEIGHT - 3*ELEMENT_GAP);
        signIn.setLocation(ELEMENT_GAP,
                ChildrenOfTime.PREFERRED_HEIGHT - 2*CustomizedJButton.MAIN_MENU_BUTTON_HEIGHT - 2*ELEMENT_GAP);
        goBack.setLocation(ELEMENT_GAP,
                ChildrenOfTime.PREFERRED_HEIGHT - CustomizedJButton.MAIN_MENU_BUTTON_HEIGHT - ELEMENT_GAP);


        goBack.addActionListener(e -> ChildrenOfTime.changeContentPane(new MainMenuScreenPanel()));
        signIn.addActionListener(e -> ChildrenOfTime.changeContentPane(new CustomGameLoginPanel()));

        emerge();
    }



}
