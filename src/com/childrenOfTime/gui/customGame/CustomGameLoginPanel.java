package com.childrenOfTime.gui.customGame;

import com.childrenOfTime.gui.customizedElements.CustomizedJButton;
import com.childrenOfTime.gui.customizedElements.MenuScreenPanel;
import com.childrenOfTime.model.ChildrenOfTime;

import javax.swing.*;

/**
 * Created by mohammadmahdi on 7/7/16.
 */
public class CustomGameLoginPanel extends MenuScreenPanel {
    @Override
    public void initialize() {

        JButton logIn  = new CustomizedJButton("Log In");
        JButton cancel = new CustomizedJButton("Cancel");

        this.add(logIn);
        this.add(cancel);

        cancel.setLocation(ELEMENT_GAP,
                ChildrenOfTime.PREFERRED_HEIGHT - CustomizedJButton.MAIN_MENU_BUTTON_HEIGHT - ELEMENT_GAP);
        logIn.setLocation(ELEMENT_GAP,
                ChildrenOfTime.PREFERRED_HEIGHT - 2*CustomizedJButton.MAIN_MENU_BUTTON_HEIGHT - 2*ELEMENT_GAP);

        cancel.addActionListener(e -> ChildrenOfTime.changeContentPane(new CustomGameMenuScreenPanel()));

        emerge();
    }
}
