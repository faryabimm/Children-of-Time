package com.childrenOfTime.gui.fillForms;

import com.childrenOfTime.cgd.CustomGameDAO;
import com.childrenOfTime.model.Equip.AbilComps.Ability;
import com.childrenOfTime.model.Equip.Effect;
import com.childrenOfTime.model.Equip.ItemComps.Item;

import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class AbilityChooserDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JComboBox comboBox1;
    private JLabel label1;
    private JButton addAbilityButton;

    private ArrayList<Ability> selectedAbilities;

    public AbilityChooserDialog(ArrayList<Ability> selectedAbilities) {
        this.selectedAbilities = selectedAbilities;
        for (Ability selectedAbility : selectedAbilities) {
            label1.setText(label1.getText() + " " + selectedAbility.getName());
        }
        ArrayList<String> abilityNames = CustomGameDAO.currentUserCustomAbilities.stream().map(Ability::getName).
                collect(Collectors.toCollection(ArrayList::new));
        comboBox1.setModel(new DefaultComboBoxModel(abilityNames.toArray()));


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
        addAbilityButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Ability toAdd = CustomGameDAO.currentUserCustomAbilities.get(comboBox1.getSelectedIndex());
                if (!selectedAbilities.contains(toAdd)) {
                    selectedAbilities.add(toAdd);
                    label1.setText(label1.getText() + " " + toAdd.getName());
                }
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
//        AbilityChooserDialog dialog = new AbilityChooserDialog();

        System.exit(0);
    }
}
