package com.childrenOfTime.gui.fillForms;

import com.childrenOfTime.gui.customGame.CusomGameEditorMenu;
import com.childrenOfTime.model.ChildrenOfTime;

import javax.swing.*;
import java.awt.event.*;

public class NewItemCreationDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField textField3;
    private JTextField textField2;
    private JButton addEffectsButton;
    private JCheckBox inflationRateCheckBox;
    private JTextField textField4;
    private JTextField textField5;
    private JCheckBox reusableCheckBox;
    private JCheckBox automaticallyUseAfterPurchaseCheckBox;
    private JCheckBox canBePlacedInCheckBox;
    private JCheckBox removeFromInventoryUponCheckBox;
    private JCheckBox wearOffEffectsUponCheckBox;
    private JCheckBox hasCooldownCheckBox;
    private JTextField textField1;
    private JComboBox comboBox1;
    private JTextField textField6;
    private JTextField textField7;
    private JTextField textField8;
    private JTextField textField9;
    private JTextField textField10;
    private JButton browseForImageFileButton;
    private JCheckBox noImageFileSelectedCheckBox;

    public NewItemCreationDialog() {
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
        addEffectsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddEffectDialog(false);
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
        ChildrenOfTime.changeContentPane(new CusomGameEditorMenu());
        dispose();
    }

    private void onCancel() {
// add your code here if necessary
        disposalProcess();
    }

    public static void main(String[] args) {
        NewItemCreationDialog dialog = new NewItemCreationDialog();

        System.exit(0);
    }
}
