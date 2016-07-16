package com.childrenOfTime.gui.fillForms;

import com.childrenOfTime.cgd.CustomGameDAO;
import com.childrenOfTime.gui.customizedElements.ScenarioCell;
import com.childrenOfTime.gui.customizedElements.ScenarioCellType;
import com.childrenOfTime.gui.customizedListeners.KeyTypeListener;
import com.childrenOfTime.gui.fillForms.dataHolders.BattleWrapper;
import com.childrenOfTime.gui.fillForms.dataHolders.StoreWrapper;
import com.childrenOfTime.gui.fillForms.dataHolders.StoryWrapper;
import com.childrenOfTime.model.Battle;
import com.childrenOfTime.model.Store;
import com.childrenOfTime.utilities.GUIUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class BlockPickerDialog extends JDialog {

    private static final String[] grounds = {
            "ground1",
            "ground2",
            "ground3"
    };

    private static final String[] doorCondition = {
            "down_up",
            "up_down",
            "left_right",
            "right_left"
    };

    // outputTextFiled.setText(grounds[groundComboBox.getSelectedIndex()]);


    private final ButtonGroup radioGroup;
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JRadioButton radioButton1;
    private JRadioButton radioButton2;
    private JRadioButton radioButton3;
    private JRadioButton radioButton4;
    private JRadioButton radioButton5;
    private JRadioButton radioButton6;
    private JRadioButton radioButton7;
    private JRadioButton radioButton8;
    private JComboBox comboBox1;
    private JComboBox comboBox2;

    ScenarioCell targetCell;

    public BlockPickerDialog(ScenarioCell targetCell) {

        radioGroup = new ButtonGroup();
        radioGroup.add(radioButton1);
        radioGroup.add(radioButton2);
        radioGroup.add(radioButton3);
        radioGroup.add(radioButton4);
        radioGroup.add(radioButton5);
        radioGroup.add(radioButton6);
        radioGroup.add(radioButton7);
        radioGroup.add(radioButton8);


        this.targetCell = targetCell;
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        buttonOK.addActionListener(e -> onOK());
        buttonCancel.addActionListener(e -> onCancel());
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });
        contentPane.registerKeyboardAction(e -> onCancel(), KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

        this.addKeyListener(new KeyTypeListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                switch (e.getKeyChar()) {
                    case 'w':
                        targetCell.reset();
                        targetCell.setIcon(GUIUtils.getScaledIcon(CustomGameDAO.textures.get("wall"), ScenarioCell.SCENARIO_CELL_DIMENTION, ScenarioCell.SCENARIO_CELL_DIMENTION, 0));
                        targetCell.setCellType(ScenarioCellType.WALL);
                        dispose();
                        break;
                    case 'g':
                        targetCell.reset();
                        targetCell.setIcon(GUIUtils.getScaledIcon(CustomGameDAO.textures.get("ground1"), ScenarioCell.SCENARIO_CELL_DIMENTION, ScenarioCell.SCENARIO_CELL_DIMENTION, 0));
                        targetCell.setCellType(ScenarioCellType.GROUND);
                        dispose();
                        break;
                }
            }
        });

        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);

    }

    private void onOK() {
        if (radioButton3.isSelected()) {
            targetCell.reset();
            targetCell.setIcon(GUIUtils.getScaledIcon(CustomGameDAO.textures.get("wall"), ScenarioCell.SCENARIO_CELL_DIMENTION, ScenarioCell.SCENARIO_CELL_DIMENTION, 0));
            targetCell.setCellType(ScenarioCellType.WALL);
        }
        if (radioButton4.isSelected()) {
            targetCell.reset();
            targetCell.setIcon(GUIUtils.getScaledIcon(CustomGameDAO.textures.get("store"), ScenarioCell.SCENARIO_CELL_DIMENTION, ScenarioCell.SCENARIO_CELL_DIMENTION, 0));
            targetCell.setCellType(ScenarioCellType.STORE);
            StoreWrapper storeWrapper = new StoreWrapper();
            new SingleStoreChooserDialog(storeWrapper.store);
            targetCell.setStore(storeWrapper.store);
        }
        if (radioButton5.isSelected()) {
            targetCell.reset();
            targetCell.setIcon(GUIUtils.getScaledIcon(CustomGameDAO.textures.get("upgrade"), ScenarioCell.SCENARIO_CELL_DIMENTION, ScenarioCell.SCENARIO_CELL_DIMENTION, 0));
            targetCell.setCellType(ScenarioCellType.UPGRADEPLACE);
        }
        if (radioButton6.isSelected()) {
            targetCell.reset();
            targetCell.setIcon(GUIUtils.getScaledIcon(CustomGameDAO.textures.get("story"), ScenarioCell.SCENARIO_CELL_DIMENTION, ScenarioCell.SCENARIO_CELL_DIMENTION, 0));
            targetCell.setCellType(ScenarioCellType.STORY);
            StoryWrapper storyWrapper = new StoryWrapper();
            new StoryChooserDialog(storyWrapper);
            targetCell.setStory(storyWrapper.story);
        }
        if (radioButton7.isSelected()) {
            targetCell.reset();
            targetCell.setIcon(GUIUtils.getScaledIcon(CustomGameDAO.textures.get("battle"), ScenarioCell.SCENARIO_CELL_DIMENTION, ScenarioCell.SCENARIO_CELL_DIMENTION, 0));
            targetCell.setCellType(ScenarioCellType.BATTLE);

            BattleWrapper battleWrapper = new BattleWrapper();
            new SingleBattleChooserDialog(battleWrapper);
            targetCell.setBattle(battleWrapper.battle);
        }
        if (radioButton8.isSelected()) {
            targetCell.reset();
            targetCell.setIcon(GUIUtils.getScaledIcon(CustomGameDAO.textures.get("boss"), ScenarioCell.SCENARIO_CELL_DIMENTION, ScenarioCell.SCENARIO_CELL_DIMENTION, 0));
            targetCell.setCellType(ScenarioCellType.BOSS);

            BattleWrapper battleWrapper = new BattleWrapper();
            new SingleBattleChooserDialog(battleWrapper);
            targetCell.setBattle(battleWrapper.battle);
        }

        if(radioButton1.isSelected()) {
            switch (comboBox1.getSelectedIndex()) {
                case 0:
                    targetCell.reset();
                    targetCell.setIcon(GUIUtils.getScaledIcon(CustomGameDAO.textures.get("ground1"), ScenarioCell.SCENARIO_CELL_DIMENTION, ScenarioCell.SCENARIO_CELL_DIMENTION, 0));
                    targetCell.setCellType(ScenarioCellType.GROUND);
                    break;
                case 1:
                    targetCell.reset();
                    targetCell.setIcon(GUIUtils.getScaledIcon(CustomGameDAO.textures.get("ground2"), ScenarioCell.SCENARIO_CELL_DIMENTION, ScenarioCell.SCENARIO_CELL_DIMENTION, 0));
                    targetCell.setCellType(ScenarioCellType.GROUND);
                    break;
                case 2:
                    targetCell.reset();
                    targetCell.setIcon(GUIUtils.getScaledIcon(CustomGameDAO.textures.get("ground3"), ScenarioCell.SCENARIO_CELL_DIMENTION, ScenarioCell.SCENARIO_CELL_DIMENTION, 0));
                    targetCell.setCellType(ScenarioCellType.GROUND);
                    break;
            }
        }
        if(radioButton2.isSelected()) {
            switch (comboBox2.getSelectedIndex()) {
                case 0:
                    targetCell.reset();
                    targetCell.setIcon(GUIUtils.getScaledIcon(CustomGameDAO.textures.get("door_down_up"), ScenarioCell.SCENARIO_CELL_DIMENTION, ScenarioCell.SCENARIO_CELL_DIMENTION, 0));
                    targetCell.setCellType(ScenarioCellType.DOOR_DOWN_UP);
                    break;
                case 1:
                    targetCell.reset();
                    targetCell.setIcon(GUIUtils.getScaledIcon(CustomGameDAO.textures.get("door_up_down"), ScenarioCell.SCENARIO_CELL_DIMENTION, ScenarioCell.SCENARIO_CELL_DIMENTION, 0));
                    targetCell.setCellType(ScenarioCellType.DOOR_UP_DOWN);
                    break;
                case 2:
                    targetCell.reset();
                    targetCell.setIcon(GUIUtils.getScaledIcon(CustomGameDAO.textures.get("door_left_right"), ScenarioCell.SCENARIO_CELL_DIMENTION, ScenarioCell.SCENARIO_CELL_DIMENTION, 0));
                    targetCell.setCellType(ScenarioCellType.DOOR_LEFT_RIGHT);
                    break;
                case 3:
                    targetCell.reset();
                    targetCell.setIcon(GUIUtils.getScaledIcon(CustomGameDAO.textures.get("door_right_left"), ScenarioCell.SCENARIO_CELL_DIMENTION, ScenarioCell.SCENARIO_CELL_DIMENTION, 0));
                    targetCell.setCellType(ScenarioCellType.DOOR_RIGHT_LEFT);
                    break;
            }
        }
        dispose();
    }

    private void onCancel() {
// add your code here if necessary
        dispose();
    }

    public static void main(String[] args) {
//        BlockPickerDialog dialog = new BlockPickerDialog(targetCell);

        System.exit(0);
    }
}
