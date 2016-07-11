package com.childrenOfTime.gui.fillForms;

import com.childrenOfTime.cgd.CustomGameDAO;
import com.childrenOfTime.gui.customizedElements.Scenario;
import com.childrenOfTime.model.Battle;

import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class SingleScenarioChooserDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JComboBox comboBox1;


    private Scenario selectedScenario;

    public SingleScenarioChooserDialog(Scenario selectedScenario) {

        this.selectedScenario = selectedScenario;


        ArrayList<String> scenarioNames = CustomGameDAO.currentUserCustomScenarios.stream().map(Scenario::getName).
                collect(Collectors.toCollection(ArrayList::new));

        comboBox1.setModel(new DefaultComboBoxModel(scenarioNames.toArray()));


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


        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    private void onOK() {
// add your code here

        selectedScenario = CustomGameDAO.currentUserCustomScenarios.get(comboBox1.getSelectedIndex());
        dispose();
    }

    private void onCancel() {
// add your code here if necessary
        dispose();
    }

    public static void main(String[] args) {
//        SingleScenarioChooserDialog dialog = new SingleScenarioChooserDialog();
        System.exit(0);
    }
}
