package com.childrenOfTime.gui.singlePlayer;

import com.childrenOfTime.gui.customGame.CustomScenarioBuilderPanel;
import com.childrenOfTime.gui.customizedElements.CustomizedJButton;
import com.childrenOfTime.gui.customizedElements.MenuScreenPanel;
import com.childrenOfTime.gui.customizedElements.PlayerIndicator;
import com.childrenOfTime.gui.customizedElements.Scenario;
import com.childrenOfTime.gui.customizedListeners.MapScreenListener;
import com.childrenOfTime.gui.fillForms.dataHolders.CustomScenarioInfoHolder;
import com.childrenOfTime.model.ChildrenOfTime;
import com.childrenOfTime.model.Warriors.Warrior;
import com.childrenOfTime.utilities.GUIUtils;

import javax.swing.*;
import java.awt.*;

import java.util.ArrayList;

/**
 * Created by mohammadmahdi on 7/12/16.
 */
public class SinglePlayerGame extends MenuScreenPanel {

    private PlayerIndicator indicator;
    private MapScreenListener controller;
    public static SinglePlayerGame lastState;

    CustomScenarioInfoHolder infoHolder;

    public SinglePlayerGame(CustomScenarioInfoHolder infoHolder) {
        lastState = this;
        this.infoHolder = infoHolder;
        indicator = new PlayerIndicator(infoHolder);
        initializeCells();
        controller = new MapScreenListener(indicator, this);
        addKeyListener(controller);
    }
    private void initializeCells() {
        for (int i = 0; i < CustomScenarioBuilderPanel.NUMBER_OF_MAP_COLUMNS; i++) {
            for (int j = 0; j < CustomScenarioBuilderPanel.NUMBER_OF_MAP_ROWS; j++) {
                infoHolder.playingScenario.getIJ(i, j).setLocation(CustomScenarioBuilderPanel.BORDER_GAP + CustomScenarioBuilderPanel.MAP_CELL_DIMENTION * i,
                        CustomScenarioBuilderPanel.BORDER_GAP + CustomScenarioBuilderPanel.MAP_CELL_DIMENTION * j);
            }
        }
    }
    @Override
    public void initialize() {
        JButton saveGame = new CustomizedJButton("Save the game");
        JButton quit = new CustomizedJButton("Quit");
//        JButton up = new CustomizedJButton("UP");
//        JButton down = new CustomizedJButton("DOWN");
//        JButton right = new CustomizedJButton("LEFT");
//        JButton left = new CustomizedJButton("RIGHT");

        quit.setBackground(Color.red);
        quit.setForeground(Color.yellow);

        this.add(quit);
        this.add(saveGame);
//        this.add(up);
//        this.add(down);
//        this.add(left);
//        this.add(right);

        quit.setLocation(ChildrenOfTime.PREFERRED_WIDTH - ELEMENT_GAP - CustomizedJButton.BUTTON_WIDTH,
                ChildrenOfTime.PREFERRED_HEIGHT - CustomizedJButton.BUTTON_HEIGHT - ELEMENT_GAP);
        saveGame.setLocation(ChildrenOfTime.PREFERRED_WIDTH - ELEMENT_GAP - CustomizedJButton.BUTTON_WIDTH,
                ChildrenOfTime.PREFERRED_HEIGHT - 2 * CustomizedJButton.BUTTON_HEIGHT - 2 * ELEMENT_GAP);
//        up.setLocation(ChildrenOfTime.PREFERRED_WIDTH - ELEMENT_GAP - CustomizedJButton.BUTTON_WIDTH,
//                ChildrenOfTime.PREFERRED_HEIGHT - 3 * CustomizedJButton.BUTTON_HEIGHT - 3 * ELEMENT_GAP);
//        down.setLocation(ChildrenOfTime.PREFERRED_WIDTH - ELEMENT_GAP - CustomizedJButton.BUTTON_WIDTH,
//                ChildrenOfTime.PREFERRED_HEIGHT - 4 * CustomizedJButton.BUTTON_HEIGHT - 4 * ELEMENT_GAP);
//        left.setLocation(ChildrenOfTime.PREFERRED_WIDTH - ELEMENT_GAP - CustomizedJButton.BUTTON_WIDTH,
//                ChildrenOfTime.PREFERRED_HEIGHT - 5 * CustomizedJButton.BUTTON_HEIGHT - 5 * ELEMENT_GAP);
//        right.setLocation(ChildrenOfTime.PREFERRED_WIDTH - ELEMENT_GAP - CustomizedJButton.BUTTON_WIDTH,
//                ChildrenOfTime.PREFERRED_HEIGHT - 6 * CustomizedJButton.BUTTON_HEIGHT - 6 * ELEMENT_GAP);


        quit.addActionListener(e -> ChildrenOfTime.changeContentPane(new SinglePlayerMenuScreenPanel()));
//        up.addActionListener(e -> {
//            indicator.moveUp();
//            repaint();
//        });
//        down.addActionListener(e -> {
//            indicator.moveDown();
//            repaint();
//        });
//        left.addActionListener(e -> {
//            indicator.moveLeft();
//            repaint();
//        });
//        right.addActionListener(e -> {
//            indicator.moveRight();
//            repaint();
//        });


        emerge();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        paintMap(g2d);
        paintIndicator(g2d);
    }

    private void paintMap(Graphics2D g2d) {

        for (int i = 0; i < CustomScenarioBuilderPanel.NUMBER_OF_MAP_COLUMNS; i++) {
            for (int j = 0; j < CustomScenarioBuilderPanel.NUMBER_OF_MAP_ROWS; j++) {
                g2d.drawImage(GUIUtils.iconToImage(infoHolder.playingScenario.getIJ(i, j).getIcon()),
                        infoHolder.playingScenario.getIJ(i, j).getX(), infoHolder.playingScenario.getIJ(i, j).getY(), this);
            }
        }
    }

    private void paintIndicator(Graphics2D g2d) {
        g2d.setColor(Color.red);

        g2d.fillRoundRect(indicator.getX(), indicator.getY(), PlayerIndicator.PLAYER_INDICATOR_DIAMETER
                , PlayerIndicator.PLAYER_INDICATOR_DIAMETER, PlayerIndicator.PLAYER_INDICATOR_DIAMETER,
                PlayerIndicator.PLAYER_INDICATOR_DIAMETER);

    }

}
