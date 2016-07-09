package com.childrenOfTime.gui.customizedElements;

import com.childrenOfTime.cgd.CustomGameDAO;
import com.childrenOfTime.gui.customGame.CustomScenarioBuilderPanel;

import javax.swing.*;
import java.awt.*;

/**
 * Created by mohammadmahdi on 7/8/16.
 */
public class ScenarioCell extends JLabel {
    Image image;
    public int i;
    int j;


    public static final int SCENARIO_CELL_DIMENTION = CustomScenarioBuilderPanel.MAP_CELL_DIMENTION;

    public ScenarioCell(String cellType, int i, int j) {
        super(new ImageIcon(CustomGameDAO.textures.get(cellType).getScaledInstance(
                SCENARIO_CELL_DIMENTION,SCENARIO_CELL_DIMENTION,0)));
        this.image = CustomGameDAO.textures.get(cellType).getScaledInstance(
                SCENARIO_CELL_DIMENTION,SCENARIO_CELL_DIMENTION,0);

        this.setPreferredSize(new Dimension(SCENARIO_CELL_DIMENTION,SCENARIO_CELL_DIMENTION));
        this.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        this.i = i;
        this.j = j;
    }
}
