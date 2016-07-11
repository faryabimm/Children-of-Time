package com.childrenOfTime.gui.fillForms;

import com.childrenOfTime.gui.SinglePlayerMenuScreenPanel;
import com.childrenOfTime.gui.fillForms.dataHolders.CustomScenarioInfoHolder;
import com.childrenOfTime.model.ChildrenOfTime;

import javax.swing.*;
import java.awt.event.*;

public class CustomScenarioSelectorDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JCheckBox noScenarioSelectedCheckBox;
    private JCheckBox noWarriorSelectedCheckBox;
    private JButton customScenariosButton;
    private JButton chooseWarriorsButton;

    private CustomScenarioInfoHolder infoHolder;

    public CustomScenarioSelectorDialog(CustomScenarioInfoHolder infoHolder) {

        this.infoHolder = infoHolder;

        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

// call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

// call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);


        customScenariosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new SingleScenarioChooserDialog(infoHolder.playingScenario);
                if (infoHolder.playingScenario != null) {
                    noScenarioSelectedCheckBox.setSelected(true);
                    noScenarioSelectedCheckBox.setText("selected: " + infoHolder.playingScenario.getName());
                }
                if (noScenarioSelectedCheckBox.isSelected() && noWarriorSelectedCheckBox.isSelected())
                    buttonOK.setEnabled(true);
            }
        });
        chooseWarriorsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new WarriorChooserDialog(infoHolder.playerWarriors);

                if (infoHolder.playerWarriors.size() != 0) {
                    noWarriorSelectedCheckBox.setSelected(true);
                    noWarriorSelectedCheckBox.setText("selected " + infoHolder.playerWarriors.size() + " Warriors");
                }


                if (noScenarioSelectedCheckBox.isSelected() && noWarriorSelectedCheckBox.isSelected())
                    buttonOK.setEnabled(true);
            }
        });
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    private void onOK() {
// add your code here
        disposalProcess();
    }

    private void disposalProcess() {
        ChildrenOfTime.changeContentPane(new SinglePlayerMenuScreenPanel());
        dispose();
    }

    private void onCancel() {
// add your code here if necessary
        disposalProcess();
    }

    public static void main(String[] args) {
//        CustomScenarioSelectorDialog dialog = new CustomScenarioSelectorDialog();
        System.exit(0);
    }
}
