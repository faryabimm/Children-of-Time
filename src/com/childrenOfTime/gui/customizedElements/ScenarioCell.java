package com.childrenOfTime.gui.customizedElements;

import com.childrenOfTime.cgd.CustomGameDAO;
import com.childrenOfTime.gui.customGame.CustomScenarioBuilderPanel;
import com.childrenOfTime.model.Battle;
import com.childrenOfTime.model.Store;
import com.childrenOfTime.utilities.GUIUtils;

import javax.swing.*;
import java.awt.*;
import java.io.Serializable;

/**
 * Created by mohammadmahdi on 7/8/16.
 */
public class ScenarioCell extends JLabel implements Serializable {


    public static final int SCENARIO_CELL_DIMENTION = CustomScenarioBuilderPanel.MAP_CELL_DIMENTION;

    private ImageIcon icon;
    private int i;
    private int j;
    private Battle battle = null;
    private Store store;
    private ScenarioCellType cellType = ScenarioCellType.GROUND;

    public void reset() {
        this.battle = null;
        this.store = null;
        this.cellType = ScenarioCellType.GROUND;
        setIcon(CustomGameDAO.textures.get("ground1"));
    }

    public ScenarioCellType getCellType() {
        return cellType;
    }

    public void setCellType(ScenarioCellType cellType) {
        this.cellType = cellType;
    }
    public ScenarioCell(String cellType, int i, int j) {

        super(GUIUtils.getScaledIcon(CustomGameDAO.textures.get(cellType),
                SCENARIO_CELL_DIMENTION, SCENARIO_CELL_DIMENTION, 0));

        this.icon = GUIUtils.getScaledIcon(CustomGameDAO.textures.get(cellType),
                SCENARIO_CELL_DIMENTION,SCENARIO_CELL_DIMENTION,0);

        this.setPreferredSize(new Dimension(SCENARIO_CELL_DIMENTION,SCENARIO_CELL_DIMENTION));
        this.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        this.i = i;
        this.j = j;
    }

    public int getJ() {
        return j;
    }

    public void setJ(int j) {
        this.j = j;
    }

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    @Override
    public ImageIcon getIcon() {
        return icon;
    }

    public void setIcon(ImageIcon icon) {
        this.icon = icon;
        super.setIcon(icon);
    }
}
