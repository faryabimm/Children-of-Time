package com.childrenOfTime.gui.multiPlayer;

import com.childrenOfTime.gui.MainMenuScreenPanel;

import com.childrenOfTime.gui.customizedElements.CustomizedJButton;
import com.childrenOfTime.gui.customizedElements.CustomizedJImage;
import com.childrenOfTime.gui.customizedElements.MenuScreenPanel;
import com.childrenOfTime.model.ChildrenOfTime;
import com.childrenOfTime.model.MultiPlayer.MultiPlayer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by mohammadmahdi on 7/12/16.
 */
public class MultiPlayerConnectionScreenPanel extends MenuScreenPanel {

    @Override
    public void initialize() {

        JButton hostAGame = new CustomizedJButton("Host a Game");
        JButton joinAGame = new CustomizedJButton("Join a Game");
        JButton back = new CustomizedJButton("Back");

        back.setBackground(Color.red);
        back.setForeground(Color.yellow);

        this.add(hostAGame);
        this.add(joinAGame);
        this.add(back);

        back.setLocation(ELEMENT_GAP,
                ChildrenOfTime.PREFERRED_HEIGHT - CustomizedJButton.BUTTON_HEIGHT - ELEMENT_GAP);
        joinAGame.setLocation(ELEMENT_GAP,
                ChildrenOfTime.PREFERRED_HEIGHT - 2 * CustomizedJButton.BUTTON_HEIGHT - 2 * ELEMENT_GAP);
        hostAGame.setLocation(ELEMENT_GAP,
                ChildrenOfTime.PREFERRED_HEIGHT - 3 * CustomizedJButton.BUTTON_HEIGHT - 3 * ELEMENT_GAP);
        back.addActionListener(e -> ChildrenOfTime.changeContentPane(new MainMenuScreenPanel()));
        hostAGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                MultiPlayer.startMultiPlayer();
                MultiPlayer.getInstacne().startAsHost();
                ChildrenOfTime.changeContentPane(new MutilpllayerLobbyMenu(true));
            }
        });
        joinAGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MultiPlayer.startMultiPlayer();
                MultiPlayer.getInstacne().autoJoin();
                ChildrenOfTime.changeContentPane(new MutilpllayerLobbyMenu(false));
            }
        });


        CustomizedJImage mainMenuArt = new CustomizedJImage("src/ui/Children Of Time Art Assets/COT (40).png", 200, 200);
        this.add(mainMenuArt);
        mainMenuArt.setLocation(ChildrenOfTime.PREFERRED_WIDTH - 200 - ELEMENT_GAP, ChildrenOfTime.PREFERRED_HEIGHT - 200 - ELEMENT_GAP);



        emerge();
    }
}
