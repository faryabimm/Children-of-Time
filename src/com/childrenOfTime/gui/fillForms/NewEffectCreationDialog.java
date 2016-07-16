package com.childrenOfTime.gui.fillForms;

import com.childrenOfTime.gui.customGame.CusomGameEditorMenu;
import com.childrenOfTime.gui.fillForms.dataHolders.EffectDataHolder;
import com.childrenOfTime.model.ChildrenOfTime;
import com.childrenOfTime.model.Equip.Target;

import javax.swing.*;
import java.awt.event.*;

public class NewEffectCreationDialog extends JDialog {

    private final ButtonGroup radioGroup;
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
    private JTextField textField1;
    private JTextField textField3;
    private JTextField textField6;
    private JTextField textField7;
    private JTextField textField8;
    private JTextField textField9;
    private JTextField textField10;
    private JTextField textField11;
    private JTextField textField12;
    private JTextField textField13;
    private JTextField textField14;
    private JTextField textField15;
    private JTextField textField16;
    private JTextField textField17;
    private JCheckBox wearOffEffectsAfteCheckBox;
    private JRadioButton himselfRadioButton;
    private JRadioButton allTeammatesRadioButton;
    private JRadioButton theEnemyRadioButton;
    private JRadioButton allEnemiesRadioButton;
    private JRadioButton theChosenEnemyRadioButton;
    private JTextField textField18;
    private JTextField a0TextField1;
    private JTextField a0TextField;
    private JRadioButton singleTeammateRadioButton;

    EffectDataHolder dataHolder;

    private boolean invokedDirectly = false;




    public NewEffectCreationDialog(boolean invokedDirectly, EffectDataHolder dataHolder) {
        radioGroup = new ButtonGroup();
        radioGroup.add(himselfRadioButton);
        radioGroup.add(allTeammatesRadioButton);
        radioGroup.add(theEnemyRadioButton);
        radioGroup.add(allEnemiesRadioButton);
        radioGroup.add(theChosenEnemyRadioButton);
        radioGroup.add(singleTeammateRadioButton);

        this.dataHolder = dataHolder;
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
        automaticTargetCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (automaticTargetCheckBox.isSelected()) {
                    himselfRadioButton.setEnabled(true);
                    theEnemyRadioButton.setEnabled(false);
                    singleTeammateRadioButton.setEnabled(false);
                    allTeammatesRadioButton.setEnabled(true);
                    allEnemiesRadioButton.setEnabled(true);
                    theChosenEnemyRadioButton.setEnabled(true);

                } else {
                    himselfRadioButton.setEnabled(false);
                    singleTeammateRadioButton.setEnabled(true);
                    theEnemyRadioButton.setEnabled(true);
                    allTeammatesRadioButton.setEnabled(true);
                    allEnemiesRadioButton.setEnabled(true);
                    theChosenEnemyRadioButton.setEnabled(false);
                }
            }
        });
        temporaryEffectCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(temporaryEffectCheckBox.isSelected()) textField4.setEnabled(true);
                else textField4.setEnabled(false);
            }
        });
        autoRepeatableCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(autoRepeatableCheckBox.isSelected()) textField5.setEnabled(true);
                else textField5.setEnabled(false);
            }
        });
        indefiniteExcecutionCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(indefiniteExcecutionCheckBox.isSelected()) textField2.setEnabled(true);
                else textField2.setEnabled(false);
            }
        });
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    private void onOK() {
// add your code here


        collectData();
        if (invokedDirectly) disposalProcess();
        else dispose();
    }

    private void collectData() {

        dataHolder.name = textField18.getText();

        dataHolder.applyUponAttack = applyUponAttackCheckBox.isSelected();
        dataHolder.temporaryEffect = temporaryEffectCheckBox.isSelected();
        dataHolder.temporaryEffectTurnCount = Integer.parseInt(textField4.getText());
        dataHolder.autoRepeatable = autoRepeatableCheckBox.isSelected();
        dataHolder.autoRepeatableTurnCount = Integer.parseInt(textField4.getText());
        dataHolder.indefiniteExcecution = indefiniteExcecutionCheckBox.isSelected();
        dataHolder.indefiniteExcecutionPercent = Integer.parseInt(textField2.getText());
        dataHolder.wearOffEffectsAfterExcecution = wearOffEffectsAfteCheckBox.isSelected();
        dataHolder.automaticTargetSelection = automaticTargetCheckBox.isSelected();

        if (automaticTargetCheckBox.isSelected()) {
            if (himselfRadioButton.isSelected()) dataHolder.TargetType = Target.HimSelf;
            if (allTeammatesRadioButton.isSelected()) dataHolder.TargetType = Target.AllTeammates;
            if (allEnemiesRadioButton.isSelected()) dataHolder.TargetType = Target.AllEnemies;
            if (theChosenEnemyRadioButton.isSelected()) dataHolder.TargetType = Target.theAttackedOne;
        } else {
//            if (himselfRadioButton.isSelected()) dataHolder.TargetType = Target.HimSelf;
            if (allTeammatesRadioButton.isSelected()) dataHolder.TargetType = Target.SeveralTeamMates;
            if (allEnemiesRadioButton.isSelected()) dataHolder.TargetType = Target.SeveralEnemies;
//            if (theChosenEnemyRadioButton.isSelected()) dataHolder.TargetType = Target.theAttackedOne;
            if (singleTeammateRadioButton.isSelected()) dataHolder.TargetType = Target.SingleTeamMate;
            if (theEnemyRadioButton.isSelected()) dataHolder.TargetType = Target.SingleEnemy;
        }

        dataHolder.APCoefficient = Double.parseDouble(textField1.getText());
        dataHolder.HCoefficient = Double.parseDouble(textField6.getText());
        dataHolder.MPCoefficient = Double.parseDouble(textField7.getText());
        dataHolder.MMPCoefficient = Double.parseDouble(textField8.getText());
        dataHolder.EPCoefficient = Double.parseDouble(textField9.getText());
        dataHolder.HRRCoefficient = Double.parseDouble(textField10.getText());
        dataHolder.MMRRCoefficient = Double.parseDouble(textField11.getText());
        dataHolder.MHCoefficient = Double.parseDouble(a0TextField1.getText());

        dataHolder.APIncrement = Integer.parseInt(textField3.getText());
        dataHolder.HIncrement = Integer.parseInt(textField12.getText());
        dataHolder.MPIncrement = Integer.parseInt(textField13.getText());
        dataHolder.MMPIncrement = Integer.parseInt(textField14.getText());
        dataHolder.EPIncrement = Integer.parseInt(textField15.getText());
        dataHolder.HRRIncrement = Integer.parseInt(textField16.getText());
        dataHolder.MMRRIncrement = Integer.parseInt(textField17.getText());
        dataHolder.MHIncrement = Integer.parseInt(a0TextField.getText());
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
        EffectDataHolder dataHolder = new EffectDataHolder();
        NewEffectCreationDialog dialog = new NewEffectCreationDialog(false, dataHolder);
        System.out.println(dataHolder);
        System.exit(0);
    }
}
