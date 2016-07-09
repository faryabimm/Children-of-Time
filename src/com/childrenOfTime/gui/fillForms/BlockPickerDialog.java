package com.childrenOfTime.gui.fillForms;

import com.childrenOfTime.cgd.CustomGameDAO;
import com.childrenOfTime.gui.customizedElements.ScenarioCell;

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
        this.pack();
        this.setLocationRelativeTo(null);
        buttonOK.addActionListener(e -> onOK());
        buttonCancel.addActionListener(e -> onCancel());
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });
        contentPane.registerKeyboardAction(e -> onCancel(), KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        this.setVisible(true);



    }

    private void onOK() {
        if(radioButton3.isSelected()) targetCell.setIcon(new ImageIcon(CustomGameDAO.textures.get("wall")));
        if(radioButton4.isSelected()) targetCell.setIcon(new ImageIcon(CustomGameDAO.textures.get("store")));
        if(radioButton5.isSelected()) targetCell.setIcon(new ImageIcon(CustomGameDAO.textures.get("upgrade")));
        if(radioButton6.isSelected()) targetCell.setIcon(new ImageIcon(CustomGameDAO.textures.get("story")));
        if(radioButton7.isSelected()) targetCell.setIcon(new ImageIcon(CustomGameDAO.textures.get("battle")));
        if(radioButton8.isSelected()) targetCell.setIcon(new ImageIcon(CustomGameDAO.textures.get("boss")));

        if(radioButton1.isSelected()) {
            switch (comboBox1.getSelectedIndex()) {
                case 0:
                    targetCell.setIcon(new ImageIcon(CustomGameDAO.textures.get("ground1")));
                    break;
                case 1:
                    targetCell.setIcon(new ImageIcon(CustomGameDAO.textures.get("ground2")));
                    break;
                case 2:
                    targetCell.setIcon(new ImageIcon(CustomGameDAO.textures.get("ground3")));
                    break;
            }
        }
        if(radioButton2.isSelected()) {
            switch (comboBox2.getSelectedIndex()) {
                case 0:
                    targetCell.setIcon(new ImageIcon(CustomGameDAO.textures.get("door_down_up")));
                    break;
                case 1:
                    targetCell.setIcon(new ImageIcon(CustomGameDAO.textures.get("door_up_down")));
                    break;
                case 2:
                    targetCell.setIcon(new ImageIcon(CustomGameDAO.textures.get("door_left_right")));
                    break;
                case 3:
                    targetCell.setIcon(new ImageIcon(CustomGameDAO.textures.get("door_right_left")));
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
