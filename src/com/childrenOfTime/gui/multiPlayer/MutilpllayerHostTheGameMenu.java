package com.childrenOfTime.gui.multiPlayer;

import com.childrenOfTime.cgd.CustomGameDAO;
import com.childrenOfTime.cgd.User;
import com.childrenOfTime.controller.GameEngine;
import com.childrenOfTime.gui.MainMenuScreenPanel;
import com.childrenOfTime.gui.customGame.CusomGameEditorMenu;
import com.childrenOfTime.gui.customGame.CustomGameMenuScreenPanel;
import com.childrenOfTime.gui.customizedElements.CustomizedJButton;
import com.childrenOfTime.gui.customizedElements.MenuScreenPanel;
import com.childrenOfTime.gui.customizedListeners.KeyTypeListener;
import com.childrenOfTime.gui.fillForms.MultiplayerChatDialog;
import com.childrenOfTime.model.ChildrenOfTime;
import com.childrenOfTime.model.MultiPlayer.MultiPlayer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by mohammadmahdi on 7/13/16.
 */
public class MutilpllayerHostTheGameMenu extends MenuScreenPanel {

    MultiplayerChatDialog chatDialog;


    @Override
    public void initialize() {

        KeyTypeListener listener = new KeyTypeListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (e.getKeyChar() == 'y') {
                    if (chatDialog == null) {
                        chatDialog = new MultiplayerChatDialog(CustomGameDAO.getCurrentUser().getUserName());
                    } else {
                        chatDialog.setVisible(true);
                    }
                }
            }
        };


        JButton initiateABattle = new CustomizedJButton("Start the Battle");
        JButton openChatDialog = new CustomizedJButton("Open Chat Dialog");
        JButton closeTheServer = new CustomizedJButton("Close the Server");


        closeTheServer.setBackground(Color.red);
        closeTheServer.setForeground(Color.yellow);

        this.add(closeTheServer);
        this.add(openChatDialog);
        this.add(initiateABattle);


        closeTheServer.setLocation(ELEMENT_GAP, ChildrenOfTime.PREFERRED_HEIGHT - CustomizedJButton.BUTTON_HEIGHT - ELEMENT_GAP);
        openChatDialog.setLocation(ELEMENT_GAP, ChildrenOfTime.PREFERRED_HEIGHT - 2 * CustomizedJButton.BUTTON_HEIGHT - 2 * ELEMENT_GAP);
        initiateABattle.setLocation(ELEMENT_GAP, ChildrenOfTime.PREFERRED_HEIGHT - 3 * CustomizedJButton.BUTTON_HEIGHT - 3 * ELEMENT_GAP);


        closeTheServer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ChildrenOfTime.changeContentPane(new MainMenuScreenPanel());
                chatDialog.dispose();
            }
        });

        openChatDialog.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        openChatDialog.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (chatDialog == null) {
                    chatDialog = new MultiplayerChatDialog(CustomGameDAO.getCurrentUser().getUserName());
                } else {
                    chatDialog.setVisible(true);
                }
            }
        });


        this.addKeyListener(listener);
        closeTheServer.addKeyListener(listener);
        openChatDialog.addKeyListener(listener);
        initiateABattle.addKeyListener(listener);

        emerge();
    }

}
