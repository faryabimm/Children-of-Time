package com.childrenOfTime.gui.customGame;

import com.childrenOfTime.cgd.CustomGameDAO;
import com.childrenOfTime.gui.customizedElements.CustomizedJButton;
import com.childrenOfTime.gui.customizedElements.CustomizedJImage;
import com.childrenOfTime.gui.customizedElements.MenuScreenPanel;
import com.childrenOfTime.model.ChildrenOfTime;
import com.childrenOfTime.utilities.GUIUtils;

import javax.swing.*;
import javax.swing.event.MouseInputAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Created by mohammadmahdi on 7/8/16.
 */
public class customScenarioBuilderPanel extends MenuScreenPanel {

    public static final int NUMBER_OF_MAP_ROWS = 11;
    public static final int NUMBER_OF_MAP_COLUMNS = 14;
    public static final int MAP_CELL_DIMENTION = 50;
    public static final int BORDER_GAP = 25;


    @Override
    public void initialize() {
        CustomizedJImage map[][] = new CustomizedJImage[NUMBER_OF_MAP_COLUMNS][NUMBER_OF_MAP_ROWS];

        for (int i = 0; i < NUMBER_OF_MAP_COLUMNS; i++) {
            for (int j = 0; j < NUMBER_OF_MAP_ROWS; j++) {
                map[i][j] = new CustomizedJImage(CustomGameDAO.textures.get("ground" + GUIUtils.randomInt(1, 3)),MAP_CELL_DIMENTION,MAP_CELL_DIMENTION);
                map[i][j].setLocation(BORDER_GAP + i*MAP_CELL_DIMENTION, BORDER_GAP+j*MAP_CELL_DIMENTION);
                this.add(map[i][j]);
                int m = i;
                int n = j;
                map[i][j].addMouseListener(new MouseListener() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        System.out.println("click");
                    }
                    @Override
                    public void mousePressed(MouseEvent e) {}
                    @Override
                    public void mouseReleased(MouseEvent e) {}
                    @Override
                    public void mouseEntered(MouseEvent e) {}
                    @Override
                    public void mouseExited(MouseEvent e) {}
                });
            }
        }

        JButton save = new CustomizedJButton("Save Scenario");
        JButton cancel = new CustomizedJButton("Discard Changes");

        this.add(cancel);
        this.add(save);

        cancel.setLocation(ChildrenOfTime.PREFERRED_WIDTH - ELEMENT_GAP - CustomizedJButton.BUTTON_WIDTH,
                ChildrenOfTime.PREFERRED_HEIGHT - CustomizedJButton.BUTTON_HEIGHT - ELEMENT_GAP);
        save.setLocation(ChildrenOfTime.PREFERRED_WIDTH - ELEMENT_GAP - CustomizedJButton.BUTTON_WIDTH,
                ChildrenOfTime.PREFERRED_HEIGHT - 2*CustomizedJButton.BUTTON_HEIGHT - 2*ELEMENT_GAP);
        cancel.addActionListener(e -> ChildrenOfTime.changeContentPane(new CusomGameEditorMenu()));
        emerge();

    }
}
