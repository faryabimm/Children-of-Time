package com.childrenOfTime.gui.singlePlayer;

import com.childrenOfTime.gui.customGame.CustomGameMenuScreenPanel;
import com.childrenOfTime.gui.customGame.CustomScenarioBuilderPanel;
import com.childrenOfTime.gui.customGame.ScenarioHolder;
import com.childrenOfTime.gui.customizedElements.CustomizedJButton;
import com.childrenOfTime.gui.customizedElements.MenuScreenPanel;
import com.childrenOfTime.gui.customizedElements.Scenario;
import com.childrenOfTime.gui.customizedElements.ScenarioCell;
import com.childrenOfTime.gui.fillForms.CustomScenarioSelectorDialog;
import com.childrenOfTime.gui.fillForms.dataHolders.CustomScenarioInfoHolder;
import com.childrenOfTime.model.ChildrenOfTime;
import com.childrenOfTime.model.Warriors.Warrior;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by mohammadmahdi on 7/12/16.
 */
public class SinglePlayerGame extends MenuScreenPanel {

    private Scenario scenario;
    private ArrayList<Warrior> playerWarriors;

    CustomScenarioInfoHolder infoHolder;

    public SinglePlayerGame(CustomScenarioInfoHolder infoHolder) {
        this.infoHolder = infoHolder;
        initializeCells();
    }


    private void initializeCells() {
        for (int i = 0; i < CustomScenarioBuilderPanel.NUMBER_OF_MAP_COLUMNS; i++) {
            for (int j = 0; j < CustomScenarioBuilderPanel.NUMBER_OF_MAP_ROWS; j++) {
                infoHolder.playingScenario.getIJ(i, j).setLocation(CustomScenarioBuilderPanel.BORDER_GAP + CustomScenarioBuilderPanel.MAP_CELL_DIMENTION * i,
                        CustomScenarioBuilderPanel.BORDER_GAP + CustomScenarioBuilderPanel.MAP_CELL_DIMENTION * j);
                this.add(infoHolder.playingScenario.getIJ(i, j));
            }
        }
    }

    @Override
    public void initialize() {

        JButton saveGame = new CustomizedJButton("Save the game");
        JButton quit = new CustomizedJButton("Quit");

        quit.setBackground(Color.red);
        quit.setForeground(Color.yellow);


        this.add(quit);
        this.add(saveGame);

        quit.setLocation(ChildrenOfTime.PREFERRED_WIDTH - ELEMENT_GAP - CustomizedJButton.BUTTON_WIDTH,
                ChildrenOfTime.PREFERRED_HEIGHT - CustomizedJButton.BUTTON_HEIGHT - ELEMENT_GAP);
        saveGame.setLocation(ChildrenOfTime.PREFERRED_WIDTH - ELEMENT_GAP - CustomizedJButton.BUTTON_WIDTH,
                ChildrenOfTime.PREFERRED_HEIGHT - 2 * CustomizedJButton.BUTTON_HEIGHT - 2 * ELEMENT_GAP);


        quit.addActionListener(e -> ChildrenOfTime.changeContentPane(new SinglePlayerMenuScreenPanel()));


        emerge();
    }
}
