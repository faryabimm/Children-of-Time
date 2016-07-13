package com.childrenOfTime.gui.fillForms;

import com.childrenOfTime.gui.MainMenuScreenPanel;
import com.childrenOfTime.gui.notification.NotificationType;
import com.childrenOfTime.model.ChildrenOfTime;
import com.childrenOfTime.model.DIFFICUALTY;
import com.childrenOfTime.model.Rules;
import com.childrenOfTime.utilities.GUIUtils;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.*;

public class SettingsScreenDialog extends JDialog {
    private final ButtonGroup radioGroup;
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JCheckBox changeScenarioSInitialCheckBox;
    private JSlider slider1;
    private JSlider slider2;
    private JSlider slider3;
    private JLabel label1;
    private JLabel label2;
    private JLabel label3;
    private JPanel panel1;
    private JLabel label4;
    private JLabel label5;
    private JLabel label6;
    private JRadioButton newbeRadioButton;
    private JRadioButton mediumRadioButton;
    private JRadioButton hardRadioButton;
    private JRadioButton nightmareRadioButton;
    private JSlider slider4;
    private JLabel label7;
    private JSlider slider5;
    private JCheckBox allOfPlayerSCheckBox;
    private JLabel label8;
    private JLabel label9;
    private JCheckBox attackPowerCanBeCheckBox;
    private JCheckBox refilRatesCanBeCheckBox;
    private JCheckBox intelligentEnemyDamageTakingCheckBox;
    private JCheckBox giveRewardBasedOnCheckBox;

    public SettingsScreenDialog() {

        radioGroup = new ButtonGroup();


        radioGroup.add(newbeRadioButton);
        radioGroup.add(mediumRadioButton);
        radioGroup.add(hardRadioButton);
        radioGroup.add(nightmareRadioButton);
        setContentPane(contentPane);
        new Rules();

        loadTheForm();
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onOK();
            }
        });
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        slider1.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                label1.setText(String.valueOf(slider1.getValue()));
                Rules.INITIAL_MONEY = slider1.getValue();
            }
        });
        slider2.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                label2.setText(String.valueOf(slider2.getValue()));
                Rules.INITIAL_XP = slider2.getValue();
            }
        });
        slider3.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                label3.setText(String.valueOf(slider3.getValue()));
                Rules.INITIAL_IMMORTALITY_POTION = slider3.getValue();
            }
        });
        changeScenarioSInitialCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (changeScenarioSInitialCheckBox.isSelected()) {
                    slider1.setEnabled(true);
                    slider2.setEnabled(true);
                    slider3.setEnabled(true);
                    label1.setEnabled(true);
                    label2.setEnabled(true);
                    label3.setEnabled(true);
                    label4.setEnabled(true);
                    label5.setEnabled(true);
                    label6.setEnabled(true);
                } else {
                    slider1.setEnabled(false);
                    slider2.setEnabled(false);
                    slider3.setEnabled(false);
                    label1.setEnabled(false);
                    label2.setEnabled(false);
                    label3.setEnabled(false);
                    label4.setEnabled(false);
                    label5.setEnabled(false);
                    label6.setEnabled(false);
                    slider1.setValue(Rules.INITIAL_MONEY_DEFAULT);
                    slider2.setValue(Rules.INITIAL_XP_DEFAULT);
                    slider3.setValue(Rules.INITIAL_IMMORTALITY_POTION_DEFAULT);
                }
            }
        });
        newbeRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Rules.DIFFICUALTY = DIFFICUALTY.Easy;
            }
        });


        mediumRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Rules.DIFFICUALTY = DIFFICUALTY.Medium;
            }
        });
        hardRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Rules.DIFFICUALTY = DIFFICUALTY.Hard;
            }
        });
        nightmareRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Rules.DIFFICUALTY = DIFFICUALTY.NightMare;
            }
        });

        slider5.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                label8.setText(String.valueOf(slider5.getValue()));
                Rules.NUMBER_OF_HEROS_DYE_FROM_HUMAN_PLAYER_TO_DEFEAT = slider4.getValue();
            }
        });
        allOfPlayerSCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!allOfPlayerSCheckBox.isSelected()) {
                    label8.setEnabled(true);
                    label9.setEnabled(true);
                    slider5.setEnabled(true);
                    Rules.NUMBER_OF_HEROS_DYE_FROM_HUMAN_PLAYER_TO_DEFEAT = slider5.getValue();
                } else {
                    label8.setEnabled(false);
                    label9.setEnabled(false);
                    slider5.setEnabled(false);
                    slider5.setValue(Rules.NUMBER_OF_HEROS_DYE_FROM_HUMAN_PLAYER_TO_DEFEAT_DEFAULT);
                    Rules.NUMBER_OF_HEROS_DYE_FROM_HUMAN_PLAYER_TO_DEFEAT = 1000;
                }
            }
        });

        slider4.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                label7.setText(String.valueOf(slider4.getValue()));
                Rules.Quantitiy_Of_Targets_For_Manual_Multiple_Target_Choosing = slider4.getValue();
            }
        });

        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        intelligentEnemyDamageTakingCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Rules.INTELLIJENT_DAMGES = intelligentEnemyDamageTakingCheckBox.isSelected();
            }
        });
    }

    private void loadTheForm() {

        if (Rules.INITIAL_MONEY != Rules.INITIAL_MONEY_DEFAULT || Rules.INITIAL_XP != Rules.INITIAL_XP_DEFAULT || Rules.INITIAL_IMMORTALITY_POTION != Rules.INITIAL_IMMORTALITY_POTION_DEFAULT) {
            changeScenarioSInitialCheckBox.setSelected(true);
            slider1.setValue(Rules.INITIAL_MONEY);
            slider2.setValue(Rules.INITIAL_XP);
            slider3.setValue(Rules.INITIAL_IMMORTALITY_POTION);
        } else {
            changeScenarioSInitialCheckBox.setSelected(false);
        }

        switch (Rules.DIFFICUALTY) {
            case Easy:
                newbeRadioButton.setSelected(true);
                break;
            case Medium:
                mediumRadioButton.setSelected(true);
                break;
            case Hard:
                hardRadioButton.setSelected(true);
                break;
            case NightMare:
                nightmareRadioButton.setSelected(true);
                break;
        }

        slider4.setValue(Rules.Quantitiy_Of_Targets_For_Manual_Multiple_Target_Choosing);

        if (Rules.NUMBER_OF_HEROS_DYE_FROM_HUMAN_PLAYER_TO_DEFEAT == 1000) allOfPlayerSCheckBox.setSelected(true);
        else allOfPlayerSCheckBox.setSelected(false);

        attackPowerCanBeCheckBox.setSelected(Rules.AttackPowerCanBeNegative);
        refilRatesCanBeCheckBox.setSelected(Rules.RefillRatesCanBeNegative);
        intelligentEnemyDamageTakingCheckBox.setSelected(Rules.INTELLIJENT_DAMGES);
        giveRewardBasedOnCheckBox.setSelected(Rules.giveRandomRewardByDifferentiationBetweenWinnerAndLoser);

    }

    private void onOK() {
// add your code here
        disposalProcess();
    }

    private void disposalProcess() {
        ChildrenOfTime.changeContentPane(new MainMenuScreenPanel());
        GUIUtils.showNotification("Your settings were saved successfully!", NotificationType.GOOD);
        dispose();
    }

    public static void main(String[] args) {
        SettingsScreenDialog dialog = new SettingsScreenDialog();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
