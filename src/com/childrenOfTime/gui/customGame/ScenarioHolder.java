package com.childrenOfTime.gui.customGame;

import com.childrenOfTime.gui.customizedElements.Scenario;
import com.childrenOfTime.gui.customizedElements.ScenarioCell;
import com.childrenOfTime.model.ChildrenOfTime;
import com.childrenOfTime.utilities.GUIUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Created by mohammadmahdi on 7/8/16.
 */

public class ScenarioHolder extends JPanel {
    public ScenarioHolder(CustomScenarioBuilderPanel parent) {
        this.setBackground(ChildrenOfTime.GREY);
        this.setLayout(new GridLayout(CustomScenarioBuilderPanel.NUMBER_OF_MAP_ROWS,
                CustomScenarioBuilderPanel.NUMBER_OF_MAP_COLUMNS));

        MouseListener listener = new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                ScenarioCell targetCell = (ScenarioCell) e.getComponent();
                System.out.println(targetCell.getI() + " , " + targetCell.getJ());
                CustomScenarioBuilderPanel.cellClicked(targetCell);
            }
            @Override
            public void mousePressed(MouseEvent e) {}
            @Override
            public void mouseReleased(MouseEvent e) {}
            @Override
            public void mouseEntered(MouseEvent e) {}
            @Override
            public void mouseExited(MouseEvent e) {}
        };

        JLabel label;

        for (int i = 0; i < CustomScenarioBuilderPanel.NUMBER_OF_MAP_ROWS; i++) {
            for (int j = 0; j < CustomScenarioBuilderPanel.NUMBER_OF_MAP_COLUMNS; j++) {
                label = new ScenarioCell("ground" + GUIUtils.randomInt(1, 3),i,j);
                label.addMouseListener(listener);
                this.add(label);
                parent.newScenario.addAnewCell((ScenarioCell) label);
            }
        }
    }

    public Dimension getDimension() {
        return new Dimension(this.getWidth(),this.getHeight());
    }


    public static void main(String[] args) {
        JFrame frame = new JFrame("Log In ...");
        frame.setResizable(false);
//        frame.setContentPane(new ScenarioHolder(newScenario));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
