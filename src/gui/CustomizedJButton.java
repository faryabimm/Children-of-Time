package gui;

import com.childrenOfTime.model.ChildrenOfTime;

import javax.swing.*;
import java.awt.*;

/**
 * Created by mohammadmahdi on 7/7/16.
 */
public class CustomizedJButton extends JButton {

    public static int MAIN_MENU_BUTTON_WIDTH = 200;
    public static int MAIN_MENU_BUTTON_HEIGHT = 60;
    public static Dimension MAIN_MENU_BUTTON_DIMENTION = new Dimension(MAIN_MENU_BUTTON_WIDTH,MAIN_MENU_BUTTON_HEIGHT);

    public CustomizedJButton(String title) {

        super(title);
        this.setFont(ChildrenOfTime.TIZA_FONT);
        this.setBackground(Color.YELLOW);
        this.setVerticalAlignment(SwingConstants.CENTER);
        this.setSize(MAIN_MENU_BUTTON_DIMENTION);
        this.setBorder(BorderFactory.createEmptyBorder(20 , 20, 20, 20));
    }
}
