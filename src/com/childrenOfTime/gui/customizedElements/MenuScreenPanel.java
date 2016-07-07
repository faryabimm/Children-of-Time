package com.childrenOfTime.gui.customizedElements;

import com.childrenOfTime.gui.LoadingScreenPanel;
import com.childrenOfTime.model.ChildrenOfTime;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by mohammadmahdi on 7/7/16.
 */
public abstract class MenuScreenPanel extends JPanel {
    public static final int ELEMENT_GAP = 20;

    protected float pageOpacity = 0f;
    protected Timer timer_emerge = new Timer(LoadingScreenPanel.TIMER_STARTING_DELAY/3, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            pageOpacity += LoadingScreenPanel.LOGO_OPACITY_INCREMENT;
            if (pageOpacity >= 1) {
                pageOpacity = 1;
                timer_emerge.stop();
            }
            repaint();
        }
    });
    protected Timer timer_fade   = new Timer(LoadingScreenPanel.TIMER_STARTING_DELAY/3, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            pageOpacity -= LoadingScreenPanel.LOGO_OPACITY_INCREMENT;
            if (pageOpacity < 0) {
                pageOpacity = 0;
                timer_fade.stop();
            }
            repaint();
        }
    });

    public void emerge() {
        timer_emerge.start();
    }
    public void fade() {
        if (!timer_emerge.isRunning()) {
            timer_fade.start();
        }
    }
    public MenuScreenPanel() {
        this.setLayout(null);
        this.setPreferredSize(ChildrenOfTime.PREFERRED_DIMENSION);
        this.initialize();
    }
    public abstract void initialize();
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(ChildrenOfTime.GREY);
        g2d.fillRect(0,0,ChildrenOfTime.PREFERRED_WIDTH,ChildrenOfTime.PREFERRED_HEIGHT);
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, pageOpacity));
    }

}
