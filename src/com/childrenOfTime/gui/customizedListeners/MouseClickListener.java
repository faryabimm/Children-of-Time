package com.childrenOfTime.gui.customizedListeners;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Created by mohammadmahdi on 7/13/16.
 */
public class MouseClickListener implements MouseListener {

    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println(e.getComponent().getClass());

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
