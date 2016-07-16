package com.childrenOfTime.gui.announcementPanels;

import com.childrenOfTime.controller.GameEngine;
import com.childrenOfTime.gui.MainMenuScreenPanel;
import com.childrenOfTime.gui.customizedElements.MenuScreenPanel;
import com.childrenOfTime.gui.customizedListeners.KeyTypeListener;
import com.childrenOfTime.model.ChildrenOfTime;
import com.sun.prism.impl.paint.PaintUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Created by mohammadmahdi on 7/14/16.
 */
public class GameOverAnnouncementPanel extends MenuScreenPanel {


    ModalAnnouncer announcer;

    public GameOverAnnouncementPanel(ModalAnnouncer announcer) {
        this.announcer = announcer;
    }

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
        this.addKeyListener(new KeyTypeListener() {
            @Override
            public void keyTyped(KeyEvent e) {

                announcer.dispose();
                System.exit(0);
            }
        });
    }
}
