package com.childrenOfTime.gui.announcementPanels;

import com.childrenOfTime.gui.customizedListeners.KeyTypeListener;
import com.childrenOfTime.model.ChildrenOfTime;

import javax.swing.*;
import java.awt.event.*;

/**
 * Created by mohammadmahdi on 7/15/16.
 */
public class ModalAnnouncer extends JDialog {

    public void addPanel(JPanel panel) {
        setModal(true);

        setContentPane(panel);

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });
// call onCancel() on ESCAPE
        this.addKeyListener(new KeyTypeListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                onCancel();
            }
        });
        this.setPreferredSize(ChildrenOfTime.PREFERRED_DIMENSION);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.repaint();
    }


    public ModalAnnouncer() {

    }

    private void onCancel() {
// add your code here if necessary
        dispose();
    }
}
