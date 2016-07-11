package com.childrenOfTime.gui.fillForms;

import com.childrenOfTime.gui.customGame.CusomGameEditorMenu;
import com.childrenOfTime.gui.fillForms.dataHolders.AbilityDataHolder;
import com.childrenOfTime.model.ChildrenOfTime;
import com.childrenOfTime.model.Equip.AbilComps.Upgrade;
import com.childrenOfTime.model.Equip.Target;
import com.childrenOfTime.utilities.GUIUtils;

import javax.swing.*;
import java.awt.event.*;

public class NewAbilityCreationDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonNext;
    private JButton buttonCancel;
    private JTextField textField3;
    private JComboBox comboBox2;
    private JButton upgradesButton;
    private JButton browseButton;
    private JCheckBox noImageFileSelectedCheckBox;
    private JTextField textField6;
    private JTextField textField7;
    private JTextField textField8;
    private JTextField textField9;
    private JTextField textField10;
    private JTextField textField1;
    private JTextField textField2;

    AbilityDataHolder dataHolder;

    protected String imageFileAddress = null;

    public NewAbilityCreationDialog(AbilityDataHolder dataHolder) {
        this.dataHolder = dataHolder;
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonNext);

        buttonNext.addActionListener(new ActionListener() {
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
        upgradesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Upgrade toAdd = null;
                new AddUpgradeToAbilityDialog(toAdd);
                if (!dataHolder.upgrades.contains(toAdd)) {
                    dataHolder.upgrades.add(toAdd);
                }
            }
        });



        browseButton.addActionListener(new ActionListener() {
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


        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);



    }

    private void onOK() {
// add your code here
        collectData();
        disposalProcess();

    }

    private void collectData() {
        dataHolder.abilityName = textField3.getText();

        switch (comboBox2.getSelectedIndex()) {
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

        dataHolder.imagePath = imageFileAddress;
        dataHolder.power = Integer.parseInt(textField1.getText());
        dataHolder.description = textField6.getText();
        dataHolder.successMessage = textField7.getText();
        dataHolder.EPFailiureMessage = textField8.getText();
        dataHolder.MPFailiureMessage = textField9.getText();
        dataHolder.cooldownFailiureMessage = textField10.getText();
        dataHolder.notAcqiredFailiureMessage = textField2.getText();

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
//        NewAbilityCreationDialog dialog = new NewAbilityCreationDialog();

        System.exit(0);
    }
}
