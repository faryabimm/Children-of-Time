package com.childrenOfTime.gui.fillForms;

import com.childrenOfTime.gui.customGame.CusomGameEditorMenu;
import com.childrenOfTime.gui.fillForms.dataHolders.ItemDataHolder;
import com.childrenOfTime.model.ChildrenOfTime;
import com.childrenOfTime.model.Equip.Target;
import com.childrenOfTime.utilities.GUIUtils;

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
    private JTextField textField11;
    private JTextField textField12;
    private JTextField textField13;
    private JTextField textField14;
    private JTextField textField15;
    private JTextField textField16;
    private JTextField textField17;
    private JTextField textField18;
    private JTextField textField19;
    private JTextField textField20;
    private JTextField textField21;
    private JTextField textField22;
    private JTextField textField23;
    private JTextField textField24;
    private JTextField textField25;


    ItemDataHolder dataHolder;
    protected String imageFileAddress = null;

    public NewItemCreationDialog(ItemDataHolder dataHolder) {
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
        addEffectsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new EffectChooserDialog(dataHolder.effects);
            }
        });

        browseForImageFileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String imageFilePath = GUIUtils.getPNGImageFilePath(null);
                try {
                    noImageFileSelectedCheckBox.setText("..." + imageFilePath.substring(imageFilePath.length() - 15,
                            imageFilePath.length()));
                } catch (NullPointerException e1) {}
                imageFileAddress = imageFilePath;
                noImageFileSelectedCheckBox.setSelected(true);
            }
        });
        inflationRateCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(inflationRateCheckBox.isSelected()) textField4.setEnabled(true);
                else textField4.setEnabled(false);
            }
        });
        reusableCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(reusableCheckBox.isSelected()) textField2.setEnabled(true);
                else textField2.setEnabled(false);
            }
        });

        hasCooldownCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(hasCooldownCheckBox.isSelected()) textField1.setEnabled(true);
                else textField1.setEnabled(false);
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
        collectData();
        ChildrenOfTime.changeContentPane(new CusomGameEditorMenu());
        dispose();
    }

    private void collectData() {
        dataHolder.itemName = textField3.getText();

        switch (comboBox1.getSelectedIndex()) {
            case 0:
                dataHolder.targetType = Target.HimSelf;
                break;
            case 1:
                dataHolder.targetType = Target.SingleEnemy;
                break;
            case 2:
                dataHolder.targetType = Target.AllTeammates;
                break;
            case 3:
                dataHolder.targetType = Target.AllEnemies;
                break;
            case 4:
                dataHolder.targetType = Target.theAttackedOne;
                break;
        }

        dataHolder.initialPrice = Integer.parseInt(textField5.getText());

        dataHolder.hasInflation = inflationRateCheckBox.isSelected();
        dataHolder.inflationRate = Integer.parseInt(textField4.getText());

        dataHolder.isReusable = reusableCheckBox.isSelected();
        dataHolder.reusableTimes = Integer.parseInt(textField2.getText());
        dataHolder.hasCooldown = hasCooldownCheckBox.isSelected();
        dataHolder.cooldownTurns = Integer.parseInt(textField1.getText());

        dataHolder.imagePath = imageFileAddress;


        dataHolder.autoUseAfterPurchse = automaticallyUseAfterPurchaseCheckBox.isSelected();
        dataHolder.placableInInventory = canBePlacedInCheckBox.isSelected();
        dataHolder.removeFromInventoryAfterUsageLimit = removeFromInventoryUponCheckBox.isSelected();
        dataHolder.wearOffEffectsAfterSelling = wearOffEffectsUponCheckBox.isSelected();

        dataHolder.description = textField6.getText();
        dataHolder.successMessage = textField7.getText();
        dataHolder.EPFailiureMessage = textField8.getText();
        dataHolder.MPFailiureMessage = textField9.getText();
        dataHolder.cooldownFailiureMessage = textField10.getText();
        dataHolder.notAcqiredFailiureMessage = textField11.getText();


        dataHolder.APCoefficient = Double.parseDouble(textField18.getText());
        dataHolder.HCoefficient = Double.parseDouble(textField19.getText());
        dataHolder.MPCoefficient = Double.parseDouble(textField20.getText());
        dataHolder.MMPCoefficient = Double.parseDouble(textField21.getText());
        dataHolder.EPCoefficient = Double.parseDouble(textField22.getText());
        dataHolder.HRRCoefficient = Double.parseDouble(textField23.getText());
        dataHolder.MMRRCoefficient = Double.parseDouble(textField24.getText());
        dataHolder.APIncrement = Integer.parseInt(textField25.getText());
        dataHolder.HIncrement = Integer.parseInt(textField12.getText());
        dataHolder.MPIncrement = Integer.parseInt(textField13.getText());
        dataHolder.MMPIncrement = Integer.parseInt(textField14.getText());
        dataHolder.EPIncrement = Integer.parseInt(textField15.getText());
        dataHolder.HRRIncrement = Integer.parseInt(textField16.getText());
        dataHolder.MMRRIncrement = Integer.parseInt(textField17.getText());
    }

    private void onCancel() {
// add your code here if necessary
        disposalProcess();
    }


    public static void main(String[] args) {
//        NewItemCreationDialog dialog = new NewItemCreationDialog();

        System.exit(0);
    }
}
