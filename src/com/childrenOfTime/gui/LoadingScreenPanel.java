package com.childrenOfTime.gui;

import com.childrenOfTime.controller.GameEngine;
import com.childrenOfTime.model.ChildrenOfTime;

import javax.swing.*;
import java.awt.*;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by mohammadmahdi on 7/7/16.
 */
public class LoadingScreenPanel extends JPanel {

    private float logoOpacity = 0f;
    public static final float LOGO_OPACITY_INCREMENT = 0.01f;
    public static final int TIMER_STARTING_DELAY = 20;

    Image logo = null;
    private Timer timer1 = new Timer(TIMER_STARTING_DELAY, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            logoOpacity += LOGO_OPACITY_INCREMENT;
            if (logoOpacity >= 1) {
                logoOpacity = 1;
                timer1.stop();
                timer2.start();
            }
            repaint();

        }
    });
    private Timer timer2 = new Timer(TIMER_STARTING_DELAY, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            logoOpacity -= LOGO_OPACITY_INCREMENT;
            if (logoOpacity < 0) {
                logoOpacity = 0;
                timer2.stop();
                ChildrenOfTime.showMainMenuScreen();
                return;
            }
            repaint();
        }
    });

    public LoadingScreenPanel () {
        this.setLayout(null);
        this.setPreferredSize(ChildrenOfTime.PREFERRED_DIMENSION);
        this.initialize();
    }
    private void initialize() {

        logo = GameEngine.DEFAULT_TOOLKIT.getImage("src/ui/icon/app_icon.png");
        logo = logo.getScaledInstance(ChildrenOfTime.PREFERRED_WIDTH/3, ChildrenOfTime.PREFERRED_WIDTH/3, 0);
        timer1.start();

    }
    @Override
    protected void paintComponent(Graphics g) {

        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2d.setColor(ChildrenOfTime.GREY);
        g2d.fillRect(0,0,ChildrenOfTime.PREFERRED_WIDTH,ChildrenOfTime.PREFERRED_HEIGHT);

        paintLogo(g2d);
    }
    private void paintLogo(Graphics2D g2d) {
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, logoOpacity));
        g2d.drawImage(logo,(ChildrenOfTime.PREFERRED_WIDTH - logo.getWidth(this))/2 ,
                (ChildrenOfTime.PREFERRED_HEIGHT - logo.getHeight(this))/2  , this);
    }
}
