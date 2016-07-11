package com.childrenOfTime.gui.fillForms;

import com.childrenOfTime.model.Equip.AbilComps.Upgrade;
import com.childrenOfTime.model.Equip.Effect;
import com.childrenOfTime.model.Equip.ItemComps.Messages;

import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;

public class AddUpgradeToAbilityDialog extends JDialog {

    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField textField3;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField4;
    private JTextField textField5;
    private JTextField textField6;
    private JCheckBox immediateApplicationActiveCheckBox;
    private JCheckBox recastableCheckBox;
    private JCheckBox setAsBaseUpgradeCheckBox;
    private JTextField textField7;
    private JCheckBox hasRequirementsCheckBox;
    private JButton addEffectsButton;

    Upgrade toAdd;

    ArrayList<Effect> addedEffects = new ArrayList<>();

    public AddUpgradeToAbilityDialog(Upgrade toAdd) {

        this.toAdd = toAdd;


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
                new EffectChooserDialog(addedEffects);
            }
        });

        hasRequirementsCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(hasRequirementsCheckBox.isSelected()) textField7.setEnabled(true);
                else textField7.setEnabled(false);
            }
        });
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    private void onOK() {

        int upgradeID = Integer.parseInt(textField3.getText());
        int coolDownTime = Integer.parseInt(textField4.getText());
        int XPCostToUpgrade = Integer.parseInt(textField5.getText());
        int EPCOstToCast = Integer.parseInt(textField2.getText());
        int MPCOstToCast = Integer.parseInt(textField1.getText());
        String description = textField6.getText();
        boolean isActive = immediateApplicationActiveCheckBox.isSelected();
        boolean isRecastable = recastableCheckBox.isSelected();
        boolean isBaseUpgrade = setAsBaseUpgradeCheckBox.isSelected();
        boolean hasRequirements = hasRequirementsCheckBox.isSelected();
        String requirements[] = textField7.getText().split("\\s");

        toAdd = new Upgrade(upgradeID, coolDownTime, XPCostToUpgrade, EPCOstToCast, MPCOstToCast, isActive, isRecastable, addedEffects, requirements);


// add your code here
        dispose();
    }

    private void onCancel() {
// add your code here if necessary
        dispose();
    }

    public static void main(String[] args) {
//        AddUpgradeToAbilityDialog dialog = new AddUpgradeToAbilityDialog(toAdd);

        System.exit(0);
    }
}
