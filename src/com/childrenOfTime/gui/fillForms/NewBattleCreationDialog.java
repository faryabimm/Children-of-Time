package com.childrenOfTime.gui.fillForms;

import com.childrenOfTime.gui.customGame.CusomGameEditorMenu;
import com.childrenOfTime.gui.fillForms.dataHolders.BattleDataHolder;
import com.childrenOfTime.gui.singlePlayer.SinglePlayerMenuScreenPanel;
import com.childrenOfTime.model.ChildrenOfTime;

import javax.swing.*;
import java.awt.event.*;

public class NewBattleCreationDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField textField1;
    private JTextField textField2;
    private JButton selectBattleFoesButton;
    private JCheckBox noFoesSelectedCheckBox;
    private JTextField a0TextField;
    private JTextField a0TextField1;
    private JTextField a0TextField2;

    private BattleDataHolder dataHolder;

    public NewBattleCreationDialog(BattleDataHolder dataHolder) {

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


        selectBattleFoesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new WarriorChooserDialog(dataHolder.battleFoes);

                if (dataHolder.battleFoes.size() != 0) {
                    noFoesSelectedCheckBox.setSelected(true);
                    noFoesSelectedCheckBox.setText("selected " + dataHolder.battleFoes.size() + " Foes");
                }

                if (noFoesSelectedCheckBox.isSelected())
                    buttonOK.setEnabled(true);
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
        dataHolder.name = textField1.getText();
        dataHolder.defeatStory = textField2.getText();
        dataHolder.rewardMoney = Integer.parseInt(a0TextField.getText());
        dataHolder.rewardXP = Integer.parseInt(a0TextField1.getText());
        dataHolder.rewardImmortalityPotion = Integer.parseInt(a0TextField2.getText());
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
        NewBattleCreationDialog dialog = new NewBattleCreationDialog(new BattleDataHolder());
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
