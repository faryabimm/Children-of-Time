package com.childrenOfTime.gui.singlePlayer;

import com.childrenOfTime.cgd.CustomGameDAO;
import com.childrenOfTime.gui.customizedElements.CustomizedJImage;
import com.childrenOfTime.gui.customizedElements.CustomizedJLabel;
import com.childrenOfTime.gui.customizedElements.MenuScreenPanel;
import com.childrenOfTime.gui.customizedElements.WarriorStatDisplayer;
import com.childrenOfTime.model.Battle;
import com.childrenOfTime.model.ChildrenOfTime;
import com.childrenOfTime.model.Warriors.Warrior;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by mohammadmahdi on 7/12/16.
 */
public class BattleScreenPanel extends JPanel {

    private Battle battle;
    private ArrayList<Warrior> warriors;


    public static final int ELEMENT_GAP = MenuScreenPanel.ELEMENT_GAP;
    public static final int PREFFERED_AVATAR_SIZE = MenuScreenPanel.PREFFERED_AVATAR_SIZE;
    public static final int PREFFERED_ELEMENT_ICON_SIZE = MenuScreenPanel.PREFFERED_ELEMENT_ICON_SIZE;


    private JLabel userNameLabel = new CustomizedJLabel("");
    private JLabel userAvatar = new CustomizedJImage();


    public BattleScreenPanel(Battle battle, ArrayList<Warrior> warriors) {
        this.battle = battle;
        this.warriors = warriors;
        this.setLayout(null);
        this.setPreferredSize(ChildrenOfTime.PREFERRED_DIMENSION);
        this.initialize();

        if (CustomGameDAO.getCurrentUser() != null) {

            userAvatar = new CustomizedJImage("src/user_data/" /*+ CustomGameDAO.getCurrentUser().getUserName()*/
                    + "/avatar.png", PREFFERED_AVATAR_SIZE, PREFFERED_AVATAR_SIZE);
            userNameLabel.setText(CustomGameDAO.getCurrentUser().getUserName());
            userNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
            userAvatar.setLocation(ChildrenOfTime.PREFERRED_WIDTH - userAvatar.getWidth() / 2 - ELEMENT_GAP - userNameLabel.getWidth() / 2,
                    ELEMENT_GAP);
            userNameLabel.setLocation(ChildrenOfTime.PREFERRED_WIDTH - CustomizedJLabel.LABEL_WIDTH - ELEMENT_GAP,
                    userAvatar.getHeight() + ELEMENT_GAP);
        }

        this.add(userNameLabel);
        this.add(userAvatar);
    }

    private void initialize() {

        JPanel westPanel = new JPanel();
        westPanel.setLayout(new BoxLayout(westPanel, BoxLayout.PAGE_AXIS));
        westPanel.setBackground(ChildrenOfTime.GREY);

        JPanel eastPanel = new JPanel();
        eastPanel.setLayout(new BoxLayout(eastPanel, BoxLayout.PAGE_AXIS));
        eastPanel.setBackground(ChildrenOfTime.GREY);

        for (Warrior warrior : warriors) {
            westPanel.add(new WarriorStatDisplayer(warrior));
        }

        for (Warrior warrior : battle.getDefualtFoes()) {
            eastPanel.add(new WarriorStatDisplayer(warrior));
        }
    }


    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    }
}
