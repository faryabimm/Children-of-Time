package com.childrenOfTime.gui.singlePlayer;

import com.childrenOfTime.gui.MainMenuScreenPanel;
import com.childrenOfTime.gui.announcementPanels.GameOverAnnouncementPanel;
import com.childrenOfTime.gui.announcementPanels.ModalAnnouncer;
import com.childrenOfTime.gui.customizedElements.CustomizedJButton;
import com.childrenOfTime.gui.customizedElements.MenuScreenPanel;
import com.childrenOfTime.gui.singlePlayer.battleScreen.BattleScreenPanel;
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
    private ModalAnnouncer father;

    public BattleScreenPanelPause(ModalAnnouncer battleScreenPanel) {

        this.father = battleScreenPanel;
    }

    @Override
    public void initialize() {

        JButton resume = new CustomizedJButton("Resume");
//        JButton map = new CustomizedJButton("Back to Map");
        JButton quit = new CustomizedJButton("Quit Scenario");

        quit.setBackground(Color.red);
        quit.setForeground(Color.yellow);

        this.add(resume);
//        this.add(map);
        this.add(quit);

        quit.setLocation(ChildrenOfTime.PREFERRED_WIDTH / 2 - CustomizedJButton.BUTTON_WIDTH * 3 / 2 - ELEMENT_GAP,
                ChildrenOfTime.PREFERRED_HEIGHT * 2 / 3);
//        map.setLocation(ChildrenOfTime.PREFERRED_WIDTH / 2 - CustomizedJButton.BUTTON_WIDTH / 2,
//                ChildrenOfTime.PREFERRED_HEIGHT * 2 / 3);
        resume.setLocation(ChildrenOfTime.PREFERRED_WIDTH / 2 + CustomizedJButton.BUTTON_WIDTH / 2 + ELEMENT_GAP,
                ChildrenOfTime.PREFERRED_HEIGHT * 2 / 3);
        resume.addActionListener(e -> {
            ((ModalAnnouncer) this.getParent()).dispose();
//                ChildrenOfTime.changeContentPaneNOANIMATION(BattleScreenPanel.lastState);
        });

//        map.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                ChildrenOfTime.changeContentPane(SinglePlayerGame.lastState);
//                SinglePlayerGame.lastState.fade();
//                SinglePlayerGame.lastState.emerge();
//            }
//        });
        quit.addActionListener(e -> {

            ChildrenOfTime.changeContentPane(new MainMenuScreenPanel());
            father.dispose();
            ModalAnnouncer announcer = new ModalAnnouncer();
            announcer.addPanel(new GameOverAnnouncementPanel(announcer));
        });

        emerge();
    }
}
