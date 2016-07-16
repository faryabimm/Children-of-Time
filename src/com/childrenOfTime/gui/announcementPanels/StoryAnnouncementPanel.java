package com.childrenOfTime.gui.announcementPanels;

import com.childrenOfTime.controller.GameEngine;
import com.childrenOfTime.gui.MainMenuScreenPanel;
import com.childrenOfTime.gui.customizedElements.CustomizedJLabel;
import com.childrenOfTime.gui.customizedElements.MenuScreenPanel;
import com.childrenOfTime.gui.customizedListeners.KeyTypeListener;
import com.childrenOfTime.model.ChildrenOfTime;
import com.childrenOfTime.model.Story;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * Created by mohammadmahdi on 7/16/16.
 */
public class StoryAnnouncementPanel extends MenuScreenPanel {

    public StoryAnnouncementPanel(Story story) {

        CustomizedJLabel storyLabel = new CustomizedJLabel("<html>" + story.getStory() + "</html>");
        storyLabel.setSize(ChildrenOfTime.PREFERRED_DIMENSION);
        storyLabel.setVerticalAlignment(SwingConstants.CENTER);
        storyLabel.setHorizontalAlignment(SwingConstants.CENTER);
        storyLabel.setForeground(Color.darkGray);
        storyLabel.setFont(new Font(null, Font.BOLD, 22));
        this.add(storyLabel);
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

        g2d.drawImage(GameEngine.DEFAULT_TOOLKIT.getImage("src/ui/background/story.png"), 0, 0, ChildrenOfTime.PREFERRED_WIDTH, ChildrenOfTime.PREFERRED_HEIGHT, this);
        this.addKeyListener(new KeyTypeListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                ChildrenOfTime.changeContentPane(new MainMenuScreenPanel());
            }
        });
    }
}
