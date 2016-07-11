package com.childrenOfTime.gui.customizedElements;

import com.childrenOfTime.gui.customGame.CustomScenarioBuilderPanel;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by mohammadmahdi on 7/11/16.
 */
public class Scenario implements Serializable {

    private ArrayList<ScenarioCell> map = new ArrayList<>(CustomScenarioBuilderPanel.NUMBER_OF_MAP_COLUMNS
            * CustomScenarioBuilderPanel.NUMBER_OF_MAP_ROWS);

    public void addAnewCell(ScenarioCell cell) {
        map.add(cell);
    }

    public ScenarioCell getIJ(int i, int j) {
        int index = CustomScenarioBuilderPanel.NUMBER_OF_MAP_COLUMNS * j + i;
        return map.get(index);
    }


}
