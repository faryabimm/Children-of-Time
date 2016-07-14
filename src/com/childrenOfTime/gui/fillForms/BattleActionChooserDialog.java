package com.childrenOfTime.gui.fillForms;

import com.childrenOfTime.gui.notification.NotificationType;
import com.childrenOfTime.gui.singlePlayer.BattleScreenPanel;
import com.childrenOfTime.model.Warriors.ActionType;
import com.childrenOfTime.model.Warriors.Warrior;
import com.childrenOfTime.utilities.GUIUtils;

import javax.swing.*;
import java.awt.event.*;

public class BattleActionChooserDialog extends JDialog {
    private JPanel contentPane;
    private JButton giveAttackButton;
    private JButton burnEPButton;
    private JButton castAnAbilityButton;
    private JButton useAnItemButton;

    private ActionType actionType;

    private Warrior warrior;
    private BattleScreenPanel source;

    public BattleActionChooserDialog(Warrior warrior, BattleScreenPanel battleScreenPanel) {

        this.source = battleScreenPanel;
        this.warrior = warrior;


        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(giveAttackButton);


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


        burnEPButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!warrior.getInfo().getCanBurnEP()) {
                    GUIUtils.showNotification("this Warrior: " + warrior.getName() + " (" + warrior.getInfo().getClassName() + ") " + warrior.getId() + " cannot burn EP", NotificationType.ERROR);
                }

            }
        });
        giveAttackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actionType = ActionType.Attack;
                dispose();
                chooseTargets(5);
            }
        });
        burnEPButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actionType = ActionType.BurnEP;
                dispose();
                chooseTargets(5);
            }
        });
        castAnAbilityButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actionType = ActionType.AbilityCast;
                dispose();
                chooseTargets(5);
            }
        });
        useAnItemButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actionType = ActionType.UseItem;
                dispose();
                chooseTargets(5);
            }
        });


        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    private void chooseTargets(int maxNumberOfTargets) {
        source.warriorChoosingWizard(maxNumberOfTargets);
    }

    private void onCancel() {
// add your code here if necessary
        dispose();
    }

    public static void main(String[] args) {
//        BattleActionChooserDialog dialog = new BattleActionChooserDialog();
        System.exit(0);
    }
}
