package com.childrenOfTime.gui.customizedElements;

import javax.swing.*;
import java.awt.*;

/**
 * Created by mohammadmahdi on 7/8/16.
 */
public class CustomizedJLabel extends JLabel {

    public static int LABEL_WIDTH = 200;
    public static int LABEL_HEIGHT = 60;
    public static Dimension LABEL_DIMENTION = new Dimension(LABEL_WIDTH, LABEL_HEIGHT);


    public CustomizedJLabel(String text) {
        super(text);
        this.setForeground(Color.white);
        this.setVerticalAlignment(SwingConstants.CENTER);
        this.setSize(LABEL_DIMENTION);
        this.setBorder(BorderFactory.createEmptyBorder(20 , 20, 20, 20));
    }
}
