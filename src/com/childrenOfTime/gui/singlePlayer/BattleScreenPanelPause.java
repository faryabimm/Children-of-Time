package com.childrenOfTime.gui.singlePlayer;

import com.childrenOfTime.gui.customizedElements.CustomizedJButton;
import com.childrenOfTime.gui.customizedElements.MenuScreenPanel;
import com.childrenOfTime.model.ChildrenOfTime;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by mohammadmahdi on 7/14/16.
 */
public class BattleScreenPanelPause extends MenuScreenPanel {

    public static final int GAP = 100;

    private BattleScreenPanel battleScreenPanel;

    public BattleScreenPanelPause(BattleScreenPanel battleScreenPanel) {
        this.battleScreenPanel = battleScreenPanel;
    }

    @Override
    public void initialize() {

        JButton resume = new CustomizedJButton("Resume");
        JButton map = new CustomizedJButton("Back to Map");
        JButton quit = new CustomizedJButton("Quit Scenario");

        quit.setBackground(Color.red);
        quit.setForeground(Color.yellow);

        this.add(resume);
        this.add(map);
        this.add(quit);

        quit.setLocation(ChildrenOfTime.PREFERRED_WIDTH / 2 - CustomizedJButton.BUTTON_WIDTH * 3 / 2 - ELEMENT_GAP,
                ChildrenOfTime.PREFERRED_HEIGHT * 2 / 3);
        map.setLocation(ChildrenOfTime.PREFERRED_WIDTH / 2 - CustomizedJButton.BUTTON_WIDTH / 2,
                ChildrenOfTime.PREFERRED_HEIGHT * 2 / 3);
        resume.setLocation(ChildrenOfTime.PREFERRED_WIDTH / 2 + CustomizedJButton.BUTTON_WIDTH / 2 + ELEMENT_GAP,
                ChildrenOfTime.PREFERRED_HEIGHT * 2 / 3);
        resume.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ChildrenOfTime.changeContentPane(BattleScreenPanel.lastState);
                BattleScreenPanel.lastState.emerge();
            }
        });

        map.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ChildrenOfTime.changeContentPane(SinglePlayerGame.lastState);
                SinglePlayerGame.lastState.fade();
                SinglePlayerGame.lastState.emerge();
            }
        });
        quit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ChildrenOfTime.changeContentPane(new SinglePlayerMenuScreenPanel());
            }
        });

        emerge();
    }
}
