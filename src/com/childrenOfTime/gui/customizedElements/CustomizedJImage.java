package com.childrenOfTime.gui.customizedElements;

import com.childrenOfTime.controller.GameEngine;


import java.awt.*;

import javax.swing.*;


/**
 * Created by mohammadmahdi on 7/8/16.
 */
public class CustomizedJImage extends JLabel {

    Image image;
    int width;
    int height;

    public CustomizedJImage(String imagePath, int width, int height) {
        this(GameEngine.DEFAULT_TOOLKIT.getImage(imagePath),width,height);
    }
    public CustomizedJImage(Image image, int width, int height) {
        super(new ImageIcon(image.getScaledInstance(width,height,0)));
        this.image = image.getScaledInstance(width,height,0);
        this.setPreferredSize(new Dimension(width,height));
        this.width = width;
        this.height = height;
        this.setBorder(BorderFactory.createEmptyBorder(5 , 5, 5, 5));
    }

    @Override
    public int getWidth() {
        return width;
    }
    @Override
    public int getHeight() {
        return height;
    }

    public CustomizedJImage() {

    }

}
