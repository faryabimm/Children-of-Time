package com.childrenOfTime.gui.multiPlayer;

import com.childrenOfTime.cgd.CustomGameDAO;
import com.childrenOfTime.gui.MainMenuScreenPanel;
import com.childrenOfTime.gui.customizedElements.CustomizedJButton;
import com.childrenOfTime.gui.customizedElements.MenuScreenPanel;
import com.childrenOfTime.gui.customizedListeners.KeyTypeListener;
import com.childrenOfTime.gui.fillForms.MultiplayerChatDialog;
import com.childrenOfTime.gui.notification.NotificationType;
import com.childrenOfTime.model.ChildrenOfTime;
import com.childrenOfTime.model.MultiPlayer.MultiPlayer;
import com.childrenOfTime.model.Player;
import com.childrenOfTime.model.PlayerType;
import com.childrenOfTime.utilities.GUIUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

/**
 * Created by mohammadmahdi on 7/13/16.
 */
public class MutilpllayerLobbyMenu extends MenuScreenPanel {

    MultiplayerChatDialog chatDialog;
    private boolean isHost = true;

    Thread messageServieceDaemon;

    public MutilpllayerLobbyMenu(boolean isHost) {
        this.isHost = isHost;
    }

    @Override
    public void initialize() {

        MultiPlayer.getInstacne().setThisPlayer(new Player(CustomGameDAO.getCurrentUser().getUserName(), PlayerType.Human));

        KeyTypeListener listener = new KeyTypeListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                chatDialog.setVisible(true);
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


        closeTheServer.addActionListener(e -> {
            ChildrenOfTime.changeContentPane(new MainMenuScreenPanel());
            chatDialog.dispose();
        });

        openChatDialog.addActionListener(e -> openChatWindow());

        this.addKeyListener(listener);

        chatDialog = new MultiplayerChatDialog(CustomGameDAO.getCurrentUser().getUserName(), isHost);
        chatDialog.setVisible(false);



        messageServieceDaemon = new Thread(() -> {
            while (true) {
                String message = MultiPlayer.getInstacne().getRecievedMessage();
                chatDialog.importMessage(message);
            }
        });


        messageServieceDaemon.setDaemon(true);
        messageServieceDaemon.setPriority(Thread.NORM_PRIORITY + 2);
        messageServieceDaemon.start();

        closeTheServer.addKeyListener(listener);
        openChatDialog.addKeyListener(listener);
        initiateABattle.addKeyListener(listener);

        closeTheServer.addActionListener(e -> MultiPlayer.getInstacne().forceStopConnection());
        emerge();
    }

    private void openChatWindow() {
        chatDialog.setVisible(true);
    }

}
