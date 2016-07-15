package com.childrenOfTime.gui.fillForms;

import com.childrenOfTime.gui.fillForms.dataHolders.AbilityWrapper;
import com.childrenOfTime.gui.fillForms.dataHolders.ItemWrapper;
import com.childrenOfTime.model.Warriors.Warrior;

import javax.swing.*;
import java.awt.event.*;

public class AbilityChooserByWarriorDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private Warrior targetWarrior;
    private AbilityWrapper selectedAbility;

    public AbilityChooserByWarriorDialog(Warrior targetWarrior, AbilityWrapper selectedAbility) {

        this.selectedAbility = selectedAbility;
        this.targetWarrior = targetWarrior;
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
        dispose();
    }

    private void onCancel() {
// add your code here if necessary
        dispose();
    }

    public static void main(String[] args) {
//        AbilityChooserByWarriorDialog dialog = new AbilityChooserByWarriorDialog();

        System.exit(0);
    }
}
