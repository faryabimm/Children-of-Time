package com.childrenOfTime.gui.customizedElements;

import com.childrenOfTime.model.ChildrenOfTime;

import javax.swing.*;
import java.awt.*;

/**
 * Created by mohammadmahdi on 7/7/16.
 */
public class CustomizedJButton extends JButton {

    public static int BUTTON_WIDTH = 200;
    public static int BUTTON_HEIGHT = 60;
    public static Dimension BUTTON_DIMENTION = new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT);

    public CustomizedJButton(String title) {

        super(title);
        this.setFont(ChildrenOfTime.TIZA_FONT);
        this.setBackground(Color.YELLOW);
        this.setVerticalAlignment(SwingConstants.CENTER);
        this.setSize(BUTTON_DIMENTION);
        this.setBorder(BorderFactory.createEmptyBorder(20 , 20, 20, 20));
    }
}
