package com.childrenOfTime.gui.customizedListeners;

import com.childrenOfTime.gui.customizedElements.PlayerIndicator;
import com.childrenOfTime.gui.singlePlayer.SinglePlayerGame;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by mohammadmahdi on 7/12/16.
 */
public class MapScreenListener implements KeyListener {

    private PlayerIndicator indicator;
    private SinglePlayerGame gamePanel;


    public MapScreenListener(PlayerIndicator indicator, SinglePlayerGame gamePanel) {
        this.indicator = indicator;
        this.gamePanel = gamePanel;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        switch (e.getKeyChar()) {
            case 'a':
                System.out.println("LEFT arrow key");
                indicator.moveLeft();
                gamePanel.repaint();
                break;
            case 'd':
                System.out.println("RIGHT arrow key");
                indicator.moveRight();
                gamePanel.repaint();
                break;
            case 's':
                System.out.println("DOWN arrow key");
                indicator.moveDown();
                gamePanel.repaint();
                break;
            case 'w':
                System.out.println("UP arrow key");
                indicator.moveUp();
                gamePanel.repaint();
                break;
            case ' ':
                System.out.println("Interact");
                indicator.interact();
                gamePanel.repaint();
                break;
        }

    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}
