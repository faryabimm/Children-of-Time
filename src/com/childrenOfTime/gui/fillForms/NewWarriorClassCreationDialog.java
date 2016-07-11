package com.childrenOfTime.gui.fillForms;

import com.childrenOfTime.gui.customGame.CusomGameEditorMenu;
import com.childrenOfTime.gui.fillForms.dataHolders.WarriorClassDataHolder;
import com.childrenOfTime.model.ChildrenOfTime;
import com.childrenOfTime.utilities.GUIUtils;

import javax.swing.*;
import java.awt.event.*;

public class NewWarriorClassCreationDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JCheckBox canBurnEPCheckBox;
    private JTextField a0TextField10;
    private JTextField a0TextField11;
    private JTextField a0TextField12;
    private JCheckBox canAttackCheckBox;
    private JCheckBox healthRelatedAPCheckBox;
    private JTextField a0TextField7;
    private JTextField a0TextField8;
    private JTextField a0TextField9;
    private JCheckBox EPChngeSystemCheckBox;
    private JCheckBox canUseImmortalityPotionCheckBox;
    private JCheckBox MPChangeSystemCheckBox;
    private JCheckBox canUseRefilSystemCheckBox;
    private JTextField textField10;
    private JTextField textField11;
    private JTextField a0TextField;
    private JTextField a0TextField4;
    private JTextField a0TextField1;
    private JTextField a0TextField2;
    private JTextField a0TextField5;
    private JTextField a0TextField3;
    private JTextField a0TextField6;
    private JButton defineAbilitiesButton;
    private JButton chooseAnImageButton;
    private JCheckBox noImageFileSelectedCheckBox;
    private JLabel lebel1;
    private JLabel lebel2;
    private JLabel lebel3;
    private JLabel label4;
    private JLabel label2;
    private JLabel label3;
    private JTextField textField4;
    private JTextField a0TextField13;
    private JLabel labelEPburningMessage;
    private JLabel labelMutationHealthLimit;
    private JCheckBox canBuyItemsCheckBox;

    private String imageFilePath;


    private WarriorClassDataHolder dataHolder;

    public NewWarriorClassCreationDialog(WarriorClassDataHolder dataHolder) {

        this.dataHolder = dataHolder;

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
        canBurnEPCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (canBurnEPCheckBox.isSelected()) {
                    a0TextField10.setEnabled(true);
                    a0TextField11.setEnabled(true);
                    a0TextField12.setEnabled(true);
                    textField4.setEnabled(true);
                    lebel1.setEnabled(true);
                    lebel2.setEnabled(true);
                    lebel3.setEnabled(true);
                    label4.setEnabled(true);
                    labelEPburningMessage.setEnabled(true);
                } else {
                    a0TextField10.setEnabled(false);
                    a0TextField11.setEnabled(false);
                    a0TextField12.setEnabled(false);
                    textField4.setEnabled(false);
                    lebel1.setEnabled(false);
                    lebel2.setEnabled(false);
                    lebel3.setEnabled(false);
                    label4.setEnabled(false);
                    labelEPburningMessage.setEnabled(false);
                }
            }
        });
        healthRelatedAPCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (healthRelatedAPCheckBox.isSelected()) {
                    a0TextField8.setEnabled(true);
                    a0TextField9.setEnabled(true);
                    a0TextField13.setEnabled(true);
                    labelMutationHealthLimit.setEnabled(true);
                    label2.setEnabled(true);
                    label3.setEnabled(true);
                } else {
                    a0TextField8.setEnabled(false);
                    a0TextField9.setEnabled(false);
                    label2.setEnabled(false);
                    label3.setEnabled(false);
                    a0TextField13.setEnabled(false);
                    labelMutationHealthLimit.setEnabled(false);
                }
            }
        });
        chooseAnImageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                imageFilePath = GUIUtils.getPNGImageFilePath(null);
                try {
                    noImageFileSelectedCheckBox.setText("..." + imageFilePath.substring(imageFilePath.length() - 15,
                            imageFilePath.length()));
                } catch (NullPointerException e1) {
                }
                if (imageFilePath != null) {
                    noImageFileSelectedCheckBox.setSelected(true);
                }
            }
        });
        defineAbilitiesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AbilityChooserDialog(dataHolder.warriorClassAbilities);
            }
        });

        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    private void onOK() {
// add your code here
        collectData();
        disposalProcess();

    }

    private void disposalProcess() {
        ChildrenOfTime.changeContentPane(new CusomGameEditorMenu());
        dispose();
    }

    private void collectData() {
        dataHolder.name = textField1.getText();
        dataHolder.description = textField2.getText();
        dataHolder.backStory = textField3.getText();
        dataHolder.dyingMessage = textField11.getText();
        dataHolder.ActionMessage = textField10.getText();
        dataHolder.canAttack = canAttackCheckBox.isSelected();
        dataHolder.canBurnEP = canBurnEPCheckBox.isSelected();
        dataHolder.burnEPRangeStart = Integer.parseInt(a0TextField10.getText());
        dataHolder.burnEPRangeEnd = Integer.parseInt(a0TextField11.getText());
        dataHolder.EPCostForPerformer = Integer.parseInt(a0TextField12.getText());
        dataHolder.healthRelatedAP = healthRelatedAPCheckBox.isSelected();
        dataHolder.APInHighHealthLevel = Integer.parseInt(a0TextField7.getText());
        dataHolder.APInLowHealthLevel = Integer.parseInt(a0TextField8.getText());
        dataHolder.mutationMessage = a0TextField9.getText();

        dataHolder.EPChangeSystem = EPChngeSystemCheckBox.isSelected();
        dataHolder.MPChangeSystem = MPChangeSystemCheckBox.isSelected();
        dataHolder.CanUseImmortalityPotion = canUseImmortalityPotionCheckBox.isSelected();
        dataHolder.CanUseRefilSystemFeatures = canUseRefilSystemCheckBox.isSelected();

        dataHolder.maxH = Integer.parseInt(a0TextField.getText());
        dataHolder.maxM = Integer.parseInt(a0TextField1.getText());
        dataHolder.inventorySize = Integer.parseInt(a0TextField2.getText());
        dataHolder.turnInitialEP = Integer.parseInt(a0TextField3.getText());
        dataHolder.HRR = Integer.parseInt(a0TextField4.getText());
        dataHolder.MRR = Integer.parseInt(a0TextField5.getText());
        dataHolder.AI = Integer.parseInt(a0TextField6.getText());
        dataHolder.imagePath = imageFilePath;


        dataHolder.EPBurningMessage = textField4.getText();
        dataHolder.mutationHealthLimit = Integer.parseInt(a0TextField13.getText());

        dataHolder.canBuyItems = canBuyItemsCheckBox.isSelected();
    }

    private void onCancel() {
// add your code here if necessary
        disposalProcess();
    }

    public static void main(String[] args) {
        NewWarriorClassCreationDialog dialog = new NewWarriorClassCreationDialog(new WarriorClassDataHolder());

        System.exit(0);
    }
}
