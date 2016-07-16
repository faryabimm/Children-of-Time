package com.childrenOfTime.gui.fillForms;

import com.childrenOfTime.cgd.CustomGameDAO;
import com.childrenOfTime.model.Equip.AbilComps.Ability;
import com.childrenOfTime.model.Player;
import com.childrenOfTime.model.Warriors.Warrior;

import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class UpgradeDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JComboBox comboBox1;
    private JComboBox comboBox2;

    private Player player;

    private ArrayList<String> selectedWarriorAbilityNames = new ArrayList<>();

    public UpgradeDialog(Player player) {
        this.player = player;

        ArrayList<String> warriorNames = player.getMyTeam().stream().map(Warrior::getName).collect(Collectors.toCollection(ArrayList::new));

        comboBox1.setModel(new DefaultComboBoxModel(warriorNames.toArray()));


        selectedWarriorAbilityNames.addAll(player.getMyTeam().get(0).abilities.stream().map(Ability::getName).collect(Collectors.toList()));

        comboBox2.setModel(new DefaultComboBoxModel(selectedWarriorAbilityNames.toArray()));


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


        comboBox1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                selectedWarriorAbilityNames = new ArrayList<String>();
                selectedWarriorAbilityNames.addAll(player.getMyTeam().get(comboBox1.getSelectedIndex()).abilities.stream().map(Ability::getName).collect(Collectors.toList()));
                comboBox2.setModel(new DefaultComboBoxModel(selectedWarriorAbilityNames.toArray()));
            }
        });
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    private void onOK() {
// add your code here
        Warrior selectedWarrior = player.getMyTeam().get(comboBox1.getSelectedIndex());
        Ability selectedAbility = selectedWarrior.abilities.get(comboBox2.getSelectedIndex());
        player.upgradeAbility(selectedAbility, selectedWarrior, 0);
        dispose();
    }

    private void onCancel() {
// add your code here if necessary
        dispose();
    }

    public static void main(String[] args) {
//        UpgradeDialog dialog = new UpgradeDialog();
        System.exit(0);
    }
}
