package com.childrenOfTime.gui.announcementPanels;

import com.childrenOfTime.controller.GameEngine;
import com.childrenOfTime.gui.customizedElements.MenuScreenPanel;
import com.childrenOfTime.model.ChildrenOfTime;
import com.sun.prism.impl.paint.PaintUtil;

import javax.swing.*;
import java.awt.*;

/**
 * Created by mohammadmahdi on 7/14/16.
 */
public class GameOverAnnouncementPanel extends MenuScreenPanel {


    @Override
    public void initialize() {


        emerge();
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(ChildrenOfTime.GREY);
        g2d.fillRect(0, 0, ChildrenOfTime.PREFERRED_WIDTH, ChildrenOfTime.PREFERRED_HEIGHT);


        g2d.drawImage(GameEngine.DEFAULT_TOOLKIT.getImage("src/ui/background/game_over.png"), 0, 0, ChildrenOfTime.PREFERRED_WIDTH, ChildrenOfTime.PREFERRED_HEIGHT, this);
//        PaintUtils.drawBevel(g, new Rectangle(200,200,500,500));
//        PaintUtils.paintFocus(g2d,new Rectangle(100,100,400,400),10);
//        PaintUtils.

    }


    public static void main(String[] args) {
        JFrame frame = new JFrame("TEST");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        MenuScreenPanel panel = new GameOverAnnouncementPanel();

        frame.setContentPane(new GameOverAnnouncementPanel());

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        panel.fade();


    }

}
