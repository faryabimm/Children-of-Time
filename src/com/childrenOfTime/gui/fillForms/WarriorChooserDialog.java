package com.childrenOfTime.gui.fillForms;

import com.childrenOfTime.cgd.CustomGameDAO;
import com.childrenOfTime.model.Equip.Effect;
import com.childrenOfTime.model.Warriors.Warrior;

import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class WarriorChooserDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JComboBox comboBox1;
    private JLabel label1;
    private JButton addWarriorButton;

    private ArrayList<Warrior> selectedWarriors;

    public WarriorChooserDialog(ArrayList<Warrior> selectedWarriors) {
        this.selectedWarriors = selectedWarriors;


        for (Warrior selectedWarrior : selectedWarriors) {
            label1.setText(label1.getText() + " " + selectedWarrior.getName());
        }

        ArrayList<String> warriorNames = CustomGameDAO.currentUserCustomWarriors.stream().map(Warrior::getName).
                collect(Collectors.toCollection(ArrayList::new));

        comboBox1.setModel(new DefaultComboBoxModel(warriorNames.toArray()));


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


        addWarriorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Warrior toAdd = CustomGameDAO.currentUserCustomWarriors.get(comboBox1.getSelectedIndex());
//                if (!selectedWarriors.contains(toAdd)) {
                    selectedWarriors.add(toAdd);
                    label1.setText(label1.getText() + " " + toAdd.getName());
//                }
            }
        });
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    private void onOK() {
// add your code here
        dispose();
    }

    private void onCancel() {
// add your code here if necessary
        dispose();
    }

    public static void main(String[] args) {
//        WarriorChooserDialog dialog = new WarriorChooserDialog();

        System.exit(0);
    }
}
