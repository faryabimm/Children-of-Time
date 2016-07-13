package com.childrenOfTime.gui.notification;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.LinearGradientPaint;
import java.awt.Toolkit;
import javax.swing.*;

public class NotificationPopup extends JDialog {

    public static final Color NOTIFICATION_BORDER_COLOR_BAD = Color.red;
    public static final Color NOTIFICATION_BORDER_COLOR_NORMAL = Color.white;
    public static final Color NOTIFICATION_BORDER_COLOR_GOOD = Color.green;
    public static final Color NOTIFICATION_BACKGROUND_COLOR = new Color(48, 48, 48);
    public static final Color NOTIFICATION_FONT_COLOR = Color.white;

    public static final int NOTIFICATION_POPUP_WIDTH = 600;
    public static final int NOTIFICATION_POPUP_HEIGHT = 50;
    public static final int NOTIFICATION_DELAY_TIME = 5000;

    private final LinearGradientPaint lpg;

    private Color borderColor;
    private NotificationType type;

    public NotificationPopup(NotificationType type) {

        this.type = type;

        switch (type) {
            case GOOD:
                borderColor = NOTIFICATION_BORDER_COLOR_GOOD;
                break;
            case BAD:
                borderColor = NOTIFICATION_BORDER_COLOR_BAD;
                break;
            case NORMAL:
                borderColor = NOTIFICATION_BORDER_COLOR_NORMAL;
                break;
        }

        setUndecorated(true);
        setSize(NOTIFICATION_POPUP_WIDTH, NOTIFICATION_POPUP_HEIGHT);
        // size of the screen
        final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        // height of the task bar
        final Insets scnMax = Toolkit.getDefaultToolkit().getScreenInsets(getGraphicsConfiguration());
        final int taskBarSize = scnMax.bottom;

        setLocation((screenSize.width - getWidth()) / 2, screenSize.height - taskBarSize - getHeight());

        // background paint
        lpg = new LinearGradientPaint(0, 0, 0, getHeight() / 2,
                new float[]{0f, 0.3f, 1f}, new Color[]
                {NOTIFICATION_BACKGROUND_COLOR, NOTIFICATION_BACKGROUND_COLOR,
                        NOTIFICATION_BACKGROUND_COLOR});

        // blue background panel
        setContentPane(new BackgroundPanel());
    }

    private class BackgroundPanel extends JPanel {
        public BackgroundPanel() {
            setOpaque(true);
        }

        @Override
        protected void paintComponent(final Graphics g) {
            final Graphics2D g2d = (Graphics2D) g;
            // background
            g2d.setPaint(lpg);
            g2d.fillRect(1, 1, getWidth() - 2, getHeight() - 2);
            g2d.setColor(borderColor); //border color

            // border
            g2d.drawRect(0, 0, getWidth() - 1, getHeight() - 1);
        }
    }
}