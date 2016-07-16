package com.childrenOfTime.gui.customGame;

import com.childrenOfTime.gui.customizedElements.CustomizedJButton;
import com.childrenOfTime.gui.customizedElements.CustomizedJImage;
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
                ChildrenOfTime.PREFERRED_HEIGHT - CustomizedJButton.BUTTON_HEIGHT - ELEMENT_GAP);
        logIn.setLocation(ELEMENT_GAP,
                ChildrenOfTime.PREFERRED_HEIGHT - 2*CustomizedJButton.BUTTON_HEIGHT - 2*ELEMENT_GAP);

        cancel.addActionListener(e -> ChildrenOfTime.changeContentPane(new CustomGameMenuScreenPanel()));


        CustomizedJImage mainMenuArt = new CustomizedJImage("src/ui/Children Of Time Art Assets/COT (58).png", 200, 200);
        this.add(mainMenuArt);
        mainMenuArt.setLocation(ChildrenOfTime.PREFERRED_WIDTH - 200 - ELEMENT_GAP, ChildrenOfTime.PREFERRED_HEIGHT - 200 - ELEMENT_GAP);



        emerge();
    }
}
