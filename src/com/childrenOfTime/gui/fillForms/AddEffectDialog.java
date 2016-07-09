package com.childrenOfTime.gui.fillForms;

import com.childrenOfTime.gui.customGame.CusomGameEditorMenu;
import com.childrenOfTime.model.ChildrenOfTime;

import javax.swing.*;
import java.awt.event.*;

public class AddEffectDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField textField4;
    private JTextField textField5;
    private JTextField textField2;
    private JCheckBox temporaryEffectCheckBox;
    private JCheckBox applyUponAttackCheckBox;
    private JCheckBox autoRepeatableCheckBox;
    private JCheckBox indefiniteExcecutionCheckBox;
    private JCheckBox automaticTargetCheckBox;
    private JCheckBox himselfCheckBox;
    private JCheckBox theEnemyCheckBox;
    private JCheckBox allTeammatesCheckBox;
    private JCheckBox allEnemiesCheckBox;
    private JTextField textField1;
    private JTextField textField3;

    private boolean invokedDirectly = false;




    public AddEffectDialog(boolean invokedDirectly) {
        this.invokedDirectly = invokedDirectly;
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
        if (invokedDirectly) disposalProcess();
        else dispose();
    }

    private void disposalProcess() {
        ChildrenOfTime.changeContentPane(new CusomGameEditorMenu());
        dispose();
    }

    private void onCancel() {
// add your code here if necessary
        if (invokedDirectly) disposalProcess();
        else dispose();
    }

    public static void main(String[] args) {
        AddEffectDialog dialog = new AddEffectDialog(false);
        System.exit(0);
    }
}
